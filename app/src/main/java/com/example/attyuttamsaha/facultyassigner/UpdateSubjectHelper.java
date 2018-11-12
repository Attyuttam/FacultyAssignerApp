package com.example.attyuttamsaha.facultyassigner;

import android.content.Context;
import android.os.AsyncTask;

class UpdateSubjectHelper extends AsyncTask<String,Void,Long> {
    private Context c;
    private AsyncResponse delegate;

    private String subject_id,updated_subject_name,updated_credit_score,updated_subject_course;

    UpdateSubjectHelper(String subject_id, String updated_subject_name, String updated_credit_score, String updated_subject_course,Context c, AsyncResponse delegate) {
        this.subject_id = subject_id;
        this.updated_credit_score = updated_credit_score;
        this.updated_subject_name = updated_subject_name;
        this.updated_subject_course = updated_subject_course;
        this.c = c;
        this.delegate = delegate;
    }
    @Override
    protected Long doInBackground(String... strings) {
        DBHelper dDB = new DBHelper(c);
        return dDB.updateHandler_SubjectDB(subject_id,updated_subject_name,updated_credit_score,updated_subject_course);
    }
    public interface AsyncResponse{
        void onUpdateFinished(Long result);
    }
    @Override
    protected void onPostExecute(Long result){
        delegate.onUpdateFinished(result);
    }
}
