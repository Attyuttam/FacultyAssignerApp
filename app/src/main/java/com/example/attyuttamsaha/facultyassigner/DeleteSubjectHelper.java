package com.example.attyuttamsaha.facultyassigner;

import android.content.Context;
import android.os.AsyncTask;

class DeleteSubjectHelper extends AsyncTask<String,Void,Integer> {
    private String delete_subject_id;
    private Context c;
    private AsyncResponse delegate;
    DeleteSubjectHelper(String delete_subject_id, Context c,AsyncResponse delegate) {
        this.delete_subject_id = delete_subject_id;
        this.c = c;
        this.delegate = delegate;
    }
    public interface AsyncResponse{
        void onDeleteSubjectHelper(Integer result);
    }

    @Override
    protected Integer doInBackground(String... strings) {
        DBHelper dDB = new DBHelper(c);
        return dDB.deleteHandler_id_SubjectDB(delete_subject_id);
    }
    @Override
    protected void onPostExecute(Integer result){
        delegate.onDeleteSubjectHelper(result);
    }
}
