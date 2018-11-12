package com.example.attyuttamsaha.facultyassigner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FacultyPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_page);
    }

    public void facultyRedirector(View view) {
        Intent intent = null;
        switch (view.getId()){
            case R.id.enter_details_button:
                intent = new Intent(this,EnterDetails.class);
                break;
            case R.id.view_assigned_subject_button:
                intent = new Intent(this,ViewAssignedSubject.class);
                break;
        }
        startActivity(intent);

    }
}
