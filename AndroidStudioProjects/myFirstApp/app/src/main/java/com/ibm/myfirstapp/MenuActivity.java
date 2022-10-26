package com.ibm.myfirstapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        TextView tvNome = findViewById(R.id.tvNome);

        SharedPreferences pref = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String nomeShared = pref.getString("nome",null);
        tvNome.setText(nomeShared);
    }

}