package com.azer.leaderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.ViewDebug;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TestActivity extends AppCompatActivity {

    ArrayList<TestMadel> tests;

    TextView question;
    TextView test_title;
    Button next;
    ImageView back;

    TextView yes;
    TextView no;

    TextView selected;

    int score;
    int currPosition;


    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_test);

            question = findViewById(R.id.question);
            yes = findViewById(R.id.YesBtn);
            no = findViewById(R.id.NoBtn);
            next = findViewById(R.id.nextbtn);
            test_title = findViewById(R.id.test_title);
            back = findViewById(R.id.test_back);

            back.setOnClickListener( view -> {
                this.finish();
            });

            getTests();

            currPosition = 0;
            score = 0;
            test_title.setText(String.valueOf(currPosition+1) + " из " + String.valueOf(tests.size()));

            question.setText(tests.get(currPosition).question);

            yes.setOnClickListener(view -> {
                selected = yes;

                yes.setBackgroundResource(R.drawable.selected);
                no.setBackgroundResource(R.drawable.unselected);
            });

            no.setOnClickListener(view -> {
                selected = no;
                no.setBackgroundResource(R.drawable.selected);
                yes.setBackgroundResource(R.drawable.unselected);
            });

            next.setOnClickListener(view -> {
                if(selected != null)
                {
                    if(currPosition < tests.size() - 1)
                    {
                        score += (selected == yes) == tests.get(currPosition).is_yes ? 1 : 0;
                        currPosition++;
                        question.setText(tests.get(currPosition).question);
                        selected.setBackgroundResource(R.drawable.unselected);
                        selected = null;
                        test_title.setText(String.valueOf(currPosition+1) + " из " + String.valueOf(tests.size()));
                    }
                    else
                    {
                        score += (selected == yes) == tests.get(currPosition).is_yes ? 1 : 0;
                        selected.setBackgroundResource(R.drawable.unselected);
                        selected = null;

                        //Toast.makeText(this, String.valueOf(score), Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(this, ResultActivity.class);
                        String s;
                        if(score * 5 <= 30){
                            s = "Увы, хотя вы часто бываете правы, убедить в этом окружающих вам удается далеко не всегда. Вы считаете, что ваша жизнь и жизнь окружающих должны быть подчинены строгой дисциплине, здравому рассудку и хорошим манерам и ход ее должен быть вполне предсказуемым. Вы не любите ничего делать через силу. При этом вы часто бываете слишком сдержанны, не достигая из-за этого желанной цели, а часто оказываясь и неправильно понятым.";
                        }
                        else{
                            s = "Вы человек, который обладает великолепными предпосылками, чтобы эффективно влиять на других, менять их модели поведения, учить, управлять, наставлять на путь истинный. В подобного рода ситуациях вы обычно чувствуете себя как рыба в воде. Вы наделены даром убеждать окружающих в своей правоте. Однако вам надо быть очень осторожным, чтобы ваша позиция не стала чрезмерно агрессивной. В том случае вы легко можете превратиться в фанатика или тирана.";
                        }
                        intent.putExtra("result", s);
                        startActivity(intent);
                        finish();
                    }
                }
                else
                {
                    Toast.makeText(this, "Выберите один из вариантов!", Toast.LENGTH_SHORT).show();
                }
            });

        }
        catch(Exception ex)
        {

            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
            this.finish();
        }

    }


    void getTests()
    {
        DBAccess dbAccess = DBAccess.getInstance(getApplicationContext());
        dbAccess.open();

        tests = dbAccess.getData();

        dbAccess.clise();
    }
}