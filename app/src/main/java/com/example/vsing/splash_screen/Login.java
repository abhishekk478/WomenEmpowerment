package com.example.vsing.splash_screen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.vsing.splash_screen.R.*;

public class Login extends AppCompatActivity {

    private TextView mRegistertxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_login);


        mRegistertxt = (TextView) findViewById(id.Log_id);
        mRegistertxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);
            }
        });
    }
}
