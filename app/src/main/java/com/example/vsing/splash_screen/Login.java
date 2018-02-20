package com.example.vsing.splash_screen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.vsing.splash_screen.R.*;

public class Login extends AppCompatActivity {

     Button mRegistertxt;
     EditText Email,Password;
     TextView register;
     String email,password;
    List<NameValuePair> params;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mRegistertxt = (Button) findViewById(R.id.Login);
        Email =(EditText)findViewById(R.id.Email);
        register=(TextView)findViewById(R.id.Log_id);
        Password =(EditText)findViewById(R.id.Password);
        mRegistertxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = Email.getText().toString();
                password = Password.getText().toString();
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("email", email));
                params.add(new BasicNameValuePair("password", password));
                ServerRequest sr = new ServerRequest();
                JSONObject json = sr.getJSON("http://192.168.1.16:8080/authenticate",params);
                if(json != null){
                    try{
                        String jsonstr = json.getString("password");

                        Toast.makeText(getApplication(),jsonstr, Toast.LENGTH_LONG).show();

                        Log.d("Hello", jsonstr);
                        Intent intent = new Intent(Login.this, after_login.class);
                        startActivity(intent);
                    }catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,SignUp.class);
                startActivity(intent);
            }
        });
    }
}
