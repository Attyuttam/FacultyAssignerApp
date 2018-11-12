package com.example.attyuttamsaha.facultyassigner;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;

class EnterNewSubHelper extends AsyncTask<String,Void,Long>{
    private String subject_name,subject_credit_score,subject_course;
    private AsyncResponse delegate;
    private Context c;

    public EnterNewSubHelper(String subject_name, String subject_credit_score, String subject_course,Context c, AsyncResponse delegate) {
        this.subject_name = subject_name;
        this.subject_credit_score = subject_credit_score;
        this.subject_course = subject_course;
        this.delegate = delegate;
        this.c = c;
    }
    public interface AsyncResponse{
        void onEnterNewSubFinish(Long output);
    }
    @Override
    protected Long doInBackground(String... strings) {
        long result = -1;
        DBHelper sDB = new DBHelper(c);
        ArrayList<String> res = sDB.loadHandler_according_subject_name_SubjectDB(subject_name);
        if(res.size()==0)
            result = sDB.addHandler_SubjectDB(subject_name, subject_credit_score,subject_course);
        return result;
    }
    @Override
    protected void onPostExecute(Long result){
        delegate.onEnterNewSubFinish(result);
    }
}
