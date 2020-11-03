package com.lunodzo.crowdfund;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {
    String nationalID, firstName, lastName, phoneNumber, emailAddress, address;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText nationalIdentity = findViewById(R.id.nationalID);
        EditText fName = findViewById(R.id.inputfirstName);
        EditText lName = findViewById(R.id.inputLastName);
        EditText phone = findViewById(R.id.inputPhone);
        EditText email = findViewById(R.id.inputEmail);
        EditText physicalAddress = findViewById(R.id.address);
        Button btnSubmit = findViewById(R.id.btn_register);

        btnSubmit.setOnClickListener(v -> {
            nationalID = nationalIdentity.getText().toString();
            firstName = fName.getText().toString();
            lastName = lName.getText().toString();
            phoneNumber = phone.getText().toString();
            emailAddress = email.getText().toString();
            address = physicalAddress.getText().toString();

            if (firstName.isEmpty() || lastName.isEmpty() || phoneNumber.isEmpty() || emailAddress.isEmpty() || nationalID.isEmpty() || address.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Fill in all Fields", Toast.LENGTH_SHORT).show();
            } else {
                //Execute Async class
                if (haveNetworkConnection()) {
                    new RegisterUser().execute();
                } else {
                    Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //Method to check internet connection
    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();

        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

    //Create inner class Async to upload data
    class RegisterUser extends AsyncTask<String, String, String> {
        //Create progress dialog
        ProgressDialog pDialog;
        String php_response;

        //Initiate the three methods

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(RegisterActivity.this);
            pDialog.setMessage("Registering User");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                /* setting up the connection and send data with url */
                // create a http default client - initialize the HTTp client

                DefaultHttpClient httpclient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost("http://twiga2.com/crowd_fund/register_user.php");

                ArrayList<NameValuePair> nameValuePairs = new ArrayList<>(3);

                nameValuePairs.add(new BasicNameValuePair("nationalID", nationalID));
                nameValuePairs.add(new BasicNameValuePair("firstName", firstName));
                nameValuePairs.add(new BasicNameValuePair("lastName", lastName));
                nameValuePairs.add(new BasicNameValuePair("phoneNumber", phoneNumber));
                nameValuePairs.add(new BasicNameValuePair("emailAddress", emailAddress));
                nameValuePairs.add(new BasicNameValuePair("address", address));

                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                HttpResponse response = httpclient.execute(httpPost);

                InputStream inputStream = response.getEntity().getContent();

                BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream), 4096);
                String line;
                StringBuilder sb = new StringBuilder();
                while ((line = rd.readLine()) != null) {
                    sb.append(line);
                }
                rd.close();
                php_response = sb.toString();
                inputStream.close();
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Try Again", Toast.LENGTH_SHORT).show();
            }
            return php_response;
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pDialog.dismiss();

            if (php_response.equals("1")) {
                Toast.makeText(RegisterActivity.this, "User registered", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), ProjectsActivity.class));
            } else {
                Toast.makeText(RegisterActivity.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
