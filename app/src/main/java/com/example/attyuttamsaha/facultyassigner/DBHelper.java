 package com.example.attyuttamsaha.facultyassigner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

class DBHelper extends SQLiteOpenHelper{

    private static final String TAG = DBHelper.class.getSimpleName();

    private static final int DATABASE_VERSION = 1;

    private static final String USER_LIST_TABLE = "user_entries";
    private static final String SUBJECT_LIST_TABLE = "subject_entries";
    private static final String DESIGNATION_LIST_TABLE = "designation_entries";
    private static final String FACULTY_LIST_TABLE = "faculty_entries";

    private static final String DATABASE_NAME = "FacultyAssigner";

    public static final String KEY_ID_USER = "_id";
    public static final String KEY_USERNAME= "username";
    public static final String KEY_PASSWORD= "password";
    public static final String KEY_ADMIN= "admin";


    public static final String KEY_ID_SUBJECT = "_id";
    public static final String KEY_SUBJECT_NAME= "subject_name";
    public static final String KEY_SUBJECT_CREDIT_SCORE= "subject_credit_score";
    public static final String KEY_SUBJECT_COURSE= "subject_course";

    public static final String KEY_ID_DESIGNATION = "_id";
    public static final String KEY_DESIGNATION_TITLE= "designation_title";
    public static final String KEY_DESIGNATION_CREDIT= "designation_credit";

    public static final String KEY_ID_FACULTY = "_id";
    public static final String KEY_FACULTY_NAME = "faculty_name";
    public static final String KEY_FACULTY_AGE = "faculty_age";
    public static final String KEY_FACULTY_QUALIFICATION = "faculty_qualification";
    public static final String KEY_FACULTY_SUBJECTS_PRIORITY = "faculty_subjects_priority";
    public static final String KEY_FACULTY_YEARS_OF_EXPERIENCE = "faculty_years_of_experience";
    public static final String KEY_FACULTY_DESIGNATION = "faculty_designation";
    public static final String KEY_FACULTY_ASSIGNED_SUBJECT = "faculty_assigned_subject";



    //private static final String[] COLUMNS = { KEY_ID, KEY_USERNAME,KEY_PASSWORD,KEY_ADMIN };
    // Build the SQL query that creates the table.

    private static final String USER_LIST_TABLE_CREATE =
            "CREATE TABLE " + USER_LIST_TABLE + " (" +
                    KEY_ID_USER + " INTEGER PRIMARY KEY, " +
                    // id will auto-increment if no value passed
                    KEY_USERNAME + " TEXT, " +
                    KEY_PASSWORD + " TEXT, " +
                    KEY_ADMIN + " TEXT );";

    private static final String SUBJECT_LIST_TABLE_CREATE =
            "CREATE TABLE " + SUBJECT_LIST_TABLE + " (" +
                    KEY_ID_SUBJECT + " INTEGER PRIMARY KEY, " +
                    // id will auto-increment if no value passed
                    KEY_SUBJECT_NAME + " TEXT, " +
                    KEY_SUBJECT_CREDIT_SCORE + " INTEGER, " +
                    KEY_SUBJECT_COURSE + " TEXT );";

    private static final String DESIGNATION_LIST_TABLE_CREATE =
            "CREATE TABLE " + DESIGNATION_LIST_TABLE + " (" +
                    KEY_ID_DESIGNATION + " INTEGER PRIMARY KEY, " +
                    // id will auto-increment if no value passed
                    KEY_DESIGNATION_TITLE + " TEXT, " +
                    KEY_DESIGNATION_CREDIT + " INTEGER); ";

