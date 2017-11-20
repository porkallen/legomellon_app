package com.app.hp_app.charMenu;

import com.app.hp_app.R;
import com.app.hp_app.botMsg.BotNameList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by allen on 11/19/2017.
 */


public class CharPool {
    public List<List<CharDict>> charPool = new ArrayList<List<CharDict>>();
    public CharPool(){
        List<CharDict> tmpList;
        String[] tmpCharSet;
        //Chapter 1
        tmpList= new ArrayList<CharDict>();
        tmpCharSet = new String[2];
        tmpCharSet[0] = BotNameList.botName[0];
        tmpCharSet[1] = BotNameList.botName[1];
        tmpList.add(new CharDict(0,0,"Dudley Family",tmpCharSet, R.drawable.dudley_family));
        tmpCharSet = new String[1];
        tmpCharSet[0] = BotNameList.botName[3];
        tmpList.add(new CharDict(1,1,"Hagrid",tmpCharSet, R.drawable.hogford));
        charPool.add(tmpList);
        //Chapter 2
        tmpList= new ArrayList<CharDict>();
        tmpCharSet = new String[1];
        tmpCharSet[0] = BotNameList.botName[4];
        tmpList.add(new CharDict(0,0,"Hagrid",tmpCharSet, R.drawable.hagrid));
        tmpCharSet = new String[1];
        tmpCharSet[0] = BotNameList.botName[5];
        tmpList.add(new CharDict(1,1,"Ollivander",tmpCharSet, R.drawable.ollivander));
        charPool.add(tmpList);
    }
}
