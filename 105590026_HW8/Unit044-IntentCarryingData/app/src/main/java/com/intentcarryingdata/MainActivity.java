package com.intentcarryingdata;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
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
