package com.app.hp_app.chapter;

import com.app.hp_app.charMenu.CharDict;

/**
 * Created by allen on 11/20/2017.
 */

public class ChapterDict {
    public static int mileStone;
    public int endPoint;
    public boolean locked;
    public String chapterName;
    public String subtitle;
    ChapterDict(int mileStone,int endPoint,boolean locked,String chapterName,String subtitle){
        this.mileStone = mileStone;
        this.endPoint = endPoint;
        this.locked = locked;
        this.chapterName = chapterName;
        this.subtitle = subtitle;
    }
}
