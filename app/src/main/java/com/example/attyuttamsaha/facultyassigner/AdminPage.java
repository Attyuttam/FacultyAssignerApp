package com.example.attyuttamsaha.facultyassigner;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminPage extends AppCompatActivity {

    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }
    public void adminRedirector(View view) {
        Intent intent = null;
        switch (view.getId()){
            case R.id.enter_new_sub:
                intent = new Intent(getApplicationContext(),EnterNewSub.class);
                startActivity(intent);
                break;
            case R.id.view_assigned_list:
                intent = new Intent(getApplicationContext(),ViewAssignedList.class);
                startActivity(intent);
                break;
            case R.id.view_subject_list:
                intent = new Intent(getApplicationContext(),ViewSubjectList.class);
                startActivity(intent);
                break;
            case R.id.enter_new_designation:
                intent = new Intent(getApplicationContext(),EnterNewDesignation.class);
                startActivity(intent);
                break;
            case R.id.update_designation:
                intent = new Intent(getApplicationContext(),UpdateDesignation.class);
                startActivity(intent);
                break;
            case R.id.update_sub:
                intent = new Intent(getApplicationContext(),UpdateSubject.class);
                startActivity(intent);
                break;
            case R.id.delete_designation:
                intent = new Intent(getApplicationContext(),DeleteDesignation.class);
                startActivity(intent);
                break;
            case R.id.delete_sub:
                intent = new Intent(getApplicationContext(),DeleteSubject.class);
                startActivity(intent);
                break;
            case R.id.enter_new_admin:
                dialog = new Dialog(this);
                dialog.setContentView(R.layout.signup_dialog);
                dialog.show();
                final Context c = this;
                Button signup_button = (Button) dialog.findViewById(R.id.signup_button_dialog);
                signup_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText ut = (EditText) dialog.findViewById(R.id.enter_name_dialog);
                        EditText pt = (EditText) dialog.findViewById(R.id.enter_password_dialog);
                        final String username = ut.getText().toString();
                        final String password = pt.getText().toString();
                        if(username.equals("") || password.equals("")){
                            Toast.makeText(c,"FILL IN ALL DETAILS",Toast.LENGTH_LONG).show();
                        }
                        else {
                            Login lc = new Login(username, password, "T", c);//checking for the authenticity of the user
                            lc.insertUser();
                        }
                    }
                });
                break;

        }

    }
}
