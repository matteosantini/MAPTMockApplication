package com.example.maptmockapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {
    // creating a constant variables for database.
    // below variable is database name.
    private static final String DB_NAME = "MAPTMockApplication";
    // below int is database version
    private static final int DB_VERSION = 1;
    // below variable is for table name.
    private static final String TABLE_NAME = "mock_app";
    // below variable is for id column.
    private static final String ID_COL = "id";
    // below variable is for name column
    private static final String NAME_COL = "name";
    // below variable for description column.
    private static final String DESCRIPTION_COL = "description";
    // creating a constructor for database handler.
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + DESCRIPTION_COL + " TEXT)";
        db.execSQL(query);
    }

    // this method is use to add new raw in table.
    public void addNewRaw(String textName, String textDescription) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME_COL, textName);
        values.put(DESCRIPTION_COL, textDescription);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}