package com.example.attyuttamsaha.facultyassigner;

import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;

class FacultyDetailsLoadHelper extends AsyncTask<Void,Void,ArrayList<String>> {
    Context c;
    AsyncResponse delegate;
    FacultyDetailsLoadHelper(Context c, AsyncResponse delegate){
        this.c = c;
        this.delegate = delegate;
    }
    public interface AsyncResponse{
        void onFacultyDetailsLoad(ArrayList<String> output);
    }
    @Override
    protected ArrayList<String> doInBackground(Void... voids) {
        DBHelper fDB = new DBHelper(c);
        return fDB.loadHandler_all_FacultyDB();
    }
    @Override
    protected void onPostExecute(ArrayList<String> list){
        delegate.onFacultyDetailsLoad(list);
    }
}
