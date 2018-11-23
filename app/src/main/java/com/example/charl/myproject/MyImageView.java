package com.example.charl.myproject;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.squareup.picasso.Picasso;

public class MyImageView extends AppCompatImageView {

    public MyImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setImageURL(String url){
        Picasso.with(getContext()).load(url).into(this);
    }
}