package com.ibm.myfirstapp;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import com.ibm.myfirstapp.data.remote.requests.UserRequest;

public class MenuActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        TextView tvNome = findViewById(R.id.tvNome);

        String name = getIntent().getStringExtra("name");
        tvNome.setText(name);
    }
}