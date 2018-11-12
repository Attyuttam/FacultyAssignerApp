package com.example.attyuttamsaha.facultyassigner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateDesignation extends AppCompatActivity implements UpdateDesignationHelper.AsyncResponse{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_designation);
    }

    public void updateDesignation(View view) {
        String designation_id = ((EditText)findViewById(R.id.designation_id)).getText().toString();
        String updated_designation_title = ((EditText)findViewById(R.id.update_designation_name)).getText().toString();
        String updated_credit_score = ((EditText)findViewById(R.id.updated_credit_score)).getText().toString();
        Log.d("values: ",designation_id+" "+updated_designation_title+" "+updated_credit_score);

        if(designation_id.equals("")||updated_designation_title.equals("") || updated_credit_score.equals("")){
            Toast.makeText(this,"FILL IN ALL DETAILS",Toast.LENGTH_LONG).show();
        }
        else if(numberOrNot(updated_designation_title)){
            Toast.makeText(this,"DESIGNATION TITLE MUST BE A STRING",Toast.LENGTH_LONG).show();
        }
        else if(!numberOrNot(designation_id) || !numberOrNot(updated_credit_score)){
            Toast.makeText(this,"DESIGNATION ID AND CREDIT SCORE MUST BE A NUMBER",Toast.LENGTH_LONG).show();
        }
        else if(Integer.parseInt(updated_credit_score)<0 || Integer.parseInt(updated_credit_score)>5){
            Toast.makeText(this,"CREDIT MUST BE WITHIN 0 TO 5 BOTH INCLUDED",Toast.LENGTH_LONG).show();
        }
        else{
            new UpdateDesignationHelper(designation_id,updated_designation_title,updated_credit_score,this,this).execute();
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
    public void onUpdateFinished(Long output) {
        long op = output;
        if(op == -1){
            Toast.makeText(this, "FAILED TO UPDATE DESIGNATION",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "DESIGNATION UPDATED SUCCESSFULLY",Toast.LENGTH_LONG).show();
        }
    }
}
