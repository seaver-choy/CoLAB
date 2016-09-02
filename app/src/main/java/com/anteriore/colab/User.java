package com.anteriore.colab;

/**
 * Created by Seaver on 9/3/2016.
 */
public class User {

    private String firstName;
    private String lastName;
    private String password;
    private String username;
    private String birthday;
    private String email;

    public User(String firstName, String lastName, String password, String username, String birthday, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.username = username;
        this.birthday = birthday;
        this.email = email;
    }

    public User() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
