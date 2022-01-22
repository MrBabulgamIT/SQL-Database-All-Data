package com.babulgam.facebookdemo.studentinformationsqldatabase;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private DatabaseHelper databaseHelper;

    private Button saveBUtton,showButton,updateButton,deletedButton;
    private EditText username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper=new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        saveBUtton=findViewById(R.id.saveButton_id);
        showButton=findViewById(R.id.showButton_id);
        updateButton=findViewById(R.id.updateButton_id);
        deletedButton=findViewById(R.id.deletedButton_id);
        username=findViewById(R.id.UserIdedittext_id);
        password=findViewById(R.id.userpasswordedittext_id);

        saveBUtton.setOnClickListener(this);
        showButton.setOnClickListener(this);
        updateButton.setOnClickListener(this);
        deletedButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        String userName=username.getText().toString();
        String passWord=password.getText().toString();


        if (v.getId()==R.id.saveButton_id)
        {

            if (userName.equals("") && passWord.equals(""))
            {
                Toast.makeText(this, "Please Enter All Data", Toast.LENGTH_SHORT).show();
            }
            else
            {
                long rowData=databaseHelper.Savedata(userName,passWord);
                if (rowData>1)
                {
                    Toast.makeText(this, "Data is insert Successfully ", Toast.LENGTH_SHORT).show();
                    username.setText("");
                    password.setText("");
                }else
                {
                    Toast.makeText(this, "Data is not inserted", Toast.LENGTH_SHORT).show();
                }
            }

        }else

            //show data


            if (v.getId()==R.id.showButton_id)
            {
                Intent intent= new Intent(getApplicationContext(),ShowDataActivity.class);
                startActivity(intent);
            }else

//uplord Data
                if (v.getId()==R.id.updateButton_id)
                {
                    Boolean isupdated=databaseHelper.updateData(userName,passWord) ;

                    if (isupdated==true)
                    {
                        username.setText("");
                        Toast.makeText(this, "Data is updated", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(this, "Data is not updated", Toast.LENGTH_SHORT).show();


                }else


                    //deleted Data

                    if (v.getId()==R.id.deletedButton_id)
                    {

                        int value =databaseHelper.deletedData(userName);

                        if (value<0)
                        {
                            Toast.makeText(this, "Data is not deleted", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(this, "Data deleted successfully", Toast.LENGTH_SHORT).show();

                            username.setText("");
                        }



                    }
    }
}
