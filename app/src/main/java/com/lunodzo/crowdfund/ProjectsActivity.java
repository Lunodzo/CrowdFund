package com.lunodzo.crowdfund;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class ProjectsActivity extends AppCompatActivity {

    CardView accessory;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);
    }

    public void cardClicked(View view){
        if (view.getId() == R.id.stationaries){
            Intent accesoriesIntent = new Intent(getApplicationContext(), ProjectDashboardActivity.class);
            startActivity(accesoriesIntent);
        }
        else if(view.getId() == R.id.automobile){
            Intent accesoriesIntent = new Intent(getApplicationContext(), ProjectDashboardActivity.class);
            startActivity(accesoriesIntent);
        }
        else if(view.getId() == R.id.restaurant){
            Intent accesoriesIntent = new Intent(getApplicationContext(), ProjectDashboardActivity.class);
            startActivity(accesoriesIntent);
        }

        else if(view.getId() == R.id.computers){
            Intent accesoriesIntent = new Intent(getApplicationContext(), ProjectDashboardActivity.class);
            startActivity(accesoriesIntent);
        }
        else if(view.getId() == R.id.maize){
            Intent accesoriesIntent = new Intent(getApplicationContext(), ProjectDashboardActivity.class);
            startActivity(accesoriesIntent);
        }
        else if(view.getId() == R.id.pork){
            Intent accesoriesIntent = new Intent(getApplicationContext(), ProjectDashboardActivity.class);
            startActivity(accesoriesIntent);
        }
        else if(view.getId() == R.id.mining){
            Intent accesoriesIntent = new Intent(getApplicationContext(), ProjectDashboardActivity.class);
            startActivity(accesoriesIntent);
        }
        else if (view.getId() == R.id.stationaries) {
            Intent accesoriesIntent = new Intent(getApplicationContext(), ProjectDashboardActivity.class);
            startActivity(accesoriesIntent);
        } else if (view.getId() == R.id.avocado) {
            Intent accesoriesIntent = new Intent(getApplicationContext(), ProjectDashboardActivity.class);
            startActivity(accesoriesIntent);
        } else if (view.getId() == R.id.computers65) {
            Intent accesoriesIntent = new Intent(getApplicationContext(), RegisterProjectActivity.class);
            startActivity(accesoriesIntent);
        }
    }

    public void floatingButton(View view) {
        Button myButton = findViewById(R.id.floating);
        myButton.setOnClickListener(view1 -> {
            Intent moveIntent = new Intent(getApplicationContext(), RegisterProjectActivity.class);
            //Toast.makeText(getApplicationContext(), "Coming soon", Toast.LENGTH_LONG).show();
            startActivity(moveIntent);
        });
    }
}

