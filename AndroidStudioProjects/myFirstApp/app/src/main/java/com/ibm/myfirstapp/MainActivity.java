package com.ibm.myfirstapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
{

    private String email, senha, nome;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText etEmail, etPassword;
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);


        Button botao = findViewById(R.id.botao);
        botao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                email = etEmail.getText().toString();
                senha = etPassword.getText().toString();


                SharedPreferences pref = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                String emailShared = pref.getString("email",null);
                String senhaShared = pref.getString("senha",null);


                if(email.isEmpty() || senha.isEmpty()){
                    Toast.makeText(getApplicationContext(),
                            "Entre com um email e senha de sua preferencia para o cadastro",
                            Toast.LENGTH_LONG).show();
                } else if (email.equals(emailShared) && senha.equals(senhaShared)) {

                    Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                    intent.putExtra("KeyEmail", email);
                    intent.putExtra("KeySenha", senha);
                    intent.putExtra("KeyNome", nome);
                    startActivity(intent);

                } else {
                    dialog();
                }
            }

            private void dialog() {
                new android.app.AlertDialog.Builder(MainActivity.this)
                        .setTitle("ATENÇÃO")
                        .setMessage("Você não possui cadastro, deseja se cadastrar?")

                        .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int with) {

                                Intent intent = new Intent(MainActivity.this, CadastroActivity.class);
                                intent.putExtra("KeyEmail", email);
                                intent.putExtra("KeySenha", senha);
                                intent.putExtra("KeyNome", nome);
                                startActivity(intent);
                            }
                        })

                .setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int with) {

                        Toast.makeText(getApplicationContext(),
                                "Acesso Bloqueado",
                                Toast.LENGTH_LONG).show();
                    }
                })
                .setIcon(android.R.drawable.ic_delete)
                .show();
            }
        });
    }
}