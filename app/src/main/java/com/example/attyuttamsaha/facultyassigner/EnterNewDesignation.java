package com.example.attyuttamsaha.facultyassigner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EnterNewDesignation extends AppCompatActivity implements EnterNewDesignationHelper.AsyncResponse{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_new_designation);
    }

    public void enterDesignation(View view) {
        String designation_title = ((EditText)findViewById(R.id.designation_title)).getText().toString();
        String credit = ((EditText)findViewById(R.id.credit)).getText().toString();
        if(designation_title.equals("") || credit.equals("")){
            Toast.makeText(this,"FILL IN ALL DETAILS",Toast.LENGTH_LONG).show();
        }
        else if(!numberOrNot(credit)){
            Toast.makeText(this,"CREDIT MUST BE A NUMBER",Toast.LENGTH_LONG).show();
        }
        else if(numberOrNot(designation_title)){
            Toast.makeText(this,"DESIGNATION TITLE MUST BE A STRING",Toast.LENGTH_LONG).show();
        }
        else if(Integer.parseInt(credit)<0 || Integer.parseInt(credit)>5){
            Toast.makeText(this,"CREDIT MUST BE WITHIN 0 TO 5 BOTH INCLUDED",Toast.LENGTH_LONG).show();
        }
        else{
            new EnterNewDesignationHelper(designation_title,credit,this,this).execute();
        }

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
    public void onEnterNewDesignationFinish(Long output){
        long op = output;
        if(op == -1){
            Toast.makeText(this, "FAILED TO INSERT NEW DESIGNATION",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "NEW DESIGNATION ID IS: "+Long.toString(op),Toast.LENGTH_LONG).show();
        }
    }
}
