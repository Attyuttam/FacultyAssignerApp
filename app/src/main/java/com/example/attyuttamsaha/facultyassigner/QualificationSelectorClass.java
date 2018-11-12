package com.example.attyuttamsaha.facultyassigner;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class QualificationSelectorClass extends AppCompatActivity {
    LinearLayout checkboxContainer ;
    CheckBox checkBox;

    ArrayList<String> result = new ArrayList<>();
    final ArrayList<CheckBox> checkBoxArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qualification_selector_class);
        checkboxContainer = (LinearLayout)findViewById(R.id.checkBoxContainer);
        result.add("MCA");
        result.add("MSc");
        result.add("M.Tech");
        result.add("MBA");
        result.add("MA");
        result.add("B.Sc");
        result.add("BCA");
        result.add("B.Tech");
        result.add("BA");
        result.add("Doctorate");

        if(result.size()>0){
            for(int i=0;i<result.size();i++){
                checkBox = new CheckBox(this);
                checkBox.setId(i);
                checkBox.setText(result.get(i));
                checkBox.setTag(result.get(i));
                checkBoxArrayList.add(checkBox);
                checkboxContainer.addView(checkBox);
            }
        }
    }

    public void selectQualification(View view) {
        Intent returnIntent = new Intent();
        String qualifications="";
        for(int i=0;i<checkBoxArrayList.size();i++){
            if(checkBoxArrayList.get(i).isChecked()){
                qualifications += checkBoxArrayList.get(i).getTag().toString()+"%";
            }
        }
        StringBuffer sb = new StringBuffer(qualifications);
        sb.replace(sb.length()-1,sb.length(),"");
        qualifications = sb.toString();
        returnIntent.putExtra("result",qualifications);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }
}
