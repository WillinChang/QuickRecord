 package com.cppor.quickrecord;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

import roboguice.inject.InjectView;



public class MainActivity extends ActionBarActivity {

    TextView mTv;
    DB mDbHelper;
    AlertDialog mDefineDialog;
    View mDialoglayout;

    public static String TAG = "com.cppor.quickrecord.MainActivity";

    class MyButtonClick implements View.OnClickListener
    {
        @Override
        public void onClick(View view) {
            Button button = (Button)view;
            String a = mTv.getText().toString() + button.getText().toString();
            mTv.setText(a);
        }
    }

    MyButtonClick mClick = new MyButtonClick();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTv = (TextView)findViewById(R.id.textView);
        mDbHelper = new DB(this);

        int[] a = new int[]{R.id.button_0, R.id.button_1, R.id.button_2, R.id.button_3, R.id.button_4, R.id.button_5,
                R.id.button_6,R.id.button_7,R.id.button_8,R.id.button_9,R.id.button_0, R.id.button__};
        for (int i : a){
            Button btn = (Button)findViewById(i);
            btn.setOnClickListener(mClick);
        }

        Button btn = (Button)findViewById(R.id.button_);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String val = mTv.getText().toString();
                if (val != null && val.length() > 0) {
                    SQLiteDatabase db = mDbHelper.getWritableDatabase();
                    ContentValues cv = new ContentValues();
                    cv.put(DB.T_DATA_[DB.T_DATA_TIME], DB.getDateTime(null));
                    cv.put(DB.T_DATA_[DB.T_DATA_VALUE], val);
                    cv.put(DB.T_DATA_[DB.T_DATA_DEINE], 0);

                    db.insert(DB.T_DATA, null, cv);

                    Toast.makeText(MainActivity.this, "insert into ", Toast.LENGTH_LONG).show();
                }
            }
        });

        RecyclerView rv = (RecyclerView)findViewById(R.id.dataList);
        rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        ArrayList<String> dbData = ReadDB();
        String[] dbDataArray = new String[]{};
        dbDataArray = dbData.toArray(dbDataArray);
        Log.d(TAG,"data:" +dbDataArray.length);
        MyRecyclerAdapter adapter = new MyRecyclerAdapter(dbDataArray);
        rv.setAdapter(adapter);
    }

    public void ReadAllDefine() {

    }

    public ArrayList<String> ReadDB() {
        ArrayList<String> result = new ArrayList<>();
        SQLiteDatabase db =mDbHelper.getReadableDatabase();
        Cursor cur = db.query(DB.T_DATA, null, null, null, null, null, null);
        int valueIndex  = cur.getColumnIndex(DB.T_DATA_[DB.T_DATA_VALUE]);
        int timeIndex = cur.getColumnIndex(DB.T_DATA_[DB.T_DATA_TIME]);
        while (cur.moveToNext()) {
            String value = cur.getString(valueIndex);
            String dt = cur.getString(timeIndex);

            result.add(value + ":" + dt);
        }

        return result;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_add_define) {
            createAlertDialog();
            mDefineDialog.show();
        }

        return super.onOptionsItemSelected(item);
    }

    private void createAlertDialog() {
        if (mDefineDialog == null) {
            mDialoglayout = getLayoutInflater().inflate(R.layout.dialog_add_define, (ViewGroup)findViewById(R.id.dialog_layout));
            mDefineDialog = new AlertDialog.Builder(MainActivity.this)
                    .setTitle("添加分类")
                    .setView(mDialoglayout)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            EditText tv = (EditText)mDialoglayout.findViewById(R.id.txt_define_name);
                            
                            Toast.makeText(MainActivity.this, tv.getText().toString(), Toast.LENGTH_LONG).show();
                        }
                    })
                    .setNegativeButton("取消", null)
                    .create();

        }
    }
}
