package com.example.attyuttamsaha.facultyassigner;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

class AssignedListRecyclerViewHolder extends RecyclerView.ViewHolder {
    public LinearLayout container;
    public TextView faculty_id,faculty_name,faculty_designation,assigned_subject;
    public AssignedListRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        container = itemView.findViewById(R.id.subjectContainer);
        faculty_id = itemView.findViewById(R.id.faculty_id);
        faculty_name = itemView.findViewById(R.id.faculty_name_card);
        faculty_designation = itemView.findViewById(R.id.faculty_designation_card);
        assigned_subject = itemView.findViewById(R.id.assigned_sub_card);
    }
}
