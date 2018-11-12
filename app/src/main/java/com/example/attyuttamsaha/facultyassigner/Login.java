package com.example.attyuttamsaha.facultyassigner;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

//we will check for login as an async task
class Login implements LoginHelper.AsyncResponse,SignupHelper.AsyncResponse {

    private String username,password,admin;
    private Boolean result;
    private Context c;


    public Login(String username, String password, String admin, Context c){
        this.username = username;
        this.password = password;
        this.admin = admin;
        this.result = true;
        this.c = c;
    }
    public void checkUser() {
        new LoginHelper(username,password,admin,c,this).execute();
    }
    public void insertUser() {
        new SignupHelper(username,password,admin,c,this).execute();
    }

    @Override
    public void processLoginFinish(Boolean output) {
        result = output;
        Log.e("result",result.toString());
        if(result){
            Intent intent;
            if(admin.equals("F"))
                intent= new Intent(c,FacultyPage.class);
            else
                intent = new Intent(c,AdminPage.class);
            intent.putExtra("Message","WELCOME "+username);
            c.startActivity(intent);
        }
        else{
            Toast.makeText(c,"INVALID CREDENTIALS",Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void processSignupFinish(Boolean output) {
        if(output){
            Toast.makeText(c,"SUCCESSFULLY SIGNED UP",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(c,"FAILED TO SIGN UP OR ALREADY SIGNED UP",Toast.LENGTH_LONG).show();
        }
    }
}

