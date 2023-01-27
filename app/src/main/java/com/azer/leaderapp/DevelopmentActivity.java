package com.azer.leaderapp;

import androidx.appcompat.app.AppCompatActivity;


import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DevelopmentActivity extends AppCompatActivity {

    TextView title;
    LinearLayout mainContainer;
    ArrayList<DevelopmentModel> devData;
    int parent = 0;
    int layer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            setContentView(R.layout.activity_development);


            ImageView back = findViewById(R.id.dev_back);
            back.setOnClickListener(view ->
            {
                if(layer == 3)
                {
                    getSubParentData(parent);
                    createContent();
                }
                else if(layer == 2)
                {

                    getStartData();
                    createContent();
                }
                else
                {
                    this.finish();
                }
            });

            title = findViewById(R.id.dev_title);

            mainContainer = findViewById(R.id.dev_conatainer);

            getStartData();
            createContent();

        }
        catch (Exception exception)
        {
            Toast.makeText(this, exception.getMessage(), Toast.LENGTH_LONG).show();
        }

    }


    private void createContent()
    {
        mainContainer.removeAllViews();
        for(int i=0; i < devData.size(); i++)
        {
            if(devData.get(i).is_parent)
            {
                TextView txt = new TextView(this);
                txt.setText(devData.get(i).content);
                txt.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 22);
                txt.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.setMargins(getpx(5), getpx(20), getpx(5), getpx(20));
                txt.setLayoutParams(params);
                txt.setTextColor(Color.parseColor("#000000"));
                txt.setTypeface(txt.getTypeface(), Typeface.BOLD);
                txt.setOnClickListener(view ->
                {
                    if (layer == 1)
                    {
                        getSubParentData(devData.get(mainContainer.indexOfChild(txt)).id);
                    } else
                    {
                        getChildData(devData.get(mainContainer.indexOfChild(txt)).id);
                    }

                    createContent();
                });
                mainContainer.addView(txt);
            }
            else
            {
                if(devData.get(i).type == 2)
                {
                    TextView txt = new TextView(this);
                    txt.setText("    " + devData.get(i).content);
                    txt.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                    );
                    params.setMargins(getpx(5), getpx(5), getpx(5), getpx(5));
                    txt.setLayoutParams(params);
                    txt.setTextColor(Color.parseColor("#000000"));
                    mainContainer.addView(txt);
                }
                else if(devData.get(i).type == 1)
                {
                    TextView txt = new TextView(this);
                    txt.setText(devData.get(i).content);
                    txt.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    txt.setTypeface(txt.getTypeface(), Typeface.BOLD);
                    txt.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                    );
                    params.setMargins(getpx(5), getpx(10), getpx(5), getpx(5));
                    txt.setLayoutParams(params);
                    txt.setTextColor(Color.parseColor("#000000"));
                    mainContainer.addView(txt);
                }
                else if(devData.get(i).type == 3)
                {
                    ImageView img = new ImageView(this);

                    try {
                        img.setScaleType(ImageView.ScaleType.FIT_CENTER);
                        Resources res = getResources();
                        String mDrawableName = devData.get(i).content;

                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                getpx(180)
                        );
                        img.setLayoutParams(params);

                        int resID = res.getIdentifier(mDrawableName , "drawable", getPackageName());
                        Drawable drawable = res.getDrawable(resID );
                        img.setImageDrawable(drawable );
                    }
                    catch (Exception ex)
                    {
                        Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    mainContainer.addView(img);
                }
            }
        }
    }


    private void getChildData(int id)
    {
        DBAccess dbAccess = DBAccess.getInstance(getApplicationContext());
        dbAccess.open();

        devData = dbAccess.getDevChildData(id);

        dbAccess.clise();
        layer = 3;
    }


    private void getSubParentData(int id)
    {
        DBAccess dbAccess = DBAccess.getInstance(getApplicationContext());
        dbAccess.open();

        parent = id;

        devData = dbAccess.getDevSubParentData(id);

        dbAccess.clise();
        layer = 2;
    }


    private void getStartData() {
        DBAccess dbAccess = DBAccess.getInstance(getApplicationContext());
        dbAccess.open();

        devData = dbAccess.getDevData();

        dbAccess.clise();
        layer = 1;
    }


    int getpx(int dp)
    {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }
}