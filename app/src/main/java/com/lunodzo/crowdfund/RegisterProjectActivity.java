package com.lunodzo.crowdfund;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
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

public class RegisterProjectActivity extends AppCompatActivity {
    String projectName;
    String projectSlogan;
    String minDeposit;
    String dateEstablished;
    String projectDescription;
    String projectLocation;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_project);

        EditText pName = findViewById(R.id.projectName);
        EditText pSlogan = findViewById(R.id.slogan);
        EditText minDep = findViewById(R.id.minDeposit);
        EditText dEstablished = findViewById(R.id.dateEstablished);
        EditText pDescription = findViewById(R.id.projectDesc);
        EditText pLocation = findViewById(R.id.projectLocation);
        Button btnSubmit = findViewById(R.id.btnAdd);

        //Date Dialog
        dEstablished.setOnClickListener(v -> {
            final Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR); // current year
            int mMonth = c.get(Calendar.MONTH); // current month
            int mDay = c.get(Calendar.DAY_OF_MONTH); // current day

            DatePickerDialog datePickerDialog = new DatePickerDialog(RegisterProjectActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    dEstablished.setText(year + "/"
                            + (month + 1) + "/" + dayOfMonth);
                }
            }, mYear, mMonth, mDay);
            datePickerDialog.show();
        });

        btnSubmit.setOnClickListener(v -> {
            projectName = pName.getText().toString();
            projectSlogan = pSlogan.getText().toString();
            minDeposit = minDep.getText().toString();
            dateEstablished = dEstablished.getText().toString();
            projectDescription = pDescription.getText().toString();
            projectLocation = pLocation.getText().toString();

            //Execute Async class
            if (haveNetworkConnection()) {
                new RegisterProject().execute();
            } else {
                Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
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
    class RegisterProject extends AsyncTask<String, String, String> {
        //Create progress dialog
        ProgressDialog pDialog;
        String php_response;

        //Initiate the three methods

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(RegisterProjectActivity.this);
            pDialog.setMessage("Registering Project");
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
                HttpPost httpPost = new HttpPost("http://twiga2.com/crowd_fund/register_project.php");

                ArrayList<NameValuePair> nameValuePairs = new ArrayList<>(3);

                nameValuePairs.add(new BasicNameValuePair("projectName", projectName));
                nameValuePairs.add(new BasicNameValuePair("projectSlogan", projectSlogan));
                nameValuePairs.add(new BasicNameValuePair("minDeposit", minDeposit));
                nameValuePairs.add(new BasicNameValuePair("dateEstablished", dateEstablished));
                nameValuePairs.add(new BasicNameValuePair("projectDescription", projectDescription));
                nameValuePairs.add(new BasicNameValuePair("projectLocation", projectLocation));

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
                Toast.makeText(RegisterProjectActivity.this, "Project registered", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), ProjectsActivity.class));
            } else {
                Toast.makeText(RegisterProjectActivity.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
