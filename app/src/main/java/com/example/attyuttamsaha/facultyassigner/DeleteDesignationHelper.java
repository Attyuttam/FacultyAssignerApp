package com.example.attyuttamsaha.facultyassigner;

import android.content.Context;
import android.os.AsyncTask;

class DeleteDesignationHelper extends AsyncTask<String,Void,Integer> {
    private String delete_designation_id;
    private Context c;
    private AsyncResponse delegate;
    DeleteDesignationHelper(String delete_designation_id, Context c, AsyncResponse delegate) {
        this.delete_designation_id = delete_designation_id;
        this.c = c;
        this.delegate = delegate;
    }
    public interface AsyncResponse{
        void onDeleteDesignationHelper(Integer result);
    }

    @Override
    protected Integer doInBackground(String... strings) {
        DBHelper dDB = new DBHelper(c);
        return dDB.deleteHandler_id_DesignationDB(delete_designation_id);
    }
    @Override
    protected void onPostExecute(Integer result){
        delegate.onDeleteDesignationHelper(result);
    }
}
