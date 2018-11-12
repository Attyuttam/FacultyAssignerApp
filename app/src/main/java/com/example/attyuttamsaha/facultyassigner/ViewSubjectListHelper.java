package com.example.attyuttamsaha.facultyassigner;

import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;

public class ViewSubjectListHelper extends AsyncTask<Void,Void,ArrayList<String>> {

    Context c;
    AsyncResponse delegate;

    ViewSubjectListHelper(Context c,AsyncResponse delegate){
        this.c = c;
        this.delegate = delegate;
    }
    public interface AsyncResponse{
        void onSubjectListReceieved(ArrayList<String> list);
    }
    @Override
    protected ArrayList<String> doInBackground(Void... voids) {

        ArrayList<String> result;
        DBHelper sDB = new DBHelper(c);
        result = sDB.loadHandler_all_SubjectDB();
        return result;
    }

    @Override
    protected void onPostExecute(ArrayList<String> list){
        delegate.onSubjectListReceieved(list);
    }
}

