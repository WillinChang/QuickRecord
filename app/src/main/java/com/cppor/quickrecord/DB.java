package com.cppor.quickrecord;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by willin on 5/15/15.
 */
public class DB extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="quick_record_db";
    private static final int DATABASE_VERSION = 1;

    public static final String T_DEFINE ="T_DEFINE";
    public static final int T_DEFINE_ID = 0;
    public static final int T_DEFINE_NAME = 1;
    public static final String[] T_DEFINE_ = new String[]{
            "_ID","_NAME"
    };

    public static final String T_DATA = "T_DATA";
    public static final int T_DATA_ID = 0;
    public static final int T_DATA_VALUE = 1;
    public static final int T_DATA_DEINE = 2;
    public static final int T_DATA_TIME = 3;
    public static final String[] T_DATA_ = new String[] {
            "_ID", "_VALUE", "_DEFINE", "_TIME"
    };


    // android not define the

    private String[] getTableCreateSqls() {
        // table field define

        String sql1 = "CREATE TABLE "+T_DEFINE+" ("+
                T_DEFINE_[T_DEFINE_ID]+" INTEGER PRIMARY KEY autoincrement,"+
                T_DEFINE_[T_DEFINE_NAME] +" TEXT"+
                ")";

        // table data
        String sql2 = "CREATE TABLE "+T_DATA+" (" +
                T_DATA_[T_DATA_ID] + " INTEGER PRIMARY KEY autoincrement," +
                T_DATA_[T_DATA_VALUE] + " TEXT," +
                T_DATA_[T_DATA_DEINE] + " INTEGER,"+
                T_DATA_[T_DATA_TIME] + " DATETIME" +
                ")";


        return new String[] {sql1, sql2};
    }


    public DB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DB(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String[] sqls = getTableCreateSqls();
        for (String s:sqls)
            sqLiteDatabase.execSQL(s);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }

    public static String getDateTime(Date dt) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        if (dt == null)
            dt = new Date();

        return format.format(dt);
    }


}
