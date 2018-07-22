package com.geetha.broadcastreciever;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rdl on 19-07-2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    private static final String DB_NAME = "SMS_DATA_DB";
    private static final String TABLE_NAME = "SMS_DATA";
    private static final int DB_VERSION = 1;

    private static final String COL_SENDER = "SENDER";
    private static final String COL_SMS_DATE = "SMS_DATE";

    private static final String COL_SMS_TEXT = "SMS_TEXT";

    private static final String CREATE_TABLE_STMT =
            "CREATE TABLE " + TABLE_NAME + "("
            + COL_SENDER + " VARCHAR(14), "
            + COL_SMS_DATE + " VARCHAR(10), "
            + COL_SMS_TEXT + " VARCHAR(160)"
            + ")";

    private static final String SELECT_STMT = "SELECT * FROM " + TABLE_NAME;

    private Context context;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context =context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "onCreate: " + CREATE_TABLE_STMT);
        db.execSQL(CREATE_TABLE_STMT);
    }

    public void insertData(SmsModel textData) {
        SQLiteDatabase db = getWritableDatabase();
        Log.i(TAG, "insertData: " + textData);
        ContentValues values = new ContentValues();
        values.put(COL_SENDER, textData.getSender());
        values.put(COL_SMS_DATE, textData.getMsgDate());
        values.put(COL_SMS_TEXT, textData.getMsg());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<SmsModel> getAllData() {
        List<SmsModel> textList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(SELECT_STMT, null);

        if (cursor.moveToFirst()) {
            do {
                SmsModel text = new SmsModel();
                text.setSender(cursor.getString(0));
                text.setMsgDate(cursor.getString(1));
                text.setMsg(cursor.getString(2));
                textList.add(text);
            }
            while (cursor.moveToNext());
        }

        return textList;
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
