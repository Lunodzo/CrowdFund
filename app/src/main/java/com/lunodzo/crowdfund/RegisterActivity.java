package com.lunodzo.crowdfund;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button button = findViewById(R.id.btn_register);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(getApplicationContext(), ProjectsActivity.class);
                Toast.makeText(getApplicationContext(), "User registered", Toast.LENGTH_LONG).show();
                startActivity(mainIntent);
            }
        });
    }

    public void signIn(View view) {
        Intent kuuIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(kuuIntent);
    }
}
