package com.azer.leaderapp;

public class DevelopmentModel
{
    public int id;

    public String content;

    public boolean is_parent;

    public int parent_id;

    public int type;

    DevelopmentModel(int id, String content, boolean is_parent, int parent_id, int type)
    {
        this.id = id;
        this.content = content;
        this.is_parent = is_parent;
        this.parent_id = parent_id;
        this.type = type;
    }

}
