package com.example.attyuttamsaha.facultyassigner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class ViewAssignedList extends AppCompatActivity implements ViewAssignedListHelper.AsyncResponse{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_assigned_list);
        new ViewAssignedListHelper(this,this).execute();
    }

    @Override
    public void OnAssignedListReturned(ArrayList<String> result) {
       if(result.size()>0)
        Log.d("view assigned ",result.get(0));

        RecyclerView assignedListRecyclerView = findViewById(R.id.recyclerView);
        AssignedListRecyclerViewAdapter assignedListAdapter = new AssignedListRecyclerViewAdapter(result, this);
        assignedListRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayout.VERTICAL, false));
        assignedListRecyclerView.setAdapter(assignedListAdapter);
        assignedListAdapter.notifyDataSetChanged();
    }
}
