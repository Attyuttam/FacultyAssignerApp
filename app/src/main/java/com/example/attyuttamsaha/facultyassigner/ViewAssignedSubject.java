package com.example.attyuttamsaha.facultyassigner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewAssignedSubject extends AppCompatActivity implements ViewAssignedSubjectHelper.AsyncResponse{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_assigned_subject);

    }

    public void findAssignedSubject(View view) {
        //TODO: from the assigned list database select the row that confines to the faculty's name and unique id
        String f_id = ((EditText)findViewById(R.id.faculty_id)).getText().toString();
        if( f_id.equals("")){
            Toast.makeText(this,"FILL ALL DETAILS",Toast.LENGTH_LONG).show();
        }
        else if(!numberOrNot(f_id)){
            Toast.makeText(this,"FACULTY ID MUST BE A NUMBER",Toast.LENGTH_LONG).show();
        }
        else{
            new ViewAssignedSubjectHelper(f_id,this,this).execute();
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
    public void OnAssignedSubjectReceived(String result) {
        if(result.equals("")){
            Toast.makeText(this,"NO SUBJECT ASSIGNED YET",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this,"SUBJECT ASSIGNED "+result,Toast.LENGTH_LONG).show();
        }
    }
}
