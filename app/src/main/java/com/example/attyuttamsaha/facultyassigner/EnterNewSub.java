package com.example.attyuttamsaha.facultyassigner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class EnterNewSub extends AppCompatActivity implements EnterNewSubHelper.AsyncResponse{

    Spinner dropdown1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_new_sub);

        String[] items = new String[]{"MCA","B.Tech","M.SC","M.Tech"};
        dropdown1 = findViewById(R.id.subject_course);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown1.setAdapter(adapter2);
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
    public void enterNewSubject(View view) {
        EditText sn = (EditText)findViewById(R.id.subject_name);
        String subject_name = sn.getText().toString();

        EditText scs = (EditText)findViewById(R.id.subject_credit_score);
        String subject_credit_score = scs.getText().toString();

        Spinner cur2=(Spinner)findViewById(R.id.subject_course);
        String subject_course = cur2.getSelectedItem().toString();

        if(subject_name.equals("") || subject_credit_score.equals("") || subject_course.equals("")){
            Toast.makeText(this,"FILL IN ALL DETAILS",Toast.LENGTH_LONG).show();
        }
        else if(numberOrNot(subject_name) || numberOrNot(subject_course)){
            Toast.makeText(this,"SUBJECT NAME AND SUBJECT COURSE MUST BE A STRING",Toast.LENGTH_LONG).show();
        }
        else if(!numberOrNot(subject_credit_score)){
            Toast.makeText(this,"CREDIT SCORE MUST BE A NUMBER",Toast.LENGTH_LONG).show();
        }
        else if(Integer.parseInt(subject_credit_score)<0 || Integer.parseInt(subject_credit_score)>5){
            Toast.makeText(this,"CREDIT MUST BE WITHIN 0 TO 5 BOTH INCLUDED",Toast.LENGTH_LONG).show();
        }
        else{
            new EnterNewSubHelper(subject_name,subject_credit_score,subject_course,this,this).execute();
        }

    }
    @Override
    public void onEnterNewSubFinish(Long output){
        long op = output;
        if(op == -1){
            Toast.makeText(this, "FAILED TO INSERT NEW SUBJECT",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "NEW SUBJECT ID IS: "+Long.toString(op),Toast.LENGTH_LONG).show();
        }
    }
}
