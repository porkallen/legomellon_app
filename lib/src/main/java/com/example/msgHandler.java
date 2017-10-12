package com.example;

/**
 * Created by allen on 10/2/2017.
 */
import java.io.*;
import java.net.*;
import java.util.concurrent.Semaphore;


public class msgHandler implements Runnable{
    private Thread t;
    public final int port = 64300;
    public String recvMsg;
    static Semaphore semaphore = new Semaphore(1);
    public void conTxtParse(boolean sendRecv,String str){};
    public void keywordGet(String str){};
    public void dataTrain(){};


    public void SendMsg(String str) throws Exception{
        Socket s = new Socket();
        DataOutputStream output;
        //replaced by future python service
        String host = "127.0.0.1";

        try
        {
            //replaced by future python service
            s.connect(new InetSocketAddress(host , this.port));
            System.out.println("Connected");
        }
        //Host not found
        catch (UnknownHostException e)
        {
            System.err.println("Don't know about host : " + host);
            s.close();
            System.exit(1);
        }

        try {
            output = new DataOutputStream(s.getOutputStream());
            //Send message to server
            System.out.println("==Send MSG "+str+"==");
            output.writeUTF(str);
            System.out.println("============");
        }
        catch (IOException e) {
            System.out.println(e);
        }

        try {
            semaphore.acquire();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        s.close();
    }
    public String RecvMsg()throws Exception {
        try {
            System.out.println("Waiting for Recving Msg");
            semaphore.acquire();
        }
        catch (InterruptedException e){
            System.out.println(e);
        }
        semaphore.release();
        System.out.println("Receiving Msg "+this.recvMsg);
        return this.recvMsg;
    }
    public void run(){
        System.out.println("Creating Msg Handler");
        System.out.println("Running MsgHandler...");
        try {
            this.msgListener();
        }
        catch(InterruptedException ie){
            System.out.println("MsgHandler interrupted.");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("MsgHandler exiting.");
    }
    public void start () {
        System.out.println("Starting MsgHandler");
        if (t == null) {
            t = new Thread (this,"MsgHandler");
            t.start ();
        }
    }
    public void msgListener()throws Exception {
        String fromClient;
        String toClient;

        ServerSocket server = new ServerSocket(this.port);
        //server.setSoTimeout(10000);
        System.out.println("wait for connection on port "+this.port);

        boolean run = true;
        while(run) {
            Socket client = server.accept();
            System.out.println("got connection to "+client.getRemoteSocketAddress());
            DataInputStream in = new DataInputStream(client.getInputStream());
            //BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            fromClient = in.readUTF();
            System.out.println("received: " + fromClient);
            this.recvMsg = new String(fromClient);
            semaphore.release();
        }
        server.close();
        System.exit(0);
    }
}
