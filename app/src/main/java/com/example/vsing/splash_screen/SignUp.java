package com.example.vsing.splash_screen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SignUp extends AppCompatActivity {
      EditText Name,Email,Password,Address,Phone,Qualification,Skills,Experience;
     Button mSignupBtn;
     String email,name,password,address,qualification,skills,experience,phone;
     List<NameValuePair> params;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Name =(EditText)findViewById(R.id.Name);
        Email =(EditText)findViewById(R.id.Email);
        Password=(EditText)findViewById(R.id.Password);
        Address=(EditText)findViewById(R.id.Address);
        Phone=(EditText)findViewById(R.id.Mobile);
        Qualification=(EditText)findViewById(R.id.Qualification);
        Skills=(EditText)findViewById(R.id.Skills);
        Experience=(EditText)findViewById(R.id.Experience);
        mSignupBtn = (Button)findViewById(R.id.sign_up_btn);
        mSignupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = Name.getText().toString();
                email = Email.getText().toString();
                password = Password.getText().toString();
                address = Address.getText().toString();
                phone = Phone.getText().toString();
                qualification = Qualification.getText().toString();
                skills = Skills.getText().toString();
                experience = Experience.getText().toString();


                 params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("name", name));
                params.add(new BasicNameValuePair("email", email));
                params.add(new BasicNameValuePair("password", password));
                params.add(new BasicNameValuePair("address",address));
                params.add(new BasicNameValuePair("mobile",phone));
                params.add(new BasicNameValuePair("Qualification",qualification));
                params.add(new BasicNameValuePair("skills",skills));
                params.add(new BasicNameValuePair("experience",experience));

                ServerRequest sr = new ServerRequest();
                JSONObject json = sr.getJSON("http://192.168.1.16:8080/Register",params);
                if(json != null){
                    try{
                       String jsonstr = json.getString("password");

                        Toast.makeText(getApplication(),jsonstr, Toast.LENGTH_LONG).show();

                        Log.d("Hello", jsonstr);
                        Intent intent = new Intent(SignUp.this, after_login.class);
                        startActivity(intent);
                    }catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

    }


}
