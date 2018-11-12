package com.example.attyuttamsaha.facultyassigner;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

public class SubjectListRecyclerViewHolder extends RecyclerView.ViewHolder {

    public LinearLayout container;
    public TextView subject_id,subject_name,subject_credit_score,subject_course;

    SubjectListRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        container = itemView.findViewById(R.id.subjectContainer);
        subject_id = itemView.findViewById(R.id.subject_id);
        subject_name = itemView.findViewById(R.id.subject_name_card);
        subject_credit_score = itemView.findViewById(R.id.subject_credit_score_card);
        subject_course = itemView.findViewById(R.id.subject_course_card);
    }
}
