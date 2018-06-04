package com.example.vsing.splash_screen;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.AsyncTask;
import android.content.Intent;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SignUp extends AppCompatActivity {
    private ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser();
    private static String url_registration = "http://193.168.0.113:3306/WomwnEmpowerment/registration.php";
    private static final String TAG_SUCCESS = "success";

    EditText Name, Email, Password, Address, State, Pin, Std, Phone, Qualification, Skills, Experience;
    Button mSignupBtn;
    String email, name, pass, address, state, pin, std, qualification, skills, experience, phone;
    List<NameValuePair> params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Name = (EditText) findViewById(R.id.Name);
        Email = (EditText) findViewById(R.id.Email);
        Password = (EditText) findViewById(R.id.Password);
        Address = (EditText) findViewById(R.id.Address);
        State = (EditText) findViewById(R.id.State);
        Pin = (EditText) findViewById(R.id.Pin);
        Std = (EditText) findViewById(R.id.Std);
        Phone = (EditText) findViewById(R.id.Mobile);
        Qualification = (EditText) findViewById(R.id.Qualification);
        Skills = (EditText) findViewById(R.id.Skills);
        Experience = (EditText) findViewById(R.id.Experience);
        mSignupBtn = (Button) findViewById(R.id.sign_up_btn);
        mSignupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // creating new registration in background thread
                new Registration().execute();
            }
        });
    }

    class Registration extends AsyncTask<String, String, String> {
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(SignUp.this);
            pDialog.setMessage("Registering..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        protected String doInBackground(String... args) {
            name = Name.getText().toString();
            email = Email.getText().toString();
            pass = Password.getText().toString();
            address = Address.getText().toString();
            state = State.getText().toString();
            pin = Pin.getText().toString();
            std = Std.getText().toString();
            phone = Phone.getText().toString();
            qualification = Qualification.getText().toString();
            skills = Skills.getText().toString();
            experience = Experience.getText().toString();

            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("name", name));
            params.add(new BasicNameValuePair("email", email));
            params.add(new BasicNameValuePair("pass", pass));
            params.add(new BasicNameValuePair("address", address));
            params.add(new BasicNameValuePair("state", state));
            params.add(new BasicNameValuePair("pin", pin));
            params.add(new BasicNameValuePair("std", std));
            params.add(new BasicNameValuePair("mobile", phone));
            params.add(new BasicNameValuePair("qualification", qualification));
            params.add(new BasicNameValuePair("skills", skills));
            params.add(new BasicNameValuePair("experience", experience));

            JSONObject json = jsonParser.makeHttpRequest(url_registration, "POST", params);
            Log.d("Create Response", json.toString());
            try {
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // successfully created product

                    // closing this screen
                    finish();
                } else {
                    // failed to create product
                    finish();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            pDialog.dismiss();
        }
    }
}