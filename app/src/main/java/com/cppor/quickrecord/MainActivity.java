package com.cppor.quickrecord;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import roboguice.inject.InjectView;



public class MainActivity extends ActionBarActivity {

    TextView mTv;

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
                    DB dbHelper = new DB(MainActivity.this);
                    SQLiteDatabase db = dbHelper.getReadableDatabase();
                    ContentValues cv = new ContentValues();
                    cv.put(DB.T_DATA_[DB.T_DATA_TIME], DB.getDateTime(null));
                    cv.put(DB.T_DATA_[DB.T_DATA_VALUE], val);
                    cv.put(DB.T_DATA_[DB.T_DATA_DEINE], 0);

                    db.insert(DB.T_DATA, null, cv);

                    Toast.makeText(MainActivity.this, "insert into ", Toast.LENGTH_LONG).show();
                }
            }
        });

        RecyclerView rv = (RecyclerView)findViewById(R.id.list_item);

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
        }

        return super.onOptionsItemSelected(item);
    }
}
