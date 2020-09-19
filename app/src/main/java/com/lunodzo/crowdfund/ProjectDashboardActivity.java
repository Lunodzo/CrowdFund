package com.lunodzo.crowdfund;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ProjectDashboardActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_dashboard);

        Button button = findViewById(R.id.invest);
        button.setOnClickListener(view -> {
            Intent moveIntent = new Intent(getApplicationContext(), ProjectsActivity.class);
            Toast.makeText(getApplicationContext(), "Coming soon", Toast.LENGTH_LONG).show();
            startActivity(moveIntent);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
