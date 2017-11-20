package com.app.hp_app.charMenu;

/**
 * Created by allen on 11/19/2017.
 */

public class CharDict{
    public int showUpidx;
    public String name;
    public int showUpChapter;
    public int showUpMileStone;
    public boolean locked;
    public boolean oneShot;
    public String startCmd;
    public String[] charSet;//Possibally serveral characters in one chat
    public int imgId;
    public CharDict(int showUpidx, String name, int showUpChapter, int showUpMileStone, boolean locked,boolean oneShot, String startCmd, String[] charSet, int imgId){
        this.showUpidx = showUpidx;
        this.name = name;
        this.showUpChapter = showUpChapter;
        this.showUpMileStone = showUpMileStone;
        this.locked = locked;
        this.oneShot = oneShot;
        this.startCmd = startCmd;
        this.charSet = charSet;
        this.imgId = imgId;
    }
}