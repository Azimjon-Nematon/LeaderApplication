package com.azer.leaderapp;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DBHandler extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "leaderApp.db";
    private static final int DATABASE_VERTION = 1;

    public DBHandler(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERTION);
    }
}
