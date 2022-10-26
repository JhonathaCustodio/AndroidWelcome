package com.ibm.myfirstapp.data.remote.requests;

public class LoginUser {
    private String email;
    private String senha;

    public LoginUser(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }
}
