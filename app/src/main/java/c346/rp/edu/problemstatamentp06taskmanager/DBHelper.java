package c346.rp.edu.problemstatamentp06taskmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "Task.db";
    private static final int DATABASE_VERSION = 1;



    private static final String TABLE_TASK = "tasks";
    private static final String COLUMN_ID = "_id";
    private static final String COLOUMN_TASK_NAME = "task_name";
    private static final String COLOUMN_TASK_DESC= "task_desc";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createNoteTableSql = "CREATE TABLE " + TABLE_TASK + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLOUMN_TASK_NAME + " TEXT," +COLOUMN_TASK_DESC + " TEXT ) ";
        db.execSQL(createNoteTableSql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASK);
        onCreate(db);

    }

    public void insertTask(String title, String description) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLOUMN_TASK_NAME, title);
        values.put(COLOUMN_TASK_DESC, description);

        db.insert(TABLE_TASK,null,values);

        db.close();
    }



    public ArrayList<TaskClass> getAllTasks(String keyword) {
        ArrayList<TaskClass> task = new ArrayList<TaskClass>();

        ArrayList<TaskClass> tasks = new ArrayList<TaskClass>();
        String selectQuery = "SELECT *" + " FROM " + TABLE_TASK;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String description = cursor.getString(2);

                TaskClass obj = new TaskClass( title, description);
                obj.set_id(id);
                tasks.add(obj);
            }while(cursor.moveToNext());

        }
        cursor.close();
        return tasks;
    }




}

