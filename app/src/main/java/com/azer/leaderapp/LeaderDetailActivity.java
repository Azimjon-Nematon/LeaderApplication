package com.azer.leaderapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class LeaderDetailActivity extends AppCompatActivity
{
    TextView leader_name;
    LinearLayout mainContainer;
    ArrayList<LeaderInfo> leader_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_detail);

        try {

            ImageView back = findViewById(R.id.leader_back);
            back.setOnClickListener(view -> {
                this.finish();
            });
            leader_name = findViewById(R.id.leader_name);
            leader_name.setText(this.getIntent().getStringExtra("leader_name"));

            getStartData(this.getIntent().getIntExtra("leader_id", 0));

            mainContainer = findViewById(R.id.leader_info_conatainer);

            for(int i=0 ; i < leader_info.size(); i++)
            {


                /*if(leader_info.get(i).type == 3)
                {
                    ImageView img = new ImageView(this);

                    try {
                        img.setScaleType(ImageView.ScaleType.FIT_CENTER);
                        Resources res = getResources();
                        String mDrawableName = leader_info.get(i).content;

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
                }*/

                if(leader_info.get(i).type == 1)
                {
                    TextView txt = new TextView(this);
                    txt.setText(leader_info.get(i).content);
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
                else
                {
                    TextView txt = new TextView(this);
                    txt.setText("    " + leader_info.get(i).content);
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


            }




        }
        catch (Exception exception)
        {
            Toast.makeText(this, exception.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    private void getStartData(int id)
    {
        DBAccess dbAccess = DBAccess.getInstance(getApplicationContext());
        dbAccess.open();

        leader_info = dbAccess.getLeader(id);

        dbAccess.clise();
    }


    int getpx(int dp)
    {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }
}