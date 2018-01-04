package com.example.shopify;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;



public class Detail extends Activity {
    TextView title;
    TextView des;
    ImageView img;


    int position;
    String ti;
    String to;
    String im;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);
        Intent i=getIntent();
       position=i.getExtras().getInt("position");
        to=i.getStringExtra("toString");
        im=i.getStringExtra("img");
        ti=i.getStringExtra("title");
        title=(TextView)findViewById(R.id.title);
        des=(TextView)findViewById(R.id.des);
        img=(ImageView)findViewById(R.id.img);


        Picasso.with(this).load(im).into(img);

        title.setText(ti);
        des.setText(to);
        des.setMovementMethod(new ScrollingMovementMethod());

    }

}
