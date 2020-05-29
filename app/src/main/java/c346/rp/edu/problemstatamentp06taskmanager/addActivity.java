package c346.rp.edu.problemstatamentp06taskmanager;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.Serializable;
import java.util.Calendar;


public class addActivity extends AppCompatActivity {
    Button AddTaskBtn;
    Button cancel;
    EditText etTaskName;
    EditText etTaskDesc;
    EditText etTime;
    int reqCode = 12345;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity);
        AddTaskBtn = findViewById(R.id.btnAdd);
        cancel = findViewById(R.id.cancelBtn);
        etTaskName = findViewById(R.id.etTaskName);
        etTaskDesc = findViewById(R.id.etTaskDesc);
        etTime = findViewById(R.id.editTextTime);


        AddTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db = new DBHelper(addActivity.this);
                String Name = etTaskName.getText().toString();
                String Desc =  etTaskDesc.getText().toString();
                int timeInSec = Integer.parseInt(etTime.getText().toString());
                TaskClass newTask = new TaskClass(Name,Desc);


                db.insertTask(Name, Desc);
                db.close();
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                setResult(RESULT_OK, i);


                //setting time for reminder
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.SECOND, timeInSec);

                Intent intent = new Intent(addActivity.this,
                        ScheduledNotificationReceiver.class);
                Bundle args = new Bundle();
                args.putSerializable("objsTask",newTask);
                intent.putExtra("DATA",args);



                PendingIntent pendingIntent = PendingIntent.getBroadcast(
                        addActivity.this, reqCode,
                        intent, PendingIntent.FLAG_CANCEL_CURRENT);


                AlarmManager am = (AlarmManager)
                        getSystemService(Activity.ALARM_SERVICE);
                am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
                        pendingIntent);
                finish();

            }


        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), addActivity.class);
                setResult(RESULT_OK, i);
               finish();
            }
        });

    }
}