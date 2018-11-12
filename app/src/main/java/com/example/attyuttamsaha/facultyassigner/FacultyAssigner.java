package com.example.attyuttamsaha.facultyassigner;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedHashMap;

class FacultyAssigner {
    Context c;
    ArrayList<String> faculty_list;
    ArrayList<String> subject_list;
    ArrayList<Integer> faculty_of_subject_id = new ArrayList<>();
    ArrayList<String> faculty_of_subject_name = new ArrayList<>();
    ArrayList<FacultyDetails> faculty_pref_list = new ArrayList<>();

    public FacultyAssigner(Context c) {
        this.c = c;
        faculty_list=null;
    }

    public void applyAssignment() {
        //extract necessary details from database
        /*
        * faculties along with their designation required
        * faculties along with their list of preferences required
        * list of subjects required
        * */
        //Synchronously set up the data
        DBHelper DB = new DBHelper(c);
        onFacultyDetailsLoad(DB.loadHandler_all_FacultyDB());
        onSubjectListReceieved(DB.loadHandler_all_SubjectDB());
        //stable marriage algo
        applyStableMarriageAlgo();
        //insert details in faculty DB
        putFacultyDetailsInDB();
    }

    private void putFacultyDetailsInDB() {
        DBHelper fDB = new DBHelper(c);
        for(int i=0;i<faculty_pref_list.size();i++){
            int fac_id = faculty_pref_list.get(i).getF_id();
            String sub = faculty_pref_list.get(i).getAssignedSubject();
            fDB.updateHandler_assignedSubject_FacultyDB(Integer.toString(fac_id),sub);
        }
    }

    private void applyStableMarriageAlgo() {
        int fac_id = checkFacultyAssignedListStatus();
        while(fac_id!=-1){
            String highest_prio_sub = getHighestPrioritySubject(fac_id);

            if(!highest_prio_sub.equals("")){
                int highestPrioritySubjectFaculty = HighestPrioritySubjectFaculty(highest_prio_sub);
                if(highestPrioritySubjectFaculty == -1){//whether the subject is not assigned a faculty
                    setAssignedSubject(fac_id,highest_prio_sub);
                }
                else{//if assigned that corresponding faculty id must have been returned
                    //highestPrioritySubjectFaculty is the id of the faculty who has been assigned
                    //fac_id is the faculty under consideration
                    int weight_current_faculty = findWeight(fac_id);
                    int weight_assigned_faculty = findWeight(highestPrioritySubjectFaculty);
                    if(weight_current_faculty > weight_assigned_faculty){
                        setAssignedSubject(fac_id,highest_prio_sub);
                        setAssignedSubject(highestPrioritySubjectFaculty,"");
                    }
                }
            }
            fac_id = checkFacultyAssignedListStatus();
        }
        printFacultyDetails();
    }

    private void printFacultyDetails() {
        for(int i=0;i<faculty_pref_list.size();i++){
            Log.d("faculty id",Integer.toString(faculty_pref_list.get(i).getF_id()));
            Log.d("faculty name",faculty_pref_list.get(i).getF_name());
            Log.d("faculty weight",Integer.toString(faculty_pref_list.get(i).getF_weight()));
            Log.d("assigned subject",faculty_pref_list.get(i).getAssignedSubject());
        }
    }

    private int findWeight(int fac_id) {
        int weight = -1;
        for(int i=0;i<faculty_pref_list.size();i++){
            if(faculty_pref_list.get(i).getF_id() == fac_id){
                weight = faculty_pref_list.get(i).getF_weight();
                break;
            }
        }
        return weight;
    }

    private void setAssignedSubject(int fac_id, String highest_prio_sub) {
        int fac_index = -1;
        for (int i=0;i<faculty_pref_list.size();i++){
            int f_id = faculty_pref_list.get(i).getF_id();
            if(f_id == fac_id){
                faculty_pref_list.get(i).setAssignedSubject(highest_prio_sub);
                fac_index = i;
                break;
            }
        }
        if(!highest_prio_sub.equals("")){
            for(int i=0;i<subject_list.size();i++){
                if(subject_list.get(i).equals(highest_prio_sub)){
                    faculty_of_subject_id.set(i,faculty_pref_list.get(fac_index).getF_id());
                    faculty_of_subject_name.set(i,faculty_pref_list.get(fac_index).getF_name());
                }
            }
        }

    }

    private int HighestPrioritySubjectFaculty(String highest_prio_sub) {
        int fac = -1;
        for(int i=0;i<subject_list.size();i++){
            if(subject_list.get(i).equals(highest_prio_sub)){
                if(faculty_of_subject_id.get(i) == -1){
                    return -1;
                }
                else{
                    fac = faculty_of_subject_id.get(i);
                    break;
                }

            }
        }
        return fac;
    }

