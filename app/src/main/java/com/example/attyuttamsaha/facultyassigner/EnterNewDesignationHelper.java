package com.example.attyuttamsaha.facultyassigner;

import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;

class EnterNewDesignationHelper extends AsyncTask<String,Void,Long> {
    Context c;
    private String designation_title,credit;
    AsyncResponse delegate;
    EnterNewDesignationHelper(String designation_title, String credit, Context c, AsyncResponse delegate) {
        this.designation_title = designation_title;
        this.credit = credit;
        this.c = c;
        this.delegate = delegate;
    }

    @Override
    protected Long doInBackground(String... strings) {
        DBHelper sDB = new DBHelper(c);
        long result = -1;
        ArrayList<String> res = sDB.loadHandler_according_subject_name_SubjectDB(designation_title);
        if(res.size()==0)
          result = sDB.addHandler_DesignationDB(designation_title, credit);
        return result;
    }

    public interface AsyncResponse{
        void onEnterNewDesignationFinish(Long output);
    }
    @Override
    protected void onPostExecute(Long result){
        delegate.onEnterNewDesignationFinish(result);
    }
}
