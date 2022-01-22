package com.babulgam.facebookdemo.studentinformationsqldatabase;



import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowDataActivity extends AppCompatActivity {

    private ListView listView;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);

        listView=findViewById(R.id.listView_id);
        databaseHelper=new DatabaseHelper(this);

        Lorddata();
    }


    public void Lorddata()
    {
        ArrayList<String> listData =new ArrayList<>();

        Cursor cursor=databaseHelper.showalldata();

        if (cursor.getCount() == 0)
        {
            Toast.makeText(this, "No Data Found", Toast.LENGTH_SHORT).show();
        }else

        {
            while (cursor.moveToNext())
            {
                listData.add(cursor.getString(0)+ "\n"+ cursor.getString(1));
            }
        }

        final ArrayAdapter<String> adapter =new ArrayAdapter<String>(this,R.layout.showdatalistview,R.id.listitemTextView_id,listData);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String selectedvalue=parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "selected Value: "+ selectedvalue, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