    private static final String FACULTY_LIST_TABLE_CREATE =
            "CREATE TABLE " + FACULTY_LIST_TABLE + " (" +
                    KEY_ID_FACULTY + " INTEGER PRIMARY KEY, " +
                    // id will auto-increment if no value passed
                    KEY_FACULTY_NAME + " TEXT, " +
                    KEY_FACULTY_AGE+ " INTEGER, "+
                    KEY_FACULTY_QUALIFICATION+" TEXT, "+
                    KEY_FACULTY_SUBJECTS_PRIORITY+" TEXT, "+
                    KEY_FACULTY_YEARS_OF_EXPERIENCE+" INTEGER, "+
                    KEY_FACULTY_DESIGNATION+" TEXT, "+
                    KEY_FACULTY_ASSIGNED_SUBJECT+" TEXT);";



    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SUBJECT_LIST_TABLE_CREATE);
        db.execSQL(USER_LIST_TABLE_CREATE);
        db.execSQL(DESIGNATION_LIST_TABLE_CREATE);
        db.execSQL(FACULTY_LIST_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long  addHandler_UserDB(String username,String password,String admin) {
        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, username);
        values.put(KEY_PASSWORD, password);
        values.put(KEY_ADMIN, admin);
        SQLiteDatabase db = this.getWritableDatabase();
        long res =  db.insert(USER_LIST_TABLE, null, values);
        db.close();
        return res;
    }

    public ArrayList<String> loadHandler_UserDB(String username,String password,String admin) {
        Log.e("yo", "I came here");
        Log.e("username",username);
        Log.e("password",password);
        ArrayList<String> result = new ArrayList<String>();
        String query = "Select * FROM " + USER_LIST_TABLE + " WHERE username = '"+username+"' AND password = '"+password+"' AND admin = '"+admin+"'";
        Log.e("query",query);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            String result_1 = cursor.getString(1);
            result.add(result_1);
        }
        cursor.close();
        db.close();
        return result;
    }
    public void deleteHandler_UserDB(){
        String query = "DELETE FROM "+USER_LIST_TABLE;
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(query);
    }
    public long addHandler_SubjectDB(String subject_name,String subject_credit_score,String subject_course) {
        ContentValues values = new ContentValues();
        values.put(KEY_SUBJECT_NAME, subject_name);
        values.put(KEY_SUBJECT_CREDIT_SCORE, Integer.parseInt(subject_credit_score));
        values.put(KEY_SUBJECT_COURSE, subject_course);
        SQLiteDatabase db = this.getWritableDatabase();
        long res =  db.insert(SUBJECT_LIST_TABLE, null, values);
        db.close();
        return res;
    }

    public ArrayList<String> loadHandler_SubjectDB(String subject_id) {
        ArrayList<String> result = new ArrayList<String>();
        int sid = Integer.parseInt(subject_id);
        String query = "Select * FROM " + SUBJECT_LIST_TABLE + " WHERE _id = "+sid;
        //Log.e("query",query);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            String result_1 = cursor.getString(1);
            result.add(result_1);
        }
        cursor.close();
        db.close();
        return result;
    }
    public ArrayList<String> loadHandler_all_SubjectDB() {
        ArrayList<String> result = new ArrayList<String>();
        String query = "Select * FROM " + SUBJECT_LIST_TABLE;
        //Log.e("query",query);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            String result_1 = cursor.getString(0)+",";
            result_1 += cursor.getString(1)+",";
            result_1 += cursor.getString(2)+",";
            result_1 += cursor.getString(3);
            result.add(result_1);
        }
        cursor.close();
        db.close();
        return result;
    }
    public ArrayList<String> loadHandler_according_subject_name_SubjectDB(String subject_name) {
        ArrayList<String> result = new ArrayList<String>();
        String query = "Select * FROM " + SUBJECT_LIST_TABLE + " WHERE subject_name = '"+subject_name+"'";
        //Log.e("query",query);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            String result_1 = cursor.getString(0)+",";
            result_1 += cursor.getString(1)+",";
            result_1 += cursor.getString(2)+",";
            result_1 += cursor.getString(3);
            result.add(result_1);
        }
        cursor.close();
        db.close();
        return result;
    }

    public long updateHandler_SubjectDB(String subject_id, String updated_subject_name, String updated_credit_score,String updated_subject_course) {
        ContentValues cv = new ContentValues();

        cv.put(KEY_ID_SUBJECT,subject_id);
        cv.put(KEY_SUBJECT_NAME,updated_subject_name);
        cv.put(KEY_SUBJECT_CREDIT_SCORE,updated_credit_score);
        cv.put(KEY_SUBJECT_COURSE,updated_subject_course);

        SQLiteDatabase db = this.getWritableDatabase();
        long res = db.update(SUBJECT_LIST_TABLE, cv, "_id="+subject_id, null);
        db.close();
        return res;
    }
    public void deleteHandler_SubjectDB(){
        String query = "DELETE FROM "+SUBJECT_LIST_TABLE;
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(query);
    }
    public int deleteHandler_id_SubjectDB(String delete_subject_id) {

        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(SUBJECT_LIST_TABLE, KEY_ID_SUBJECT + "=" + delete_subject_id, null);

    }
    public long addHandler_DesignationDB(String designation_title,String designation_credit) {
        ContentValues values = new ContentValues();
        values.put(KEY_DESIGNATION_TITLE, designation_title);
        values.put(KEY_DESIGNATION_CREDIT, Integer.parseInt(designation_credit));
        SQLiteDatabase db = this.getWritableDatabase();
        long res =  db.insert(DESIGNATION_LIST_TABLE, null, values);
        db.close();
        return res;
    }

    public ArrayList<String> loadHandler_DesignationDB(String designation_id) {
        ArrayList<String> result = new ArrayList<String>();
        int sid = Integer.parseInt(designation_id);
        String query = "Select * FROM " + DESIGNATION_LIST_TABLE + " WHERE _id = "+sid;
        //Log.e("query",query);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            String result_1 = cursor.getString(1);
            result.add(result_1);
        }
        cursor.close();
        db.close();
        return result;
    }

    public ArrayList<String> loadHandler_according_to_designation_title_DesignationDB(String designation_title) {
        ArrayList<String> result = new ArrayList<String>();

        String query = "Select * FROM " + DESIGNATION_LIST_TABLE + " WHERE designation_title = '"+designation_title+"'";
        //Log.e("query",query);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            String result_1 = cursor.getString(1);
            result.add(result_1);
        }
        cursor.close();
        db.close();
        return result;
    }

    public ArrayList<String> loadHandler_all_DesignationDB() {
        ArrayList<String> result = new ArrayList<String>();
        String query = "Select * FROM " + DESIGNATION_LIST_TABLE;
        //Log.e("query",query);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            String result_1 = cursor.getString(0)+",";
            result_1 += cursor.getString(1)+",";
            result_1 += cursor.getString(2);
            result.add(result_1);
        }
        cursor.close();
        db.close();
        return result;
    }
    public String loadHandler_single_DesignationDB(String designation_name){
        String query = "Select "+KEY_DESIGNATION_CREDIT+" from "+DESIGNATION_LIST_TABLE+" where "+KEY_DESIGNATION_TITLE+" = '"+designation_name+"'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        String result="";
        while (cursor.moveToNext()) {
            String result_1 = cursor.getString(0);
            result +=result_1;
        }
        cursor.close();
        db.close();
        return result;
    }
    public void deleteHandler_DesignationDB(){
        String query = "DELETE FROM "+DESIGNATION_LIST_TABLE;
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(query);
    }
    public int deleteHandler_id_DesignationDB(String delete_designation_id) {

        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(DESIGNATION_LIST_TABLE, KEY_ID_DESIGNATION + "=" + delete_designation_id, null);

    }
    public long updateHandler_DesignationDB(String designation_id, String updated_designation_title, String updated_credit_score) {
        ContentValues cv = new ContentValues();

        cv.put(KEY_ID_DESIGNATION,designation_id);
        cv.put(KEY_DESIGNATION_TITLE,updated_designation_title);
        cv.put(KEY_DESIGNATION_CREDIT,updated_credit_score);

        SQLiteDatabase db = this.getWritableDatabase();
        long res = db.update(DESIGNATION_LIST_TABLE, cv, "_id="+designation_id, null);
        db.close();
        return res;
    }

    //---------------

    public long addHandler_FacultyDB(String f_name,String f_age,String years_of_experience,String subject_rank,String qualification,String designation,String assignedSubject) {
        ContentValues values = new ContentValues();
        values.put(KEY_FACULTY_NAME, f_name);
        values.put(KEY_FACULTY_AGE, Integer.parseInt(f_age));
        values.put(KEY_FACULTY_YEARS_OF_EXPERIENCE, Integer.parseInt(years_of_experience));
        values.put(KEY_FACULTY_SUBJECTS_PRIORITY, subject_rank);
        values.put(KEY_FACULTY_QUALIFICATION, qualification);
        values.put(KEY_FACULTY_DESIGNATION, designation);
        values.put(KEY_FACULTY_ASSIGNED_SUBJECT, assignedSubject);

        SQLiteDatabase db = this.getWritableDatabase();
        long res =  db.insert(FACULTY_LIST_TABLE, null, values);
        db.close();
        return res;
    }

    public ArrayList<String> loadHandler_FacultyDB(String faculty_id) {
        ArrayList<String> result = new ArrayList<String>();
        int fid = Integer.parseInt(faculty_id);
        String query = "Select * FROM " + FACULTY_LIST_TABLE + " WHERE _id = "+fid;
        //Log.e("query",query);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            String result_1 = cursor.getString(7);
            result.add(result_1);
        }
        cursor.close();
        db.close();
        return result;
    }
    public ArrayList<String> loadHandler_all_FacultyDB() {
        ArrayList<String> result = new ArrayList<String>();
        String query = "Select * FROM " + FACULTY_LIST_TABLE;
        //Log.e("query",query);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            String result_1 = cursor.getString(0)+",";
            result_1 += cursor.getString(1)+",";
            result_1 += cursor.getString(2)+",";
            result_1 += cursor.getString(3)+",";
            result_1 += cursor.getString(4)+",";
            result_1 += cursor.getString(5)+",";
            result_1 += cursor.getString(6)+",";
            result_1 += cursor.getString(7);
            Log.d("extracted: ",result_1);
            result.add(result_1);
        }
        cursor.close();
        db.close();
        return result;
    }
    public void deleteHandler_FacultyDB(){
        String query = "DELETE FROM "+FACULTY_LIST_TABLE;
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(query);
    }

    public long updateHandler_FacultyDB(String faculty_id, String updated_faculty_name, String updated_faculty_age,String updated_faculty_qualification,String updated_faculty_subjects_priority,String updated_faculty_years_of_experience,String updated_faculty_designation,String assignedSubject) {
        ContentValues cv = new ContentValues();

        cv.put(KEY_ID_FACULTY,Integer.parseInt(faculty_id));
        cv.put(KEY_FACULTY_NAME,updated_faculty_name);
        cv.put(KEY_FACULTY_AGE,Integer.parseInt(updated_faculty_age));
        cv.put(KEY_FACULTY_QUALIFICATION,updated_faculty_qualification);
        cv.put(KEY_FACULTY_SUBJECTS_PRIORITY,updated_faculty_subjects_priority);
        cv.put(KEY_FACULTY_YEARS_OF_EXPERIENCE,Integer.parseInt(updated_faculty_years_of_experience));
        cv.put(KEY_FACULTY_DESIGNATION,updated_faculty_designation);
        cv.put(KEY_FACULTY_ASSIGNED_SUBJECT,assignedSubject);

        SQLiteDatabase db = this.getWritableDatabase();
        long res = db.update(FACULTY_LIST_TABLE, cv, "_id="+faculty_id, null);
        db.close();
        return res;
    }
    public long updateHandler_assignedSubject_FacultyDB(String faculty_id,String assignedSubject){
        ContentValues cv = new ContentValues();

        cv.put(KEY_ID_FACULTY,Integer.parseInt(faculty_id));
        cv.put(KEY_FACULTY_ASSIGNED_SUBJECT,assignedSubject);

        SQLiteDatabase db = this.getWritableDatabase();
        long res = db.update(FACULTY_LIST_TABLE, cv, "_id="+faculty_id, null);
        db.close();
        return res;
    }
}
