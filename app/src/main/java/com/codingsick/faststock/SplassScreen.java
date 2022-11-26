package com.codingsick.faststock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SplassScreen extends AppCompatActivity {
    private ImageView mImageView;
    private TextView mTextView;
    private LinearLayout layout;
    private Animation mAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(getSupportActionBar()!=null)
            getSupportActionBar().hide();

        mImageView=findViewById(R.id.imageView);
        mTextView=findViewById(R.id.textView);
        layout=findViewById(R.id.linearLayout);

        mAnimation= AnimationUtils.loadAnimation(this,R.anim.custom_animation);
        layout.setAnimation(mAnimation);
        mTextView.setAnimation(mAnimation);

        mAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(SplassScreen.this, LoginActivity.class));
                finish();

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }

    @Override
    protected void onStop() {
        super.onStop();
        //mImageView.startAnimation(mAnimation);
        mTextView.startAnimation(mAnimation);
        layout.setAnimation(mAnimation);
    }

}