package com.app.hp_app.botMsg;

/**
 * Created by allen on 11/19/2017.
 */

public class BotMsgNode {
    public int msgType;
    public int msgTo;
    public String msg;
    BotMsgNode(int msgType,int msgTo,String msg){
        this.msgType = msgType;
        this.msgTo = msgTo;
        this.msg = msg;
    }
}
