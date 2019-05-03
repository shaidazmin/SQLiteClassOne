package com.example.nz.sqliteclassone;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    MyClass myClass;

    EditText userName, userAge, userGendear,userId;
    Button saveButton, showButton,clearButton,updatButton,deleteButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // open DataBase .....
       myClass = new MyClass(this);

       SQLiteDatabase sqLiteDatabase = myClass.getWritableDatabase();
        // close ...

        // design implement .....
                // edit text ...
        userName= (EditText) findViewById(R.id.nameEditText);
        userAge = (EditText) findViewById(R.id.ageEditText);
        userGendear = (EditText) findViewById(R.id.genderEditText);
        userId = (EditText) findViewById(R.id.idEditText);
            // button ......
        saveButton = (Button) findViewById(R.id.saveButton);
        showButton = (Button) findViewById(R.id.showButton);
        clearButton = (Button) findViewById(R.id.clearButton);
        updatButton = (Button) findViewById(R.id.updateButton);
        deleteButton = (Button) findViewById(R.id.deleteButton);

        // close .....

        // set Listenar ......
        saveButton.setOnClickListener(this);
        showButton.setOnClickListener(this);
        clearButton.setOnClickListener(this);
        updatButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        String name = userName.getText().toString();
        String age = userAge.getText().toString();
        String gendear = userGendear.getText().toString();
        String id = userId.getText().toString();

        // clear EditText ...
        if(v.getId() == R.id.clearButton){
            userName.setText("");
            userAge.setText("");
            userGendear.setText("");
        }

        // save Data SQLite.....

               else if(v.getId() == R.id.saveButton){
                  long rowId = myClass.inseartData(name,age,gendear);

                  if(rowId == -1){
                      Toast.makeText(MainActivity.this,"Data not saved",Toast.LENGTH_LONG).show();
                  }else {
                      Toast.makeText(MainActivity.this,"Data is saved \n successfully",Toast.LENGTH_LONG).show();
                  }
                }
        // Show  Data from SQLite ....

                       else if(v.getId() == R.id.showButton){

                           Cursor cursor = myClass.displayAlldata();
                           if(cursor.getCount() == 0){
                               showData("Error! : ", "Their is no data ");
                               // their is no data ....
                           }

                               StringBuffer stringBuffer = new StringBuffer();
                               while (cursor.moveToNext()){
                                   stringBuffer.append("ID     : "+cursor.getString(0)+"\n");
                                   stringBuffer.append("Name   : "+cursor.getString(1)+"\n");
                                   stringBuffer.append("Age    : "+cursor.getString(2)+"\n");
                                   stringBuffer.append("Gender : "+cursor.getString(3)+"\n\n");
                               }
                               showData("SQLite Data : ", stringBuffer.toString());
                            }



        // update Data........

                                      else   if(v.getId() == R.id.updateButton){
                                          boolean isUpdate =  myClass.updateData(id,name,age,gendear);
                                          if(isUpdate == true){
                                              Toast.makeText(this,"Data is Updated \n successfully",Toast.LENGTH_LONG).show();
                                          }else {
                                              Toast.makeText(this,"Data Updated failed",Toast.LENGTH_LONG).show();
                                          }
                                        }

        // delete Data ...

                 else if (v.getId() == R.id.deleteButton){
                   int value =  myClass.deleteData(id);
                   if(value>0){
                       Toast.makeText(this,id+ " Data is deleted",Toast.LENGTH_LONG).show();
                   }else {
                       Toast.makeText(this,id+ " Their is no Data ",Toast.LENGTH_LONG).show();
                   }
                 }

    }










    // this method use for --->>> showButton ......
    public  void showData(String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(true);
        builder.show();
    }

}
