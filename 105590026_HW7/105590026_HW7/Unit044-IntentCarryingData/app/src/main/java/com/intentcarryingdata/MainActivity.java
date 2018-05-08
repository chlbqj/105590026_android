package com.intentcarryingdata;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {

    private TextView mTxtResult;
    private ImageButton mImgBtnDice;

    // 新增統計遊戲局數和輸贏的變數
    private int miCountSet = 0;
    private static int miCountPlayerWin = 0;
    private static int miCountComWin = 0;
    private static int miCountDraw = 0;

    private Button mBtnShowResult;

    private class StaticHandler extends Handler {
        private final WeakReference<MainActivity> mActivity;

        public StaticHandler(MainActivity activity) {
            mActivity = new WeakReference<MainActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            MainActivity activity = mActivity.get();
            if (activity == null) return;

            int iRand = (int)(Math.random()*6 + 1);

            //String s = activity.getString(R.string.result);
            if(iRand > 4){
                Toast.makeText(MainActivity.this, R.string.player_lose, Toast.LENGTH_LONG).show();
                miCountComWin++;
            }
            else if(iRand >=3 && iRand <= 4){
                Toast.makeText(MainActivity.this, R.string.player_draw, Toast.LENGTH_LONG).show();
                miCountDraw++;
            }
            else{
                Toast.makeText(MainActivity.this, R.string.player_win, Toast.LENGTH_LONG).show();
                miCountPlayerWin++;
            }
            //activity.mTxtResult.setText(s);
            switch (iRand) {
                case 1:
                    activity.mImgBtnDice.setImageResource(R.drawable.dice01);
                    break;
                case 2:
                    activity.mImgBtnDice.setImageResource(R.drawable.dice02);
                    break;
                case 3:
                    activity.mImgBtnDice.setImageResource(R.drawable.dice03);
                    break;
                case 4:
                    activity.mImgBtnDice.setImageResource(R.drawable.dice04);
                    break;
                case 5:
                    activity.mImgBtnDice.setImageResource(R.drawable.dice05);
                    break;
                case 6:
                    activity.mImgBtnDice.setImageResource(R.drawable.dice06);
                    break;
            }

        }
    }

    public final StaticHandler mHandler = new StaticHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mTxtResult = (TextView)findViewById(R.id.txtResult);
        mImgBtnDice = (ImageButton) findViewById(R.id.button_dice);

        mBtnShowResult = (Button)findViewById(R.id.btnShowResult);
        mBtnShowResult.setOnClickListener(btnShowResultOnClick);
        mImgBtnDice.setOnClickListener(mImgBtnDiceOnClick);
    }

    private View.OnClickListener mImgBtnDiceOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            // Decide computer play.
            String s = getString(R.string.result);
            miCountSet++;
//            mEdtCountSet.setText(String.valueOf(miCountSet));

            // 從程式資源中取得動畫檔，設定給ImageView物件，然後開始播放。
            Resources res = getResources();
            final AnimationDrawable animDraw =
                    (AnimationDrawable) res.getDrawable(R.drawable.anim_roll_dice);
            mImgBtnDice.setImageDrawable(animDraw);
            animDraw.start();

            // 啟動background thread進行計時。
            new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        Thread.sleep(5000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    animDraw.stop();
                    mHandler.sendMessage(mHandler.obtainMessage());
                }
            }).start();


        }
    };

    private View.OnClickListener btnShowResultOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            Intent it = new Intent();
            it.setClass(MainActivity.this, GameResultActivity.class);

            Bundle bundle = new Bundle();
            bundle.putInt("KEY_COUNT_SET", miCountSet);
            bundle.putInt("KEY_COUNT_PLAYER_WIN", miCountPlayerWin);
            bundle.putInt("KEY_COUNT_COM_WIN", miCountComWin);
            bundle.putInt("KEY_COUNT_DRAW", miCountDraw);
            it.putExtras(bundle);

            startActivity(it);
        }
    };
}
