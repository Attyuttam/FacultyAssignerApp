package com.example.attyuttamsaha.facultyassigner;

import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;

class ViewAssignedListHelper extends AsyncTask<Void,Void,ArrayList<String>>{

    Context c;
    AsyncResponse delegate;
    public ViewAssignedListHelper(Context c, AsyncResponse delegate) {
        this.c = c;
        this.delegate = delegate;
    }
    public interface AsyncResponse{
        void OnAssignedListReturned(ArrayList<String> result);
    }
    @Override
    protected ArrayList<String> doInBackground(Void... voids) {
        ArrayList<String> result;
        DBHelper fDB = new DBHelper(c);
        result = fDB.loadHandler_all_FacultyDB();
        return result;
    }
    @Override
    protected void onPostExecute(ArrayList<String> result){
        delegate.OnAssignedListReturned(result);
    }
}
