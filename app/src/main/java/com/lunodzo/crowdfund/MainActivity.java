package com.lunodzo.crowdfund;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.btn_login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(getApplicationContext(), ProjectsActivity.class);
                startActivity(mainIntent);
            }
        });
    }

    public void newUser(View view) {
        if (view.getId() == R.id.newUser){
            Intent newIntent = new Intent(getApplicationContext(), RegisterUSerActivity.class);
            startActivity(newIntent);
        }
    }

    public void forgotPass(View view) {
        if(view.getId() == R.id.forgot_pass){
            Toast.makeText(getApplicationContext(), "Coming soon", Toast.LENGTH_LONG).show();
            //startActivity(passIntent);
        }
    }
}