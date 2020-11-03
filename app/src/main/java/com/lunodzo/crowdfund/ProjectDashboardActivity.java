package com.lunodzo.crowdfund;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ProjectDashboardActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_dashboard);

        //Get Intent data
        Intent projectIntent = getIntent();


        //Call selected Image ID
        int position = projectIntent.getExtras().getInt("id");

        ImageView imageView = findViewById(R.id.icon);
        imageView.setImageResource(R.drawable.ic_project_24);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
