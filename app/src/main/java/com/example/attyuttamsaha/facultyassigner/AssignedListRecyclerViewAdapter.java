package com.example.attyuttamsaha.facultyassigner;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;
import java.util.StringTokenizer;

class AssignedListRecyclerViewAdapter extends RecyclerView.Adapter<AssignedListRecyclerViewHolder>{


    LayoutInflater inflater;
    Context context;
    List<String> faculty;

    AssignedListRecyclerViewAdapter(List<String> faculty, Context context){
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.faculty = faculty;
        this.context = context;
    }
    @NonNull
    @Override
    public AssignedListRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new AssignedListRecyclerViewHolder(inflater.inflate(R.layout.faculty_card, viewGroup, false));    }

    @Override
    public void onBindViewHolder(@NonNull AssignedListRecyclerViewHolder assignedListRecyclerViewHolder, int i) {

        final String subjects = faculty.get(i);
        StringTokenizer st = new StringTokenizer(subjects,",");
        final String[] subs=new String[8];
        int k=0;
        while (st.hasMoreTokens()) {
            subs[k++]=st.nextToken();
        }
        assignedListRecyclerViewHolder.faculty_id.setText(subs[0]);
        assignedListRecyclerViewHolder.faculty_name.setText(subs[1]);
        assignedListRecyclerViewHolder.faculty_designation.setText(subs[6]);
        assignedListRecyclerViewHolder.assigned_subject.setText(subs[7]);
        Log.d("subrecycler",subs[4]);
    }

    @Override
    public int getItemCount() {
        return faculty.size();
    }
}
