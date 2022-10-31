package com.ibm.myfirstapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import com.ibm.myfirstapp.data.Repository;
import com.ibm.myfirstapp.data.remote.UserResponse;
import com.ibm.myfirstapp.data.remote.requests.UserLogin;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

        private EditText etEmail, etPassword;
        String emailLogin, senhaLogin;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            etEmail = findViewById(R.id.etEmail);
            etPassword = findViewById(R.id.etPassword);
            Button botao = findViewById(R.id.botao);
            TextView txCadastro = findViewById(R.id.txCadastro);

            botao.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    emailLogin = etEmail.getText().toString();
                    senhaLogin = etPassword.getText().toString();

                    if(TextUtils.isEmpty(emailLogin) || TextUtils.isEmpty(senhaLogin)){
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("ATENÇÃO!")
                                .setMessage("Preencha todos os campos!")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                }).setIcon(android.R.drawable.ic_delete)
                                .show();
                    }else {
                        login(emailLogin, senhaLogin);
                    }
                }
            });

            txCadastro.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, CadastroActivity.class);
                    startActivity(intent);
                }
            });

        }
        public void login(String email, String password){

            Call<UserResponse> registerResponseCall = Repository.welcomeBoardService().loginUser(new UserLogin(email, password));

            registerResponseCall.enqueue(new Callback <UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    if(response.isSuccessful()){
                        if (emailLogin.equals(response.body().getEmail()) && senhaLogin.equals(response.body().getPassword())){
                            Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                            String name = response.body().getName();
                            intent.putExtra("name", name);
                            Toast.makeText(getApplicationContext(), "login realizado com sucesso!", Toast.LENGTH_SHORT).show();
                            startActivity(intent);

                            //mensagem de usuario nao existente
                        }
                    }else  {
                        dialog();
                    }
                }
                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Falha no cadastro!"+ t.getLocalizedMessage() + ";" + t.getCause(), Toast.LENGTH_SHORT).show();
                }
            });

        }

        private void dialog(){
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("ATENÇÃO!")
                    .setMessage("Realize seu cadastro!")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    }).setIcon(android.R.drawable.ic_delete)
                    .show();
        }
}