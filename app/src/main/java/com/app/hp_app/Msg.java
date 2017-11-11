package com.app.hp_app;

/**
 * Created by Lan on 10/22/17.
 */

public class Msg {

    public static final int TypeReceived = 0;
    public static final int TypeSent = 1;
    private int imgId;
    private String messageContent;
    private int type;

    public Msg (String messageContent, int type, int imgId){
        this.messageContent = messageContent;
        this.type = type;
        this.imgId = imgId;
    }
    public String getMessage(){
        return messageContent;
    }
    public int getType(){
        return type;
    }
    public int getImgId(){
        return this.imgId;
    }
}
