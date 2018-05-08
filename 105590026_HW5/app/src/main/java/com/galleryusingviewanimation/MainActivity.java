package com.galleryusingviewanimation;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity implements ViewSwitcher.ViewFactory {

    private GridView mGridView;
    private ImageSwitcher mImgSwitcher;

    private Integer[] miImgArr = {
            R.drawable.img01, R.drawable.img02, R.drawable.img03,
            R.drawable.img04, R.drawable.img05, R.drawable.img06,
            R.drawable.img07, R.drawable.img08, R.drawable.img09,
            R.drawable.img10, R.drawable.img11, R.drawable.img12};

    private Integer[] miThumbImgArr = {
            R.drawable.img01th, R.drawable.img02th, R.drawable.img03th,
            R.drawable.img04th, R.drawable.img05th, R.drawable.img06th,
            R.drawable.img07th, R.drawable.img08th, R.drawable.img09th,
            R.drawable.img10th, R.drawable.img11th, R.drawable.img12th};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImgSwitcher = (ImageSwitcher) findViewById(R.id.imgSwitcher);

        mImgSwitcher.setFactory(this);	// 主程式類別必須implements ViewSwitcher.ViewFactory
        mImgSwitcher.setInAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.fade_in));
        mImgSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.fade_out));

        ImageAdapter imgAdap = new ImageAdapter(this, miThumbImgArr);

        mGridView = (GridView)findViewById(R.id.gridView);
        mGridView.setAdapter(imgAdap);
        mGridView.setOnItemClickListener(gridViewOnItemClick);
    }

    @Override
    public View makeView() {
        ImageView v = new ImageView(this);
        v.setBackgroundColor(0xFF000000);
        v.setScaleType(ImageView.ScaleType.FIT_CENTER);
        v.setLayoutParams(new ImageSwitcher.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));
        v.setBackgroundColor(Color.WHITE);
        return v;
    }

    private AdapterView.OnItemClickListener gridViewOnItemClick = new
            AdapterView.OnItemClickListener() {
            private Animation alpha_in = new AlphaAnimation(0,1);
            private Animation alpha_out = new AlphaAnimation(1,0);

            private Animation trans_in = new TranslateAnimation(0,0,-8000,0);
            private Animation trans_out = new TranslateAnimation(0,0,0,8000);

            private Animation scale_in = new ScaleAnimation(0,1,0,1,10,10);
            private Animation scale_out = new ScaleAnimation(1,0,1,0,360,360);

            private Animation rotate_in = new RotateAnimation(0,360,800,800);
            private Animation rotate_out = new RotateAnimation(0,360,36,360);

            AnimationSet scale_rotate_in = new AnimationSet(false);
            AnimationSet scale_rotate_out = new AnimationSet(false);

            AnimationSet scale_rotate_trans_in = new AnimationSet(false);
            AnimationSet scale_rotate_trans_out = new AnimationSet(false);

                @Override
                public void onItemClick(AdapterView<?> parent,
                                        View v,
                                        int position,
                                        long id) {
                    alpha_in.setDuration(3000);
                    alpha_out.setDuration(3000);

                    trans_in.setDuration(3000);
                    trans_out.setDuration(3000);

                    rotate_in.setStartOffset(1000);
                    rotate_in.setDuration(3000);

                    rotate_out.setStartOffset(1000);
                    rotate_out.setDuration(3000);

                    scale_rotate_in.addAnimation(scale_in);
                    scale_rotate_in.addAnimation(rotate_in);
                    scale_rotate_out.addAnimation(scale_out);
                    scale_rotate_out.addAnimation(rotate_out);

                    scale_rotate_trans_in.addAnimation(scale_in);
                    scale_rotate_trans_in.addAnimation(rotate_in);
                    scale_rotate_trans_in.addAnimation(trans_in);

                    scale_rotate_trans_out.addAnimation(scale_out);
                    scale_rotate_trans_out.addAnimation(rotate_out);
                    scale_rotate_trans_out.addAnimation(trans_out);

                    switch ((int)(Math.random()*4 + 1)) {
                        case 1:
                            mImgSwitcher.setInAnimation(alpha_in);
                            mImgSwitcher.setOutAnimation(alpha_out);
                            break;
                        case 2:
                            mImgSwitcher.setInAnimation(trans_in);
                            mImgSwitcher.setOutAnimation(trans_out);
                            break;
                        case 3:
                            mImgSwitcher.setInAnimation(scale_rotate_in);
                            mImgSwitcher.setOutAnimation(scale_rotate_out);
                            break;
                        case 4:
                            mImgSwitcher.setInAnimation(scale_rotate_trans_in);
                            mImgSwitcher.setOutAnimation(scale_rotate_trans_out);
                    }

                    mImgSwitcher.setImageResource(miImgArr[position]);
                }
            };
}
