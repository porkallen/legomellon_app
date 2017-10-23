package com.example;

/**
 * Created by allen on 10/2/2017.
 */
import com.example.allen.hp_app.Msg;
import com.example.allen.hp_app.layerView;

import java.io.*;
import java.net.*;
import java.util.concurrent.Semaphore;


public class msgHandler implements Runnable{
    private Thread t;
    //private ServerSocket server;
    public final int port = 3535;
    public String recvMsg;
    public layerView lv;
    //static Semaphore semaphore = new Semaphore(1);
    public void conTxtParse(boolean sendRecv,String str){};
    public void keywordGet(String str){};
    public void dataTrain(){};

    public msgHandler(layerView lv){
        this.lv = lv;
    }
    public void SendMsg(String str) throws Exception{
        Socket s = new Socket();
        DataOutputStream output;
        //replaced by future python service
        String host = "127.0.0.1";

        try
        {
            lv.msgUpdate("Send Msg",Msg.TypeSent);
            //replaced by future python service
            s.connect(new InetSocketAddress(host , 9999));
            lv.msgUpdate("Connected",Msg.TypeSent);
        }
        //Host not found
        catch (UnknownHostException e)
        {
            lv.msgUpdate("Don't know about host : " + host,Msg.TypeSent);
            s.close();
            System.exit(1);
        }

        try {
            output = new DataOutputStream(s.getOutputStream());
            //Send message to server
            lv.msgUpdate("==Send MSG "+str+"==",Msg.TypeSent);
            output.writeUTF(str);
            System.out.println("============");
        }
        catch (IOException e) {
            System.out.println(e);
        }
/*
        try {
            semaphore.acquire();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        */
        lv.msgUpdate("Closed",Msg.TypeSent);
        s.close();
    }
    public String RecvMsg()throws Exception {
        /*
        try {
            System.out.println("Waiting for Recving Msg");
            semaphore.acquire();
        }
        catch (InterruptedException e){
            System.out.println(e);
        }
        semaphore.release();
        */
        System.out.println("Receiving Msg "+this.recvMsg);
        return this.recvMsg;
    }
    public void run(){
        lv.msgUpdate("Creating Msg Handler",Msg.TypeSent);
        lv.msgUpdate("Running MsgHandler...",Msg.TypeSent);
        try {
            this.msgListener();
        } catch (Exception e) {
            e.printStackTrace();
        }
        lv.msgUpdate("MsgHandler exiting.",Msg.TypeSent);
    }
    public void start() {
        lv.msgUpdate("wait for connection on port ",Msg.TypeSent);
        if (t == null) {
            t = new Thread (this);
            t.start ();
        }
    }
    public void msgListener()throws Exception {
        String fromClient;
        String toClient;
        //lv.msgUpdate("Sart msgListener"+this.port,Msg.TypeSent);
        ServerSocket server = new ServerSocket(this.port);
        //server.setSoTimeout(10000);
        //lv.msgUpdate("wait for connection on port "+this.port,Msg.TypeSent);
        //System.out.println("wait for connection on port "+this.port);

        boolean run = true;
        while(run) {
            this.lv.msgUpdate("Start Listening..."+Inet4Address.getLocalHost().getHostAddress(),Msg.TypeReceived);
            Socket client = server.accept();
            this.lv.msgUpdate("got connection to ",Msg.TypeReceived);
            DataInputStream in = new DataInputStream(client.getInputStream());
            //BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            fromClient = in.readUTF();
            //System.out.println("received: " + fromClient);
            this.recvMsg = new String(fromClient);
            this.lv.msgUpdate(this.recvMsg, Msg.TypeReceived);
            //semaphore.release();
        }
        server.close();
        System.exit(0);
    }
}
