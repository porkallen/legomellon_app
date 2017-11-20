package com.app.hp_app.botMsg;

/**
 * Created by allen on 10/2/2017.
 */

import java.io.IOException;
import java.net.Socket;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import android.util.Log;
import android.os.AsyncTask;

import com.app.hp_app.chapter.ChapterPool;
import com.app.hp_app.charMenu.CharPool;
import com.app.hp_app.conversation.MsgLayerView;
import com.app.hp_app.conversation.MsgNode;
import com.app.hp_app.R;

class BotServerInfor {
    public static final String BOT_SERV_IP="35.203.167.236";
    public static final int BOT_SERV_PORT=9999;
}

class BotNode {
    String botname;
    String botID;
    int botPort;
    int imgId;
    BotNode(String name, String id, int port, int imgId){
        this.botname = name;
        this.botID = id;
        this.botPort = port;
        this.imgId = imgId;
    }
}
class BotSet {
    final BotNode HPNode = new BotNode("@Harry Potter ","<@U7HQ4QJR2>",0,0);;
    BotNode[] botMap;
    BotSet(){
        this.botMap = new BotNode[BotNameList.botName.length];
        this.botMap[0] = new BotNode(BotNameList.botName[0],"<@U7JL8RLEQ>",60001, R.drawable.dudley_dursley);
        this.botMap[1] = new BotNode(BotNameList.botName[1],"<@U7JK660E6>",60002,R.drawable.aunt_petunia);
        this.botMap[2] = new BotNode(BotNameList.botName[2],"<@U7N6MU4AC>",60003,R.drawable.bob);
        this.botMap[3] = new BotNode(BotNameList.botName[3],"<@U7NA7RWC9>",60004,R.drawable.hogford);
        this.botMap[4] = new BotNode(BotNameList.botName[4],"<@U7UNYBVB3>",60005,R.drawable.hagrid);
        this.botMap[5] = new BotNode(BotNameList.botName[5],"<@U7VFE8PGV>",60006,R.drawable.ollivander);
    }
}
public class BotMsgHandl extends AsyncTask<String, String, String>{
    private Thread t;
    public MsgLayerView lv;
    public BotSet bs;
    public boolean isMsgDone = false;
    public String retMsg;

    public BotMsgHandl(MsgLayerView lv) throws Exception {
        this.lv = lv;
        this.bs = new BotSet();
    }

