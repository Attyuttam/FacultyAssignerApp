package com.example.attyuttamsaha.facultyassigner;

import java.util.ArrayList;

class FacultyDetails {
    private Integer f_id;
    private Integer f_weight;
    private String f_name;
    private ArrayList<String> sub_list;
    private ArrayList<Integer> sub_priorty;
    private String AssignedSubject;
    FacultyDetails(Integer f_id,String f_name,ArrayList<String> sub_list,ArrayList<Integer> sub_priorty,Integer f_weight,String AssignedSubject){
        this.f_id = f_id;
        this.f_name = f_name;
        this.sub_list = sub_list;
        this.sub_priorty = sub_priorty;
        this.f_weight = f_weight;
        this.AssignedSubject = AssignedSubject;
    }
    int getF_id(){
        return this.f_id;
    }
    String getF_name(){
        return this.f_name;
    }
    ArrayList<String> getSub_list(){
        return this.sub_list;
    }
    ArrayList<Integer> getSub_priority(){
        return this.sub_priorty;
    }
    Integer getF_weight(){
        return this.f_weight;
    }
    String getAssignedSubject(){
        return this.AssignedSubject;
    }
    public void setAssignedSubject(String assignedSubject){
        this.AssignedSubject = assignedSubject;
    }
}
