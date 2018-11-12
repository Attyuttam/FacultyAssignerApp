package com.example.attyuttamsaha.facultyassigner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class ViewSubjectList extends AppCompatActivity implements ViewSubjectListHelper.AsyncResponse {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_subject_list);
        new ViewSubjectListHelper(this,this).execute();
    }

    @Override
    public void onSubjectListReceieved(ArrayList<String> list) {

        RecyclerView subjectListRecyclerView = findViewById(R.id.recyclerView);
        SubjectListRecyclerViewAdapter subjectListAdapter = new SubjectListRecyclerViewAdapter(list, this);
        subjectListRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayout.VERTICAL, false));
        subjectListRecyclerView.setAdapter(subjectListAdapter);
        subjectListAdapter.notifyDataSetChanged();
    }
}
