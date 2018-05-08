package com.example.ying.a105590026_hw4_1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button mBtnOK;
    private TextView mTxtSug;
    private CheckBox mMusic;
    private CheckBox mSing;
    private CheckBox mDance;
    private CheckBox mTravel;
    private CheckBox mReading;
    private CheckBox mWriting;
    private CheckBox mClimbing;
    private CheckBox mSwim;
    private CheckBox mEating;
    private CheckBox mDrawing;
    private String mSelectedSex;
    private Spinner mAgeSpin;
    private RadioGroup mSexGrp;
    private RadioButton mMaleBtn;
    private RadioButton mFemaleBtn;
    private TextView mTextInterset;
    private String selectAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnOK = (Button) findViewById(R.id.btnOK);
        mSexGrp = (RadioGroup) findViewById(R.id.SexGrp);
        mMaleBtn = (RadioButton) findViewById(R.id.MaleBtn);
        mFemaleBtn = (RadioButton) findViewById(R.id.FemaleBtn);
        mMusic = (CheckBox) findViewById(R.id.music);
        mSing = (CheckBox) findViewById(R.id.sing);
        mDance = (CheckBox) findViewById(R.id.dance);
        mTravel = (CheckBox) findViewById(R.id.travel);
        mReading = (CheckBox) findViewById(R.id.reading);
        mWriting = (CheckBox) findViewById(R.id.writing);
        mClimbing = (CheckBox) findViewById(R.id.climbing);
        mSwim = (CheckBox) findViewById(R.id.swim);
        mEating = (CheckBox) findViewById(R.id.eating);
        mDrawing = (CheckBox) findViewById(R.id.drawing);
        mTxtSug = (TextView) findViewById(R.id.txtSug);
        mTextInterset = (TextView) findViewById(R.id.txtInterest);
        mAgeSpin = (Spinner) findViewById(R.id.AgeSpin);
        mBtnOK.setOnClickListener(btnOKOnClick);
    }

    private View.OnClickListener btnOKOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v){
            String strAge = mAgeSpin.getSelectedItem().toString();
            String s = "你的興趣：";
            switch (mSexGrp.getCheckedRadioButtonId()){
                case R.id.MaleBtn:
                    if(strAge.equals("(男)小於30歲 / (女)小於28歲")){
                        mTxtSug.setText("婚姻建議：還不急");
                    }
                    if(strAge.equals("(男)30~40歲 / (女)28歲~35歲")){
                        mTxtSug.setText("婚姻建議：趕快結婚");
                    }
                    if(strAge.equals("(男)40歲以上 / (女)大於35歲")){
                        mTxtSug.setText("婚姻建議：開始找對象");
                    }
                    //break;
                case R.id.FemaleBtn:
                    if(strAge.equals("(男)小於30歲 / (女)小於28歲")){
                        mTxtSug.setText("婚姻建議：還不急");
                    }
                    if(strAge.equals("(男)30~40歲 / (女)28歲~35歲")){
                        mTxtSug.setText("婚姻建議：趕快結婚");
                    }
                    if(strAge.equals("(男)40歲以上 / (女)大於35歲")){
                        mTxtSug.setText("婚姻建議：開始找對象");
                    }
                    //break;
            }
            if(mMusic.isChecked()){
                s+=mMusic.getText().toString();
            }
            if(mSing.isChecked()){
                s+=mSing.getText().toString();
            }
            if(mDance.isChecked()){
                s+=mDance.getText().toString();
            }
            if(mTravel.isChecked()){
                s+=mTravel.getText().toString();
            }
            if(mReading.isChecked()){
                s+=mReading.getText().toString();
            }
            if(mWriting.isChecked()){
                s+=mWriting.getText().toString();
            }
            if(mClimbing.isChecked()){
                s+=mClimbing.getText().toString();
            }
            if(mSwim.isChecked()){
                s+=mSwim.getText().toString();
            }
            if(mEating.isChecked()){
                s+=mEating.getText().toString();
            }
            if(mDrawing.isChecked()){
                s+=mDrawing.getText().toString();
            }
            mTextInterset.setText(s);
        }
    };
}
