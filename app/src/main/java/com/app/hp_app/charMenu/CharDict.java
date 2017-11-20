package com.app.hp_app.charMenu;

/**
 * Created by allen on 11/19/2017.
 */

public class CharDict{
    public int showUpidx;
    public int mileStone;
    public String name;
    public String[] charSet;
    public int imgId;
    CharDict(int showUpidx,int mileStone,String name,String[] charSet,int imgId){
        this.showUpidx = showUpidx;
        this.mileStone = mileStone;
        this.name = name;
        this.charSet = charSet;
        this.imgId = imgId;
    }
}