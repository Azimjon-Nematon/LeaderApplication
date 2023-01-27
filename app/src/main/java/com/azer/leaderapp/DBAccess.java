package com.azer.leaderapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;

public class DBAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DBAccess instance;
    Cursor c = null;

    private DBAccess(Context context)
    {
        this.openHelper =new DBHandler(context);
    }

    public static DBAccess getInstance(Context context)
    {
        if(instance == null)
        {
            instance = new DBAccess(context);
        }
        return instance;
    }

    public void open()
    {
        this.db = openHelper.getReadableDatabase();
    }
    public void clise()
    {
        if(db != null)
        {
            this.db.close();
        }
    }

    public ArrayList<TestMadel> getData()
    {
        ArrayList<TestMadel> tests = new ArrayList<>();
        c = db.rawQuery("Select title, is_yes FROM tests", new String[]{});

        while(c.moveToNext())
        {
//            String aa = c.getString(1);
//            int id = c.getInt(0);
//            int is_yes = c.getInt(2);

            tests.add(new TestMadel(c.getString(0), c.getInt(1) == 1));

        }

        return tests;
    }
    public ArrayList<DevelopmentModel> getDevData()
    {
        ArrayList<DevelopmentModel> devData = new ArrayList<>();
        c = db.rawQuery("SELECT id, content, is_parent, parent_id, type FROM development WHERE is_parent = 1 AND parent_id IS NULL ORDER BY sort",
                new String[]{});

        while(c.moveToNext())
        {
            devData.add(
                    new DevelopmentModel(
                            c.getInt(0),
                            c.getString(1),
                            c.getInt(2) == 1,
                            c.getInt(3),
                            c.getInt(4)
                    )
            );
        }

        return devData;
    }

    public ArrayList<DevelopmentModel> getDevSubParentData( int id)
    {
        ArrayList<DevelopmentModel> devData = new ArrayList<>();

        String sql = "SELECT id, content, is_parent, parent_id, type FROM development WHERE is_parent = 1 AND parent_id = "
                + id + " ORDER BY sort";

        c = db.rawQuery(sql, new String[]{});

        while(c.moveToNext())
        {
            devData.add(
                    new DevelopmentModel(
                            c.getInt(0),
                            c.getString(1),
                            c.getInt(2) == 1,
                            c.getInt(3),
                            c.getInt(4)
                    )
            );
        }

        return devData;
    }

    public ArrayList<DevelopmentModel> getDevChildData(int id)
    {
        ArrayList<DevelopmentModel> devData = new ArrayList<>();

        String sql = "SELECT id, content, is_parent, parent_id, type FROM development WHERE parent_id = "
                + id + " ORDER BY sort";

        c = db.rawQuery(sql, new String[]{});

        while(c.moveToNext())
        {
            devData.add(
                    new DevelopmentModel(
                            c.getInt(0),
                            c.getString(1),
                            c.getInt(2) == 1,
                            c.getInt(3),
                            c.getInt(4)
                    )
            );
        }

        return devData;
    }

    public ArrayList<LeaderModel> getAllLeaders()
    {
        ArrayList<LeaderModel> leaders = new ArrayList<>();

        String sql = "SELECT id, full_name, image FROM leaders";

        c = db.rawQuery(sql, new String[]{});

        while(c.moveToNext())
        {
            leaders.add(
                    new LeaderModel(
                            c.getInt(0),
                            c.getString(1),
                            c.getString(2)
                    )
            );
        }

        return leaders;
    }

    public ArrayList<LeaderInfo> getLeader(int id)
    {
        ArrayList<LeaderInfo> leader = new ArrayList<>();

        String sql = "SELECT id, leader_id, content, type FROM leaders_info WHERE leader_id = " +
                id + " ORDER BY sort";

        c = db.rawQuery(sql, new String[]{});

        while(c.moveToNext())
        {
            leader.add(
                    new LeaderInfo(
                            c.getInt(0),
                            c.getInt(1),
                            c.getString(2),
                            c.getInt(3)
                    )
            );
        }

        return leader;
    }

}
