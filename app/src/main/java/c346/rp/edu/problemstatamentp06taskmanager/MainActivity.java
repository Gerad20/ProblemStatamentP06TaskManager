package c346.rp.edu.problemstatamentp06taskmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;


import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button addtasks;
    ListView displayTasks;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addtasks = findViewById(R.id.button2);
        displayTasks = findViewById(R.id.displayTask);
        DBHelper db = new DBHelper(MainActivity.this);

        ArrayList<TaskClass> data = db.getAllTasks("");
        db.close();
        ArrayAdapter aa = new taskArrayAdapter(getApplicationContext(), R.layout.custom_list_view,data);
        displayTasks.setAdapter(aa);
        addtasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), addActivity.class);
                startActivityForResult(i, 9);


            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 9){
            DBHelper db = new DBHelper(MainActivity.this);

            ArrayList<TaskClass> datas = db.getAllTasks("");
            db.close();
            ArrayAdapter aa = new taskArrayAdapter(getApplicationContext(), R.layout.custom_list_view,datas);
            displayTasks.setAdapter(aa);

        }
    }
}
