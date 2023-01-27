package com.azer.leaderapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class LeadersActivity extends AppCompatActivity
{

    LinearLayout mainContainer;
    ArrayList<LeaderModel> leaders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_leaders);

            ImageView back = findViewById(R.id.leaders_back);
            back.setOnClickListener(view -> {
                this.finish();
            });

            mainContainer = findViewById(R.id.leaders_conatainer);

            getStartData();

            for(int i=0 ; i < leaders.size(); i++)
            {
                // CardView
                CardView card = new CardView(this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.setMargins(getpx(50), getpx(20), getpx(50), getpx(20));
                card.setLayoutParams(params);
                card.setRadius(getpx(25));
                card.setOnClickListener(view -> {
                    Intent intent = new Intent(this, LeaderDetailActivity.class);
                    int index = mainContainer.indexOfChild(card);
                    intent.putExtra("leader_id", leaders.get(index).id);
                    intent.putExtra("leader_name", leaders.get(index).name);
                    startActivity(intent);
                });
                mainContainer.addView(card);

                // LinearLayout
                LinearLayout linear = new LinearLayout(this);
                linear.setOrientation(LinearLayout.VERTICAL);
                linear.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));
                card.addView(linear);

                // ImageView
                ImageView img = new ImageView(this);
                try {
                    img.setScaleType(ImageView.ScaleType.FIT_XY);
                    Resources res = getResources();
                    String mDrawableName = leaders.get(i).img;
                    img.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT, getpx(250)));
                    int resID = res.getIdentifier(mDrawableName , "drawable", getPackageName());
                    Drawable drawable = res.getDrawable(resID );
                    img.setImageDrawable(drawable );
                }
                catch (Exception ex)
                {
                    Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
                linear.addView(img);


                // TextView
                TextView txt = new TextView(this);
                txt.setText(leaders.get(i).name);
                txt.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                txt.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 24);
                LinearLayout.LayoutParams name_params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.setMargins(getpx(5), getpx(5), getpx(5), getpx(5));
                txt.setLayoutParams(name_params);
                linear.addView(txt);

            }




        }
        catch (Exception exception)
        {
            Toast.makeText(this, exception.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    private void getStartData()
    {
        DBAccess dbAccess = DBAccess.getInstance(getApplicationContext());
        dbAccess.open();

        leaders = dbAccess.getAllLeaders();

        dbAccess.clise();
    }
    int getpx(int dp)
    {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }
}