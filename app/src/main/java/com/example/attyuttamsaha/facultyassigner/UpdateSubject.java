package com.example.attyuttamsaha.facultyassigner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class UpdateSubject extends AppCompatActivity implements UpdateSubjectHelper.AsyncResponse {

    Spinner courseDropdown;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_subject);
        String[] items = new String[]{"MCA","B.Tech","M.SC","M.Tech"};
        courseDropdown = findViewById(R.id.updated_subject_course);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        courseDropdown.setAdapter(adapter2);
    }
    public void updateSubject(View view) {
        String subject_id = ((EditText)findViewById(R.id.updated_subject_id)).getText().toString();
        String updated_subject_name = ((EditText)findViewById(R.id.updated_subject_name)).getText().toString();
        String updated_credit_score = ((EditText)findViewById(R.id.updated_credit_score)).getText().toString();
        Spinner cur2=(Spinner)findViewById(R.id.updated_subject_course);
        String subject_course = cur2.getSelectedItem().toString();


        if(subject_id.equals("")||updated_subject_name.equals("") || updated_credit_score.equals("") || subject_course.equals("")){
            Toast.makeText(this,"FILL IN ALL DETAILS",Toast.LENGTH_LONG).show();
        }
        else if(numberOrNot(updated_subject_name) || numberOrNot(subject_course)){
            Toast.makeText(this,"SUBJECT NAME AND SUBJECT COURSE MUST BE A STRING",Toast.LENGTH_LONG).show();
        }
        else if(!numberOrNot(subject_id) || !numberOrNot(updated_credit_score)){
            Toast.makeText(this,"SUBJECT ID AND CREDIT SCORE MUST BE A NUMBER",Toast.LENGTH_LONG).show();
        }
        else if(Integer.parseInt(updated_credit_score)<0 || Integer.parseInt(updated_credit_score)>5){
            Toast.makeText(this,"CREDIT MUST BE WITHIN 0 TO 5 BOTH INCLUDED",Toast.LENGTH_LONG).show();
        }
        else{
            new UpdateSubjectHelper(subject_id,updated_subject_name,updated_credit_score,subject_course,this,this).execute();
        }
        //Log.d("values: ",designation_id+" "+updated_designation_title+" "+updated_credit_score);

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
    @Override
    public void onUpdateFinished(Long output) {
        long op = output;
        if(op == -1){
            Toast.makeText(this, "FAILED TO UPDATE SUBJECT",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "SUBJECT UPDATED SUCCESSFULLY",Toast.LENGTH_LONG).show();
        }
    }
}
