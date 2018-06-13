package com.intentcarryingdata;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class GameResultActivity extends AppCompatActivity {

    private Intent intent;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_result);

        listView = (ListView) findViewById(R.id.listView);
        intent = getIntent();

        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1);
        arrayAdapter.addAll(intent.getStringArrayListExtra("data"));
        listView.setAdapter(arrayAdapter);
    }
}
