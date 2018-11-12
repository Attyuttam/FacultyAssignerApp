package com.example.attyuttamsaha.facultyassigner;

import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;

class ViewAssignedSubjectHelper extends AsyncTask<String,Void,String> {
    String f_id;
    Context c;
    AsyncResponse delegate;
    public ViewAssignedSubjectHelper(String f_id, Context c, AsyncResponse delegate) {

        this.f_id = f_id;
        this.c = c;
        this.delegate = delegate;
    }
    public interface AsyncResponse{
        void OnAssignedSubjectReceived(String result);
    }
    @Override
    protected String doInBackground(String... strings) {
        String res = "";
        DBHelper fDB = new DBHelper(c);
        ArrayList<String> s = fDB.loadHandler_FacultyDB(this.f_id);
        if(s.size()>0)
            res = s.get(0);
        return  res;
    }
    @Override
    protected void onPostExecute(String res){
        delegate.OnAssignedSubjectReceived(res);
    }
}
