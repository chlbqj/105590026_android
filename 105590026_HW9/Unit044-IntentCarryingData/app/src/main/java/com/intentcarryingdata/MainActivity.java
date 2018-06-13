package com.intentcarryingdata;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> data;
    private ArrayAdapter<CharSequence> arrayAdapter;

    private Spinner mSpinItem;
    private EditText mEditTxtDate,mEditTxtCost;
    private DatePicker mDatePicker;
    private CalendarView mCalendatView;
    private Button mBtnInput,mBtnRecord;
    private Intent intent;
    private int counts;

    private static final int MENU_MUSIC = Menu.FIRST,
            MENU_PLAY_MUSIC = Menu.FIRST + 1,
            MENU_STOP_MUSIC = Menu.FIRST + 2,
            MENU_ABOUT = Menu.FIRST + 3,
            MENU_EXIT = Menu.FIRST + 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSpinItem = (Spinner) findViewById(R.id.spinItem);
        mEditTxtCost = (EditText) findViewById(R.id.editTxtCost);
        mEditTxtDate = (EditText) findViewById(R.id.editTxtDate);
        mDatePicker = (DatePicker) findViewById(R.id.datePicker);
        mCalendatView = (CalendarView) findViewById(R.id.calendarView2);
        mBtnInput = (Button) findViewById(R.id.btnInput);
        mBtnRecord = (Button) findViewById(R.id.btnRecord);

        mBtnInput.setOnClickListener(onBtnInputClick);
        mBtnRecord.setOnClickListener(onBtnRecordClick);
        mCalendatView.setOnDateChangeListener(onDateChangeListener);

        Calendar calendar = Calendar.getInstance();
        mDatePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), this.onDpkchange);

        arrayAdapter = ArrayAdapter.createFromResource(this,R.array.meal,android.R.layout.simple_spinner_dropdown_item);
        mSpinItem.setAdapter(arrayAdapter);
        String result = String.valueOf(mDatePicker.getYear() + "/" + String.valueOf(mDatePicker.getMonth() + 1) + "/" + String.valueOf(mDatePicker.getDayOfMonth()));
        mEditTxtDate.setText(result);
        data = new ArrayList();
        counts = 0;
        intent = new Intent();
        intent.setClass(MainActivity.this,GameResultActivity.class);
    }


    @Override
    public boolean onContextItemSelected(MenuItem item){
        onOptionsItemSelected(item);
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        SubMenu subMenu = menu.addSubMenu(0, MENU_MUSIC, 0, "背景音樂").setIcon(android.R.drawable.ic_media_ff);
        subMenu.add(0, MENU_PLAY_MUSIC, 0, "播放背景音樂");
        subMenu.add(0, MENU_STOP_MUSIC, 1, "停止播放背景音樂");
        menu.add(0, MENU_ABOUT, 1, "關於這個程式...");
        menu.add(0, MENU_EXIT, 2, "結束").setIcon(android.R.drawable.ic_menu_close_clear_cancel);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case MENU_PLAY_MUSIC:
                Intent it = new Intent(MainActivity.this, MediaPlayService.class);
                startService(it);
                return true;
            case MENU_STOP_MUSIC:
                it = new Intent(MainActivity.this, MediaPlayService.class);
                stopService(it);
                return true;
            case MENU_ABOUT:
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("關於這個程式")
                        .setMessage("選單範例程式")
                        .setCancelable(false)
                        .setIcon(android.R.drawable.star_big_on)
                        .setPositiveButton("確定",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                })
                        .show();

                return true;
            case MENU_EXIT:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private final View.OnClickListener onBtnInputClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String cost = mEditTxtCost.getText().toString();
            String item = mSpinItem.getSelectedItem().toString();
            String date = mEditTxtDate.getText().toString();

            String result = "項目" + String.valueOf(counts++) + "  " + date + "  " + item + "  " + cost;
            data.add(result);
            Toast.makeText(MainActivity.this, cost, Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener onBtnRecordClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            intent.putStringArrayListExtra("data",data);
            startActivity(intent);
        }
    };

    public DatePicker.OnDateChangedListener onDpkchange = new DatePicker.OnDateChangedListener() {
        @Override
        public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            String result = String.valueOf(year) + "/"  + String.valueOf(monthOfYear + 1) + "/" + String.valueOf(dayOfMonth);
            Calendar calendar = Calendar.getInstance();
            calendar.set(year,monthOfYear,dayOfMonth);
            mCalendatView.setDate(calendar.getTimeInMillis());
            mEditTxtDate.setText(result);
        }
    };

    public CalendarView.OnDateChangeListener onDateChangeListener = new CalendarView.OnDateChangeListener() {
        @Override
        public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
            String result = String.valueOf(year) + "/"  + String.valueOf(month + 1) + "/" + String.valueOf(dayOfMonth);
            mEditTxtDate.setText(result);
            mDatePicker.updateDate(year,month,dayOfMonth);
        }
    };
}
