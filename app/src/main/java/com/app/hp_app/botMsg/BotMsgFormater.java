package com.app.hp_app.botMsg;

/**
 * Created by allen on 11/19/2017.
 */

public class BotMsgFormater {

    public static final int BOT_MSG_TYPE_TXT = 0;
    public static final int BOT_MSG_TYPE_HINT = 1;
    public static final int BOT_MSG_IDX_MSG = 0;
    public static final int BOT_MSG_IDX_MSG_TYPE = 1;
    public static final int BOT_MSG_IDX_MSGTO = 2;
    public static final int BOT_MSG_IDX_CHAP = 3;
    public static final int BOT_MSG_IDX_MS = 4;
    public static final String BOT_SPLIT_IND = "!@#";
    public static int gChapter = 0;
    public static int gMilestone = 0;
    public static BotMsgNode parseBotMsg(String str) {
        /*
         *  Data Format:
         *  0: <Text MSg>
         *  1: <MsgNode Type>
         *  2: <Port>
         *  3: <Chapter>
         *  4: <MileStone>
         *
         */
        BotMsgNode botMsgNode;
        String[] strPool = str.split(BOT_SPLIT_IND,BOT_MSG_IDX_MS+2);
        botMsgNode = new BotMsgNode(Integer.parseInt(strPool[BOT_MSG_IDX_MSG_TYPE]),
                Integer.parseInt(strPool[BOT_MSG_IDX_MSGTO]),strPool[BOT_MSG_IDX_MSG]);
        if(Integer.parseInt(strPool[BOT_MSG_IDX_CHAP])!= 0){
            gChapter = Integer.parseInt(strPool[BOT_MSG_IDX_CHAP]);
        }
        if(Integer.parseInt(strPool[BOT_MSG_IDX_MS])!= 0){
            gMilestone = Integer.parseInt(strPool[BOT_MSG_IDX_MS]);
        }
        return botMsgNode;
    }
    public static String formatBotMsg(BotMsgNode node) {
        /*
         *  Data Format:
         *  0: <Text MSg>
         *  1: <MsgNode Type>
         *  2: <Port>
         *  3: <Chapter>
         *  4: <MileStone>
         *
         */
        String strPool[] = {node.msg,Integer.toString(node.msgType),Integer.toString(node.msgTo),
                Integer.toString(gChapter),Integer.toString(gMilestone)};
        String retStr = "";
        for(int i = 0 ; i < strPool.length - 1 ; i++)
            retStr += strPool[i] + BOT_SPLIT_IND;
        retStr += strPool[strPool.length - 1];
        return retStr;
    }
}
