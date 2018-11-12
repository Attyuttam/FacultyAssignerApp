package com.example.attyuttamsaha.facultyassigner;

import android.content.Context;
import android.os.AsyncTask;

class UpdateDesignationHelper extends AsyncTask<String,Void,Long>{

    private Context c;
    private AsyncResponse delegate;

    private String designation_id,updated_designation_title,updated_credit_score;

    UpdateDesignationHelper(String designation_id, String updated_designation_title, String updated_credit_score, Context c, AsyncResponse delegate) {
        this.designation_id = designation_id;
        this.updated_credit_score = updated_credit_score;
        this.updated_designation_title = updated_designation_title;
        this.c = c;
        this.delegate = delegate;
    }
    @Override
    protected Long doInBackground(String... strings) {
        DBHelper dDB = new DBHelper(c);
        return dDB.updateHandler_DesignationDB(designation_id,updated_designation_title,updated_credit_score);
    }
    public interface AsyncResponse{
        void onUpdateFinished(Long result);
    }
    @Override
    protected void onPostExecute(Long result){
        delegate.onUpdateFinished(result);
    }
}
