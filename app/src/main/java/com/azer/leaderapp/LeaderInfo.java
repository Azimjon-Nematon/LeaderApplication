package com.azer.leaderapp;

public class LeaderInfo
{
    public int id;
    public int leader_id;
    public String content;
    public int type;

    public LeaderInfo(int id, int leader_id, String content, int type)
    {
        this.id = id;
        this.leader_id = leader_id;
        this.content = content;
        this.type = type;
    }
}
