package com.example.attyuttamsaha.facultyassigner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteSubject extends AppCompatActivity implements DeleteSubjectHelper.AsyncResponse {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_subject);
    }
    public void deleteSubject(View view) {
        String delete_subject_id = ((EditText)findViewById(R.id.delete_subject_id)).getText().toString();
        if(delete_subject_id.equals("") && !numberOrNot(delete_subject_id)){
            Toast.makeText(this,"FILL IN ALL DETAILS",Toast.LENGTH_LONG).show();
        }
        else{
            new DeleteSubjectHelper(delete_subject_id,this,this).execute();
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
    public void onDeleteSubjectHelper(Integer output) {
        long op = output;
        if(op >0){
            Toast.makeText(this, "SUBJECT DELETED SUCCESSFULLY",Toast.LENGTH_LONG).show();

        }
        else{
            Toast.makeText(this, "FAILED TO DELETE SUBJECT",Toast.LENGTH_LONG).show();
        }
    }
}
