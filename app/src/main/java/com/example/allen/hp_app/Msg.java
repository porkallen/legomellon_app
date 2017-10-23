package com.example.allen.hp_app;

/**
 * Created by Lan on 10/22/17.
 */

public class Msg {

    public static final int TypeReceived = 0;
    public static final int TypeSent = 1;


    private String messageContent;
    private int type;

    public Msg (String messageContent, int type){

        this.messageContent = messageContent;
        this.type = type;
    }

    public String getMessage(){
        return messageContent;
    }


    public int getType(){
        return type;
    }
}
