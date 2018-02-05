package com.example.vsing.splash_screen;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.net.wifi.p2p.WifiP2pManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.transition.Slide;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import org.w3c.dom.Text;

import javax.crypto.NullCipher;

public class MainActivity extends AppCompatActivity {



    private ViewPager mSlideViewPager;
    private LinearLayout mDotsLayout;

    private TextView[] mDots;

    private SliderAdapter sliderAdapter;

    private Button mNextBtn;
    private Button mBackBtn;
    private Button mStartBtn;
    //private Button mRegisterBtn;

    private int mCurrentPage;
     //public TextView Log_id;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mSlideViewPager = (ViewPager) findViewById(R.id.slideViewPager);
        mDotsLayout = (LinearLayout) findViewById(R.id.dotsLayout);

        mNextBtn = (Button) findViewById(R.id.nextBtn);
        mBackBtn = (Button) findViewById(R.id.prevBtn);


        sliderAdapter = new SliderAdapter(this);

        mSlideViewPager.setAdapter(sliderAdapter);

        addDotsIndicator(0);

        mSlideViewPager.addOnPageChangeListener(viewListener);


        mStartBtn = (Button) findViewById(R.id.startBtn);

        mStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, Login.class);

                startActivity(intent);
                finish();
            }
        });








        //OnClickListeners

        mNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mSlideViewPager.setCurrentItem(mCurrentPage +1 );
            }
        });

        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mSlideViewPager.setCurrentItem(mCurrentPage -1 );
            }
        });

    }

    public void addDotsIndicator(int position){

        mDots = new TextView[3];
        mDotsLayout.removeAllViews();

        for(int i = 0; i < mDots.length; i++){

            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));

            mDotsLayout.addView(mDots[i]);

        }
        if (mDots.length > 0){

            mDots[position].setTextColor(getResources().getColor(R.color.colorWhite));
        }
    }


    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {

            addDotsIndicator(i);

            mCurrentPage = i;
            if (i == 0){

                mNextBtn.setEnabled(true);
                mBackBtn.setEnabled(false);
                mStartBtn.setEnabled(false);
                mBackBtn.setVisibility(View.INVISIBLE);

                mNextBtn.setText("Next");
                mBackBtn.setText("");

            }else if (i == mDots.length -1 ){

                mNextBtn.setEnabled(true);
                mBackBtn.setEnabled(true);
                mStartBtn.setEnabled(true);
                mBackBtn.setVisibility(View.VISIBLE);
                mNextBtn.setVisibility(View.INVISIBLE);
                mStartBtn.setVisibility(View.VISIBLE);
                mStartBtn.setText("Get Started");
                mBackBtn.setText("Back");
            }else {

                mNextBtn.setEnabled(true);
                mBackBtn.setEnabled(true);
                mStartBtn.setEnabled(false);
                mBackBtn.setVisibility(View.VISIBLE);
                mNextBtn.setVisibility(View.VISIBLE);
                mStartBtn.setVisibility(View.INVISIBLE);

                mNextBtn.setText("Next");
                mBackBtn.setText("Back");
            }


        }


        @Override
        public void onPageScrollStateChanged(int i) {

        }

    };


}


