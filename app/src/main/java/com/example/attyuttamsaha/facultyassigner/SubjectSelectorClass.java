package com.example.attyuttamsaha.facultyassigner;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SubjectSelectorClass extends AppCompatActivity implements ViewSubjectListHelper.AsyncResponse{
    LinearLayout checkboxContainer ;
    CheckBox checkBox;
    final ArrayList<CheckBox> checkBoxArrayList = new ArrayList<>();
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_selector_class);

        checkboxContainer = (LinearLayout)findViewById(R.id.checkBoxContainer);
        new ViewSubjectListHelper(this,this).execute();
    }

    @Override
    public void onSubjectListReceieved(ArrayList<String> result) {

        if(result.size()>0){
            for(int i=0;i<result.size();i++){
                checkBox = new CheckBox(this);
                checkBox.setId(i);
                String[] s = result.get(i).split(",");
                String toPlace = s[1]+", credit: "+s[2]+" ,course: "+s[3];
                checkBox.setText(toPlace);
                checkBox.setTag(toPlace);
                checkBoxArrayList.add(checkBox);
                checkboxContainer.addView(checkBox);
            }
        }
    }

    public void selectSubject(View view) {
        //we can open a dialog box to set the priorities now

        String subjects="";
        if(checkBoxArrayList.size()>0) {
            for (int i = 0; i < checkBoxArrayList.size(); i++) {
                if (checkBoxArrayList.get(i).isChecked()) {
                    subjects += checkBoxArrayList.get(i).getTag().toString() + "-";
                }
            }
            StringBuffer sb = new StringBuffer(subjects);
            sb.replace(sb.length() - 1, sb.length(), "");
            subjects = sb.toString();
            inputPriorities(subjects);
        }
    }

    private void inputPriorities(String subjects) {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog);
        LinearLayout subject = (LinearLayout)dialog.findViewById(R.id.sub_priority);
        EditText sub;
        String[] s = subjects.split("-");
        final ArrayList<EditText> allEds = new ArrayList<>();
        ArrayList<String> subjects_selected = new ArrayList<>();
        for(int i=0;i<s.length;i++){
            String[] su = s[i].split(",");
            sub = new EditText(dialog.getContext());

            Log.d("s",s[i]);
            Log.d("su",su[0]);
            subjects_selected.add(su[0]);
            sub.setHint("Enter priority of "+su[0]);
            sub.setId(i);
            allEds.add(sub);
            subject.addView(sub);

        }
        dialog.show();
        Button setPrioritiesButton = (Button) dialog.findViewById(R.id.set_priorites_button);
        final ArrayList<EditText> allEds_temp;
        allEds_temp= allEds;
        final int size_ed = allEds_temp.size();
        final ArrayList<String> subjects_selected_final = subjects_selected;
        //String[] final_strings = new String[size_ed];

        setPrioritiesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strings = "";
                int flag = 0;
                for(int i=0; i < size_ed-1; i++){
                    strings += subjects_selected_final.get(i) +"-"+ allEds_temp.get(i).getText().toString()+"%";
                    if(allEds_temp.get(i).getText().toString().equals("") || !numberOrNot(allEds_temp.get(i).getText().toString())){
                        Toast.makeText(getApplicationContext(),"PRIORITIES CANNOT BE EMPTY AND MUST BE NUMERIC",Toast.LENGTH_LONG).show();
                        flag = 1;
                        break;
                    }
                    else if(Integer.parseInt(allEds_temp.get(i).getText().toString())<0 || Integer.parseInt(allEds_temp.get(i).getText().toString())>=size_ed){
                        Toast.makeText(getApplicationContext(),"PRIORITIES MUST BE WITHIN 0 TO "+Integer.toString(size_ed-1)+" BOTH INCLUDED",Toast.LENGTH_LONG).show();
                        flag = 1;
                        break;
                    }
                }
                if(flag == 0) {
                    strings += subjects_selected_final.get(size_ed - 1) + "-" + allEds_temp.get(size_ed - 1).getText().toString();
                    if(allEds_temp.get(size_ed-1).getText().toString().equals("") || !numberOrNot(allEds_temp.get(size_ed-1).getText().toString())){
                        Toast.makeText(getApplicationContext(),"PRIORITIES CANNOT BE EMPTY AND MUST BE NUMERIC",Toast.LENGTH_LONG).show();
                    }
                    else if(Integer.parseInt(allEds_temp.get(size_ed-1).getText().toString())<0 || Integer.parseInt(allEds_temp.get(size_ed-1).getText().toString())>=size_ed){
                        Toast.makeText(getApplicationContext(),"PRIORITIES MUST BE WITHIN 0 TO "+Integer.toString(size_ed-1)+" BOTH INCLUDED",Toast.LENGTH_LONG).show();
                    }
                    else{
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra("subject_rank_list", strings);
                        setResult(Activity.RESULT_OK, returnIntent);
                        finish();
                    }
                }
            }
        });

    }
    static boolean numberOrNot(String input)
    {
        try
        {
            Integer.parseInt(input);
        }
        catch(NumberFormatException ex)
        {
            return false;
        }
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }
}