    public boolean isMsgDone() {
        return this.isMsgDone;
    }
    public String msgReplace(String ori,String beReplaced,String replacer) {
        String ret = null;
        if(ori.replaceAll(" ", "").toLowerCase().startsWith(beReplaced.replaceAll(" ","").toLowerCase())) {
            ret = ori.replace(beReplaced, replacer);
        }
        return ret;
    }
    public void msgEx(String str){
        boolean is_sendOut = false;
        Socket socket = new Socket();
        InputStream nis = null; //Network Input Stream
        OutputStream nos = null; //Network Output Stream
        String ret = "";

        for (int i = 0; i < bs.botMap.length; i++) {
            String s;
            if ((s = msgReplace(str, bs.botMap[i].botname, bs.botMap[i].botID)) != null) {
                ret = s;
                is_sendOut = true;
                break;
            }
        }
        if(!is_sendOut){
            return;
        }
        try {
            SocketAddress sockaddr = new InetSocketAddress(BotServerInfor.BOT_SERV_IP, BotServerInfor.BOT_SERV_PORT);
            //socket = new Socket();
            socket.connect(sockaddr, 5000); //5 second connection timeout
            if (socket.isConnected()) {
                byte[] buffer = new byte[1024];
                BotMsgNode node = null;
                String msgTx;
                int len = 0;
                nis = socket.getInputStream();
                nos = socket.getOutputStream();
                Log.i("AsyncTask", "doInBackground: Socket created, streams assigned");
                node = new BotMsgNode(BotMsgFormater.BOT_MSG_TYPE_TXT,BotServerInfor.BOT_SERV_PORT,0,ret);
                msgTx = BotMsgFormater.formatBotMsg(node);
                Log.i("AsyncTask", "Send Data: " + msgTx);
                nos.write(msgTx.getBytes());
                nos.flush();
                BufferedReader in = new BufferedReader(new InputStreamReader(nis));
                String line = in.readLine();
                String line1 = "";
                while (line != null && !line.equals("")) {
                    line1 += line;
                    len = len + line.length();
                    line = in.readLine();
                    Log.i("AsyncTask", "doInBackground: Waiting for inital data..." + line1);
                    publishProgress(line1);
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            Log.i("AsyncTask", "doInBackground: IOException");
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("AsyncTask", "doInBackground: Exception");
        } finally {
            try {
                if (socket.isConnected()) {
                    nis.close();
                    nos.close();
                    socket.close();
                }
            } catch (IOException e) {
                Log.getStackTraceString(e);
            } catch (Exception e) {
                Log.getStackTraceString(e);
            }
            Log.i("AsyncTask", "doInBackground: Finished");
        }
    }
    @Override
    protected String doInBackground(String ... str) {
        msgEx(str[0]);
        return null;
    }
    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        Log.i("AsyncTask", "onProgressUpdate: Start");
        if (values != null && values.length > 0) {
            //Your View attribute object in the activity
            // already initialized in the onCreate!
            //lv.updateLVMsg(values[0],MsgNode.TypeReceived);
        }
        onPostExecute(values[0]);
        //msgEx(values[0]);
    }
    @Override
    protected void onPostExecute(String str) {
        boolean is_sendOut = false;
        String ret=str;
        super.onPostExecute(str);
        Log.i("AsyncTask", "onPostExecute: Start");
        if (str != null && str.length() > 0) {
            //msgEx(s);
            BotMsgHandl mhl;
            Log.i("AsyncTask", "MsgNode Recv: "+str);
            try {
                for (int i = 0; i < bs.botMap.length; i++) {
                    String s;
                    if ((s = msgReplace(str, bs.botMap[i].botID,bs.botMap[i].botname)) != null) {
                        ret = s;
                        is_sendOut = true;
                        break;
                    }
                }
                if(is_sendOut) {//MsgNode to Bots
                    mhl = new BotMsgHandl(this.lv);
                    mhl.execute(ret);
                }
                else{//MsgNode to HP
                    if((msgReplace(ret, bs.HPNode.botID,bs.HPNode.botname)) != null){
                        ret = msgReplace(ret, bs.HPNode.botID,bs.HPNode.botname);
                    }
                }
                BotMsgNode node = BotMsgFormater.parseBotMsg(ret);
                int imgId = 0;
                ChapterPool.chapterPool.get(BotMsgFormater.gChapter).mileStone = BotMsgFormater.gMilestone;

                for(int i = 0; i < CharPool.charPool.get(BotMsgFormater.gChapter).size(); i++){
                    if(CharPool.charPool.get(BotMsgFormater.gChapter).get(i).locked){
                        if(CharPool.charPool.get(BotMsgFormater.gChapter).get(i).showUpMileStone <=
                                BotMsgFormater.gMilestone){
                            CharPool.charPool.get(BotMsgFormater.gChapter).get(i).locked = false;

                        }
                    }
                }

                Log.i("AsyncTask", "Chapter unable to unlock..."+ChapterPool.chapterPool.get(BotMsgFormater.gChapter).mileStone );
                Log.i("AsyncTask", "CBotMsgFormater..."+BotMsgFormater.gMilestone);
                Log.i("AsyncTask", "BotMsgFormater..."+BotMsgFormater.gChapter);

                if(ChapterPool.chapterPool.get(BotMsgFormater.gChapter).mileStone <= BotMsgFormater.gMilestone){
                    ChapterPool.chapterPool.get(BotMsgFormater.gChapter).mileStone = BotMsgFormater.gMilestone;
                    if(ChapterPool.chapterPool.size() > BotMsgFormater.gChapter+1)
                        ChapterPool.chapterPool.get(BotMsgFormater.gChapter+1).locked = false;
                    else
                        Log.i("AsyncTask", "Chapter unable to unlock...");
                }
                for(int i = 0; i< bs.botMap.length;i++){
                    if(node.msgFrom == bs.botMap[i].botPort){
                        imgId = bs.botMap[i].imgId;
                        break;
                    }
                }
                lv.updateLVMsg(node.msg, MsgNode.TypeReceived,imgId);
            } catch (Exception e) {
                e.printStackTrace();
                Log.i("AsyncTask", "onPostExecute: Exception");
            }

        }
        Log.i("AsyncTask", "onPostExecute: Finished");
    }
}