    private String getHighestPrioritySubject(int fac_id) {
        for(int i=0;i<faculty_pref_list.size();i++){
            if(faculty_pref_list.get(i).getF_id()==fac_id){
                String sub =faculty_pref_list.get(i).getSub_list().get(0);
                faculty_pref_list.get(i).getSub_list().remove(0);
                faculty_pref_list.get(i).getSub_priority().remove(0);
                return sub;
            }
        }
        return "";
    }

    private int checkFacultyAssignedListStatus() {
        for(int i=0;i<faculty_pref_list.size();i++){
            if(faculty_pref_list.get(i).getAssignedSubject().equals(""))return faculty_pref_list.get(i).getF_id();
        }
        return -1;
    }

    public void onFacultyDetailsLoad(ArrayList<String> output) {
        //here we get the faculty details
        if(output == null){
            Toast.makeText(c,"Cannot perform assignment ",Toast.LENGTH_LONG).show();
        }
        else{
            faculty_list = output;
            for(int i=0;i<faculty_list.size();i++){

                String[] s = faculty_list.get(i).split(",");
                //s[4] has the subjects with their corresponding preferences in the format sub_name-4(priority value)
                Log.d("s",s[i]);
                String f_id = s[0];
                String f_name = s[1];
                String split_pref[] = s[4].split("%");
                ArrayList<String> s_name = new ArrayList<>();
                ArrayList<Integer> s_priority = new ArrayList<>();

                for(int k=0;k<split_pref.length;k++){
                    String temp[] = split_pref[k].split("-");
                    s_name.add(temp[0]);
                    s_priority.add(Integer.parseInt(temp[1]));
                }
                //now need to sort the subjects according to the priorities
                for (int k = 0; k < s_name.size()-1; k++) {
                    for (int j = 0; j < s_name.size() - k - 1; j++) {
                        if (s_priority.get(j) < s_priority.get(j + 1)) {
                            // swap temp and arr[i]
                            int temp = s_priority.get(j);
                            s_priority.set(j, s_priority.get(j + 1));
                            s_priority.set(j + 1, temp);

                            String temp_name = s_name.get(j);
                            s_name.set(j, s_name.get(j + 1));
                            s_name.set(j + 1, temp_name);
                        }
                    }
                }
                DBHelper dDB = new DBHelper(c);
                int designation_credit = Integer.parseInt(dDB.loadHandler_single_DesignationDB(s[6]));
                int course_value=0;
                String cv[] = s[3].split("%");
                for(int z=0;z<cv.length;z++){
                    if(cv[z].equals("BCA")||cv[z].equals("B.Sc")||cv[z].equals("B.Tech")||cv[z].equals("BA")){
                        course_value+=5;
                    }
                    else if(cv[z].equals("MBA") ||cv[z].equals("MCA")||cv[z].equals("MSc")||cv[z].equals("M.Tech")||cv[z].equals("MA")){
                        course_value+=10;
                    }
                    else if(cv[z].equals("Doctorate")){
                        course_value+=15;
                    }
                }
                int f_weight = (int) (designation_credit*0.5+Integer.parseInt(s[2])*0.2+course_value*0.3);
                FacultyDetails fd = new FacultyDetails(Integer.parseInt(f_id),f_name,s_name,s_priority,f_weight,"");
                faculty_pref_list.add(fd);
                for (int k = 0; k < faculty_pref_list.size()-1; k++) {
                    for (int j = 0; j < faculty_pref_list.size() - k - 1; j++) {
                        if (faculty_pref_list.get(j).getF_weight() > faculty_pref_list.get(j + 1).getF_weight()) {
                            // swap temp and arr[i]
                            FacultyDetails temp = faculty_pref_list.get(j);
                            faculty_pref_list.set(j, faculty_pref_list.get(j + 1));
                            faculty_pref_list.set(j + 1, temp);
                        }
                    }
                }
                /*Log.d("f_id",Integer.toString(fd.getF_id()));
                Log.d("f_name",fd.getF_name());
                for(int z =0 ;z<fd.getSub_list().size();z++){
                    Log.d("f_sub",fd.getSub_list().get(z));
                    Log.d("f_prio",Integer.toString(fd.getSub_priority().get(z)));
                }
                Log.d("f_weight",Integer.toString(fd.getF_weight()));*/
            }
        }
    }


    public void onSubjectListReceieved(ArrayList<String> output) {
        if(output == null){
            Toast.makeText(c,"Cannot perform assignment ",Toast.LENGTH_LONG).show();
        }
        else{
            ArrayList<String> op = new ArrayList<>();
            for(int i=0;i<output.size();i++){
                String s[] = output.get(i).split(",");
                op.add(s[1]);
                faculty_of_subject_name.add("");
                faculty_of_subject_id.add(-1);
            }
            subject_list = op;
        }
    }
}
