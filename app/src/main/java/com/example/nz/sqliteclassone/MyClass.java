package com.example.nz.sqliteclassone;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Nz on 2/28/2019.
 */

public class MyClass  extends SQLiteOpenHelper{

    // variable ..........


    // use for consturctor ....
    public  static  final String DATABASE_NAME = "Student.db";
    public  static  final String TABLE_NAME = "student_details";
    public  static  final  int VERSION_NUMBER = 3;

// use for onCreate Method ...
    public  static  final  String ID = "_id";
    public  static  final  String NAME = "Name";
    public  static  final String AGE = "Age";
    public static final String GENDER = "Gender";
    public  static  final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+" ("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NAME+" VARCHAR(255), "+AGE+" INTEGER,"+GENDER+" VARCHAR(15))";

    // use for onUpgrade method .........

    public static  final  String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME;

    // use for displayAllData method .....

    public  static final String SELECT_ALL_DATA = "SELECT * FROM "+TABLE_NAME;


    //Create  Context .......

    private  Context context;


    // constructor .....

    public MyClass(Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
        this.context = context;
    }


    // onCreate Method ....

    @Override
    public void onCreate(SQLiteDatabase sQLiteDatabase) {

        try {
            Toast.makeText(context,"DataBase is created",Toast.LENGTH_SHORT).show();
            sQLiteDatabase.execSQL(CREATE_TABLE);
        }catch (Exception e){
            Toast.makeText(context,"Exception is : "+e,Toast.LENGTH_SHORT).show();
        }
    }



    // onUpgrade Method.........

    @Override
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int oldVersion, int newVersion) {

        try {
            Toast.makeText(context,"Data_Base is Upgrade ",Toast.LENGTH_SHORT).show();
            sQLiteDatabase.execSQL(DROP_TABLE);
            onCreate(sQLiteDatabase);
        }catch (Exception e){
            Toast.makeText(context,"Exception is : "+e,Toast.LENGTH_SHORT).show();
        }
    }


    // insert data from user . ...........

    public long inseartData (String name, String age, String gendear){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME,name);
        contentValues.put(AGE,age);
        contentValues.put(GENDER,gendear);
        long rowId = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        return  rowId;
    }


    // read Data ........

    public Cursor displayAlldata(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_ALL_DATA,null);
        return  cursor;
    }

    // update Data .......

    public boolean  updateData (String  id, String name , String age , String gender){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID,id);
        contentValues.put(NAME,name);
        contentValues.put(AGE,age);
        contentValues.put(GENDER,gender);
        sqLiteDatabase.update(TABLE_NAME,contentValues,ID+" = ?", new String[]{id});
        return  true;
    }

    // delete Data.....

    public  int deleteData(String id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME,ID+" = ? ", new String[]{id});
    }


}
