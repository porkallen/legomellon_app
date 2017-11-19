package com.app.hp_app.converstation;

import android.widget.AutoCompleteTextView;
//import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

/**
 * Created by allen on 10/23/2017.
 */

public class MsgLayerView {
    private ListView msgListView;
    private AutoCompleteTextView input;
    private Button sent;
    private MsgAdapter msgAdapter;
    private List<MsgNode> msgList;

    public MsgLayerView(MsgAdapter msgAdapter, AutoCompleteTextView input, Button sent, ListView msgListView, List<MsgNode> msgList) {
        //Log.i("MsgLayerView","Test");
        this.msgAdapter = msgAdapter;
        this.input = input;
        this.sent = sent;
        this.msgListView = msgListView;
        this.msgList = msgList;
    }

    public void updateLVMsg(String msgContent, int typeSent , int id){
        MsgNode msg = new MsgNode(msgContent, typeSent,id);
        msgList.add(msg);
        msgAdapter.notifyDataSetChanged();
        msgListView.setSelection(msgList.size());
        input.setText("");
    }
}
