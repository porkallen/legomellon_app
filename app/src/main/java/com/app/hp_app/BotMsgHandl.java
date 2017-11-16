package com.app.hp_app;

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

class MsgNode {
    public static final int MSG_TYPE_TXT = 0;
    public static final int MSG_TYPE_HINT = 1;
    public int msgTo;
    public byte[] msg;
    public int msgType;
    MsgNode(int msgTo,byte[] msg, int type){
        this.msgTo = msgTo;
        this.msgType = msgType;
        this.msg = msg;
    }
}

class BOT_SERV {
    public static final String BOT_SERV_IP="35.203.167.236";
    public static final int BOT_SERV_PORT=9999;
    public static final int BOT_HANDL_PORT=3535;
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
        this.botMap[0] = new BotNode(BotNameList.botName[0],"<@U7JL8RLEQ>",60001,R.drawable.dudley_dursley);
        this.botMap[1] = new BotNode(BotNameList.botName[1],"<@U7JK660E6>",60002,R.drawable.aunt_petunia);
        this.botMap[2] = new BotNode(BotNameList.botName[2],"<@U7N6MU4AC>",60003,R.drawable.bob);
        this.botMap[3] = new BotNode(BotNameList.botName[3],"<@U7NA7RWC9>",60004,R.drawable.hogford);
    }
}
public class BotMsgHandl extends AsyncTask<String, String, String>{
    private Thread t;
    public final int listenPort = BOT_SERV.BOT_HANDL_PORT;
    public final String dstAddress = BOT_SERV.BOT_SERV_IP;
    public final int dstPort = BOT_SERV.BOT_SERV_PORT;;
    public LayerView lv;
    public BotSet bs;
    public boolean isMsgDone = false;
    public String retMsg;

    public BotMsgHandl(LayerView lv) throws Exception {
        this.lv = lv;
        this.bs = new BotSet();
    }
    public String[] parseBotMsg(String str) {
        String[] strPool = str.split("@#");
        return strPool;
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
        String retStr = "";

        for (int i = 0; i < bs.botMap.length; i++) {
            String s;
            if ((s = msgReplace(str, bs.botMap[i].botname, bs.botMap[i].botID)) != null) {
                retStr = s;
                is_sendOut = true;
                break;
            }
        }
        if(!is_sendOut){
            return;
        }
        try {
            SocketAddress sockaddr = new InetSocketAddress(  this.dstAddress, this.dstPort);
            //socket = new Socket();
            socket.connect(sockaddr, 5000); //10 second connection timeout
            if (socket.isConnected()) {
                MsgNode msgTx;
                byte[] buffer = new byte[1024];
                int len = 0;
                nis = socket.getInputStream();
                nos = socket.getOutputStream();
                Log.i("AsyncTask", "doInBackground: Socket created, streams assigned");
                Log.i("AsyncTask", "Send Data: " + retStr);
                msgTx.msgTo = BOT_SERV.BOT_SERV_PORT;
                msgTx.msgType = MsgNode.MSG_TYPE_TXT;
                msgTx.msg = retStr.getBytes();
                nos.write(msgTx.);
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
            //lv.updateLVMsg(values[0],Msg.TypeReceived);
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
            try {
                for (int i = 0; i < bs.botMap.length; i++) {
                    String s;
                    if ((s = msgReplace(str, bs.botMap[i].botID,bs.botMap[i].botname)) != null) {
                        ret = s;
                        is_sendOut = true;
                        break;
                    }
                }
                if(is_sendOut) {//Msg to Bots
                    mhl = new BotMsgHandl(this.lv);
                    mhl.execute(ret);
                }
                else{//Msg to HP
                    if((msgReplace(ret, bs.HPNode.botID,bs.HPNode.botname)) != null){
                        ret = msgReplace(ret, bs.HPNode.botID,bs.HPNode.botname);
                    }
                }
                String[] tmpStr = parseBotMsg(ret);
                int imgId = 0;
                if(tmpStr[0] != null && tmpStr[1] != null){
                    for(int i = 0; i< bs.botMap.length;i++){
                        if(Integer.parseInt(tmpStr[1]) == bs.botMap[i].botPort){
                            imgId = bs.botMap[i].imgId;
                            break;
                        }
                    }
                }
                lv.updateLVMsg(tmpStr[0],Msg.TypeReceived,imgId);
            } catch (Exception e) {
                e.printStackTrace();
                Log.i("AsyncTask", "onPostExecute: Exception");
            }

        }
        Log.i("AsyncTask", "onPostExecute: Finished");
    }
}
