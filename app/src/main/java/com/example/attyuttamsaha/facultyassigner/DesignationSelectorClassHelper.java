package com.example.attyuttamsaha.facultyassigner;

import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;

public class DesignationSelectorClassHelper extends AsyncTask<Void,Void,ArrayList<String>> {

    private Context c;
    private AsyncResponse delegate;
    private String subject_rank_list;
    DesignationSelectorClassHelper(Context c, AsyncResponse delegate){
        this.c = c;
        this.delegate = delegate;
        subject_rank_list="";
    }

    @Override
    protected ArrayList<String> doInBackground(Void... voids) {

        ArrayList<String> result;
        DBHelper sDB = new DBHelper(c);
        result = sDB.loadHandler_all_DesignationDB();
        return result;
    }

    public interface AsyncResponse{
        void onDesignationSelectFinish(ArrayList<String> result);
    }

    @Override
    protected void onPostExecute(ArrayList<String> result){
        delegate.onDesignationSelectFinish(result);
    }
}
