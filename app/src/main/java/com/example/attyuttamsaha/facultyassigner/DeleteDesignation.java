package com.example.attyuttamsaha.facultyassigner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteDesignation extends AppCompatActivity implements DeleteDesignationHelper.AsyncResponse {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_designation);
    }

    public void deleteDesignation(View view) {
        String delete_designation_id = ((EditText)findViewById(R.id.delete_designation_id)).getText().toString();

        if(delete_designation_id.equals("") && !numberOrNot(delete_designation_id)){
            Toast.makeText(this,"FILL IN ALL DETAILS",Toast.LENGTH_LONG).show();
        }
        else {
            new DeleteDesignationHelper(delete_designation_id,this,this).execute();
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
    public void onDeleteDesignationHelper(Integer output) {
        long op = output;
        if(op >0){
            Toast.makeText(this, "DESIGNATION DELETED SUCCESSFULLY",Toast.LENGTH_LONG).show();

        }
        else{
            Toast.makeText(this, "FAILED TO DELETE DESIGNATION",Toast.LENGTH_LONG).show();
        }
    }
}
