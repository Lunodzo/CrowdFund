package com.lunodzo.crowdfund;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.util.ArrayList;
import java.util.List;

public class ProjectsActivity extends AppCompatActivity {

    CardView accessory;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);

        //Create accessories element
        accessory = findViewById(R.id.accessories);
        accessory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(ProjectsActivity.this.ProjectDashboardActivity.class));
            }
        });

        //Create spinner element
        Spinner spinner = findViewById(R.id.spiner);

        //Spinner click listener
        spinner.setOnItemClickListener((AdapterView.OnItemClickListener) this);

        //Spinner dropdown list
        List<String> categories = new ArrayList<String>();
        categories.add("Agriculture");
        categories.add("Education");
        categories.add("Finance");
        categories.add("Mining & Minerals");
        categories.add("Electronics");
        categories.add("Construction");

        //create adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        //Dropdown layout style
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }

    //METHODS TO BE DEFINED LATER

//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        // On selecting a spinner item
//        String item = parent.getItemAtPosition(position).toString();
//
//        // Showing selected spinner item
//        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
//    }
//    public void onNothingSelected(AdapterView<?> arg0) {
//        TODO Auto-generated method stub
//    }
}
