package com.example.allen.hp_app;
import android.support.v7.app.AppCompatActivity;

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

class msgNode{
    public int msgType;
    public String msg;
}
class botNode{
    String botname;
    String botID;
    botNode(String name,String id){
        this.botname = name;
        this.botID = id;
    }
}
class botSet{
    final botNode HPNode = new botNode("@Harry Potter","<@U7HQ4QJR2>");;
    botNode[] botMap;
    botSet(){
        this.botMap = new botNode[2];
        this.botMap[0] = new botNode("@Dudley Dursley","<@U7JL8RLEQ>");
        this.botMap[1] = new botNode("@Aunt Petunia","<@U7JK660E6>");
    }
}
public class MsgHandler extends AsyncTask<String, String, String>{
    private Thread t;
    public final int port = 3535;
    public LayerView lv;
    public String dstAddress;
    public int dstPort;
    public botSet bs;
    public boolean isMsgDone = false;
    public String retMsg;

    public MsgHandler(LayerView lv) throws Exception {
        this.lv = lv;
        this.dstAddress = "35.203.167.236";
        this.dstPort = 9999;
        this.bs = new botSet();
    }
    public String retMsg() {
        return this.retMsg;
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
            SocketAddress sockaddr = new InetSocketAddress("35.203.167.236", 9999);
            //socket = new Socket();
            socket.connect(sockaddr, 5000); //10 second connection timeout
            if (socket.isConnected()) {
                byte[] buffer = new byte[1024];
                int len = 0;
                nis = socket.getInputStream();
                nos = socket.getOutputStream();
                Log.i("AsyncTask", "doInBackground: Socket created, streams assigned");
                Log.i("AsyncTask", "Send Data: " + ret);
                nos.write(ret.getBytes());
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
            //lv.msgUpdate(values[0],Msg.TypeReceived);
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
            MsgHandler mhl;
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
                    lv.msgUpdate(ret,Msg.TypeReceived);
                    mhl = new MsgHandler(this.lv);
                    mhl.execute(ret);
                }
                else{//Msg to HP
                    if((msgReplace(ret, bs.HPNode.botID,bs.HPNode.botname)) != null){
                        ret = msgReplace(ret, bs.HPNode.botID,bs.HPNode.botname);
                    }
                    lv.msgUpdate(ret,Msg.TypeReceived);
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.i("AsyncTask", "onPostExecute: Exception");
            }

        }
        Log.i("AsyncTask", "onPostExecute: Finished");
    }
}
