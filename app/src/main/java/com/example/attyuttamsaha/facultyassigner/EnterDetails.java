package com.example.attyuttamsaha.facultyassigner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class EnterDetails extends AppCompatActivity implements DesignationSelectorClassHelper.AsyncResponse,EnterDetailsHelper.AsyncResponse {

    Spinner designation;
    String subject_rank="";
    String qualification="";
    final static int REQUEST_CODE_SUBJECT_SELECTOR = 1;
    final static int REQUEST_CODE_QUALIFICATION_SELECTOR = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_details);
        new DesignationSelectorClassHelper(this,this).execute();
    }
    static boolean numberOrNot(String input)
    {
        try
        {
            Integer.parseInt(input);
        }
        catch(NumberFormatException ex)
        {
            return false;
        }
        return true;
    }
    public void enterFacultyDetails(View view) {

        String f_name = ((EditText)findViewById(R.id.enter_name)).getText().toString();
        String f_age = (((EditText)findViewById(R.id.enter_age)).getText().toString());
        String years_of_experience = (((EditText)findViewById(R.id.enter_years_experience)).getText().toString());
        Spinner designation1=(Spinner)findViewById(R.id.enter_designation);
        String designation = "";
        if(designation1.getSelectedItem() == null||subject_rank.equals("") || qualification.equals("") || f_age.equals("") || f_name.equals("") || years_of_experience.equals("") ) {
            Toast.makeText(this, "FILL IN ALL THE DETAILS", Toast.LENGTH_SHORT).show();
        }
        else if(numberOrNot(f_name)){
            Toast.makeText(this,"FACULTY NAME MUST BE A STRING",Toast.LENGTH_LONG).show();
        }
        else{
            designation = designation1.getSelectedItem().toString();
            if(numberOrNot(f_age) && numberOrNot(years_of_experience) && Integer.parseInt(f_age)>=18 && Integer.parseInt(f_age)<=80)
                new EnterDetailsHelper(f_name,f_age,years_of_experience,subject_rank,qualification,designation,"",this,this).execute();
            else
                Toast.makeText(this, "ENTER VALID YEARS OF EXPERIENCE OR AGE", Toast.LENGTH_SHORT).show();

        }

    }

    public void subjectSelector(View view) {
        Intent intent = new Intent(this,SubjectSelectorClass.class);
        startActivityForResult(intent,REQUEST_CODE_SUBJECT_SELECTOR);
    }

    public void qualificationSelector(View view) {
        Intent intent = new Intent(this,QualificationSelectorClass.class);
        startActivityForResult(intent,REQUEST_CODE_QUALIFICATION_SELECTOR);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK && requestCode == REQUEST_CODE_SUBJECT_SELECTOR){
            subject_rank = data.getStringExtra("subject_rank_list");
            Log.d("subject rank",subject_rank);
        }
        if(resultCode == RESULT_OK && requestCode == REQUEST_CODE_QUALIFICATION_SELECTOR){
            qualification = data.getStringExtra("result");
            Log.e("q",qualification);
        }
    }

    @Override
    public void onDesignationSelectFinish(ArrayList<String> result) {
        //need to set the spinner with id enter_designation
        designation = findViewById(R.id.enter_designation);
        String[] items = new String[100];
        Log.e("yo",Integer.toString(result.size()));

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, android.R.id.text1);
        designation.setAdapter(spinnerAdapter);
        if(result.size()>0){

            for(int i=0;i<result.size();i++){
                Log.e("yo",result.get(i));
                String[] s = result.get(i).split(",");
                spinnerAdapter.add(s[1]);
            }
        }
        spinnerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDetailsEntered(Long output) {
        long op = output;
        if(op == -1){
            Toast.makeText(this, "FAILED TO INSERT DETAILS",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "NEW FACULTY ID IS: "+Long.toString(op),Toast.LENGTH_LONG).show();
            //once the faculty is created we need to apply the stable marriage algorithm
            FacultyAssigner fa = new FacultyAssigner(this);
            fa.applyAssignment();
        }
    }
}
