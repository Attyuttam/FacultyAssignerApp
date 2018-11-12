package com.example.attyuttamsaha.facultyassigner;

import android.content.Context;
import android.os.AsyncTask;


class EnterDetailsHelper extends AsyncTask<String,Void,Long>{
    private Context c;
    private AsyncResponse delegate;
    private String f_name,f_age,years_of_experience,subject_rank,qualification,designation,assignedSubject;
    public EnterDetailsHelper(String f_name, String f_age, String years_of_experience, String subject_rank, String qualification, String designation, String assignedSubject,Context c, AsyncResponse delegate) {
        this.f_name = f_name;
        this.f_age = f_age;
        this.years_of_experience = years_of_experience;
        this.subject_rank = subject_rank;
        this.qualification = qualification;
        this.designation = designation;
        this.assignedSubject = assignedSubject;
        this.c = c;
        this.delegate = delegate;

    }
    public interface AsyncResponse{
        void onDetailsEntered(Long result);
    }
    @Override
    protected Long doInBackground(String... strings) {
        DBHelper sDB = new DBHelper(c);
        long result = sDB.addHandler_FacultyDB(f_name,f_age,years_of_experience,subject_rank,qualification,designation,assignedSubject);
        return result;
    }
    @Override
    protected void onPostExecute(Long result){
        delegate.onDetailsEntered(result);
    }
}
