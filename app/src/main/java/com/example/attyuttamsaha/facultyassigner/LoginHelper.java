package com.example.attyuttamsaha.facultyassigner;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;


class LoginHelper extends AsyncTask<String,Void,Boolean>{
    private String username,password,admin;
    private DBHelper uDB;
    private AsyncResponse delegate = null;
    Context c;

    LoginHelper(String username, String password, String admin, Context c, AsyncResponse delegate) {
        this.password = password;
        this.username = username;
        this.admin = admin;
        this.c = c;
        this.delegate = delegate;
    }
    public interface AsyncResponse {
        void processLoginFinish(Boolean output);
    }

    @Override
    protected Boolean doInBackground(String... strings) {
        uDB = new DBHelper(c);
        Log.e("the object",uDB.toString());
        ArrayList<String> result = uDB.loadHandler_UserDB(username, password,admin);
        return result.size() > 0;
    }

    @Override
    protected void onPostExecute(Boolean bool_result){
        delegate.processLoginFinish(bool_result);
    }
}


