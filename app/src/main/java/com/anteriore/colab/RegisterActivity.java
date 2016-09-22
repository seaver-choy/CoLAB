package com.anteriore.colab;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.anteriore.colab.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private EditText inputFirstname;
    private EditText inputLastname;
    private EditText inputEmail;
    private EditText inputPassword;
    private EditText confirmPassword;
    private EditText month;
    private EditText day;
    private EditText year;
    private Button registerButton;
    private FirebaseAuth auth;
    private FirebaseModel fbModel;
    private String TAG = "RegisterActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth = FirebaseAuth.getInstance();
        fbModel = new FirebaseModel();
        setContentView(R.layout.activity_register);

        inputFirstname = (EditText) findViewById(R.id.register_input_firstname);
        inputLastname = (EditText) findViewById(R.id.register_input_lastname);
        inputEmail = (EditText) findViewById(R.id.register_input_email);
        inputPassword = (EditText) findViewById(R.id.register_input_password);
        confirmPassword = (EditText) findViewById(R.id.register_confirm_password);
        month = (EditText) findViewById(R.id.register_month);
        day = (EditText) findViewById(R.id.register_day);
        year = (EditText) findViewById(R.id.register_year);
        registerButton = (Button) findViewById(R.id.button_register);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitForm();
            }
        });
    }

    private void submitForm() {

        String email = inputEmail.getText().toString().trim();
        String password = inputPassword.getText().toString().trim();

        if(!checkEmail()) {
            return;
        }
        if(!checkPassword()) {
            return;
        }
        //signupInputLayoutEmail.setErrorEnabled(false);
        //signupInputLayoutPassword.setErrorEnabled(false);

        //progressBar.setVisibility(View.VISIBLE);
        //create user
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());
                        //progressBar.setVisibility(View.GONE);
                        // If sign in fails, Log the message to the LogCat. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.d(TAG,"Authentication failed." + task.getException());

                        } else{
                            User newUser = new User(auth.getCurrentUser().getUid(), inputFirstname.getText().toString(),inputLastname.getText().toString(), inputEmail.getText().toString());
                            fbModel.writeNewUserToDatabase(newUser);
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                            finish();
                        }
                    }
                });
        Toast.makeText(getApplicationContext(), "You are successfully Registered !!", Toast.LENGTH_SHORT).show();
    }

    private boolean checkEmail() {
        String email = inputEmail.getText().toString().trim();
        if (email.isEmpty() || !isEmailValid(email)) {

            //signupInputLayoutEmail.setErrorEnabled(true);
            //signupInputLayoutEmail.setError(getString(R.string.err_msg_email));
            inputEmail.setError(getString(R.string.err_msg_required));
            requestFocus(inputEmail);
            return false;
        }
        //signupInputLayoutEmail.setErrorEnabled(false);
        return true;
    }

    private boolean checkPassword() {

        String password = inputPassword.getText().toString().trim();
        if (password.isEmpty() || !isPasswordValid(password)) {

            //signupInputLayoutPassword.setError(getString(R.string.err_msg_password));
            inputPassword.setError(getString(R.string.err_msg_required));
            requestFocus(inputPassword);
            return false;
        }
        //signupInputLayoutPassword.setErrorEnabled(false);
        return true;
    }

    private static boolean isEmailValid(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private static boolean isPasswordValid(String password){
        return (password.length() >= 6);
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
}
