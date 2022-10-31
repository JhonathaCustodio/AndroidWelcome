package com.ibm.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //TextView tvEmail = findViewById(R.id.tvEmail);
        TextView tvNome = findViewById(R.id.tvNome);

        SharedPreferences pref = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String emailShared = pref.getString("email",null);
        String senhaShared = pref.getString("senha",null);
        String nomeShared = pref.getString("nome",null);

        tvNome.setText(nomeShared);
    }

}