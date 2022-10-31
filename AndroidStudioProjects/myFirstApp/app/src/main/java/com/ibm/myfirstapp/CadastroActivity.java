package com.ibm.myfirstapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.ibm.myfirstapp.data.Repository;
import com.ibm.myfirstapp.data.remote.UserResponse;
import com.ibm.myfirstapp.data.remote.requests.UserRequest;

import retrofit2.Call;
import retrofit2.Callback;

public class CadastroActivity extends AppCompatActivity {

        EditText etEmail, etSenha, etSenhaConfirma, etNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        etEmail = findViewById(R.id.editTextEmail);
        etSenha = findViewById(R.id.editTextSenha);
        etSenhaConfirma = findViewById(R.id.editTextSenhaConfirma);
        etNome = findViewById(R.id.editTextNome);

        etEmail.setText(getIntent().getStringExtra("KeyEmail"));
        etSenha.setText(getIntent().getStringExtra("KeySenha"));
        etNome.setText(getIntent().getStringExtra("KeyNome"));


        Button btnCadastrar = findViewById(R.id.btnCadastrar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String nome = etNome.getText().toString();
                String email = etEmail.getText().toString();
                String senha = etSenha.getText().toString();
                String senhaConfirma = etSenhaConfirma.getText().toString();


                if(nome.isEmpty()){
                    Toast.makeText(getApplicationContext(),
                            "Digite seu nome",
                            Toast.LENGTH_LONG).show();
                }else if ( senha.equals(senhaConfirma)){
                    saveUser(createRequest());
                    finish();

                }else{
                    //erro senhas divergentes

                    alerta("Confirme sua senha");
                }

            }
        });
    }

    public UserRequest createRequest(){
        UserRequest request = new UserRequest();

        request.setName(etNome.getText().toString());
        request.setEmail(etEmail.getText().toString());
        request.setPassword(etSenha.getText().toString());

        return request;
    }

    public void saveUser(UserRequest request){

        Call<UserResponse> responseCall = Repository.welcomeBoardService().saveUser(request);
        responseCall.enqueue(new Callback<UserResponse>() {

            @Override
            public void onResponse(Call<UserResponse> call, retrofit2.Response<UserResponse> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "email cadastrado", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "falha no cadastro", Toast.LENGTH_SHORT).show();

            }
        });

    }



    private void alerta(String msg){

        new android.app.AlertDialog.Builder(CadastroActivity.this)
                .setTitle("ATENÇÃO")
                .setMessage(msg)

                .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int with) {

                    }
                })

                .setIcon(android.R.drawable.ic_delete)
                .show();
    }
}