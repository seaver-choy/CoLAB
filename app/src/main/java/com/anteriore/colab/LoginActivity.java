package com.anteriore.colab;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private EditText inputUsername;
    private EditText inputPassword;
    private Button loginButton;
    private FirebaseAuth auth;
    private TextView noAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();

        inputUsername = (EditText) findViewById(R.id.input_username);
        inputPassword = (EditText) findViewById(R.id.input_password);
        loginButton = (Button) findViewById(R.id.button_login);
        noAccount = (TextView) findViewById(R.id.no_account_text);

        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                submitForm();
            }
        });

        noAccount.setText((Html.fromHtml("No Account Yet? Click <u>Here</u> to Get One!")));
    }

    private void submitForm(){
        String email = inputUsername.getText().toString().trim();
        String password = inputPassword.getText().toString();

        if(!checkEmail())
        {
            return;
        }

        if(!checkPassword())
        {
            return;
        }

        //loginInputLayoutEmail.setErrorEnabled(false);
        //loginInputLayoutPassword.setErrorEnabled(false);

        //progressBar.setVisibility(View.VISIBLE);
        //authenticate user
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // If sign in fails, Log a message to the LogCat. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        //progressBar.setVisibility(View.GONE);
                        if (!task.isSuccessful()) {
                            // there was an error
                            Toast.makeText(LoginActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();

                        } else {
                            //Intent intent = new Intent(LoginActivity.this, UserActivity.class);
                            //startActivity(intent);
                            //finish();
                        }
                    }
                });
    }

    private boolean checkEmail() {
        String email = inputUsername.getText().toString().trim();
        if (email.isEmpty() || !isEmailValid(email)) {

            //inputUsername.setErrorEnabled(true);
            //inputUsername.setError(getString(R.string.err_msg_email));
            inputUsername.setError(getString(R.string.err_msg_required));
            requestFocus(inputUsername);
            return false;
        }
        //inputUsername.setErrorEnabled(false);
        return true;
    }

    private boolean checkPassword() {

        String password = inputPassword.getText().toString().trim();
        if (password.isEmpty() || !isPasswordValid(password)) {

            //loginInputLayoutPassword.setError(getString(R.string.err_msg_password));
            inputPassword.setError(getString(R.string.err_msg_required));
            requestFocus(inputPassword);
            return false;
        }
        //loginInputLayoutPassword.setErrorEnabled(false);
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
