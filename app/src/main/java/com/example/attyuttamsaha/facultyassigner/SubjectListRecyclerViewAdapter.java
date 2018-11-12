package com.example.attyuttamsaha.facultyassigner;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.StringTokenizer;

class SubjectListRecyclerViewAdapter extends RecyclerView.Adapter<SubjectListRecyclerViewHolder> {


    LayoutInflater inflater;
    Context context;
    List<String> subject;

    SubjectListRecyclerViewAdapter(List<String> subject, Context context){
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.subject = subject;
        this.context = context;
    }

    @NonNull
    @Override
    public SubjectListRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new SubjectListRecyclerViewHolder(inflater.inflate(R.layout.subject_card, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectListRecyclerViewHolder subjectListRecyclerViewHolder, int i) {

        Log.d("Adapter", "Event " + i);
        final String subjects = subject.get(i);
        StringTokenizer st = new StringTokenizer(subjects,",");
        final String[] subs=new String[4];
        int k=0;
        while (st.hasMoreTokens()) {
            subs[k++]=st.nextToken();
        }
        subjectListRecyclerViewHolder.subject_id.setText(subs[0]);
        subjectListRecyclerViewHolder.subject_name.setText(subs[1]);
        subjectListRecyclerViewHolder.subject_credit_score.setText(subs[2]);
        subjectListRecyclerViewHolder.subject_course.setText(subs[3]);
        /*subjectListRecyclerViewHolder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Main2Activity.class);
                intent.putExtra("eventId", event.getId());
                context.startActivity(intent);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return subject.size();
    }
}
