package com.app.hp_app;

import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

/**
 * Created by allen on 10/23/2017.
 */

public class LayerView {
    private ListView msgListView;
    private AutoCompleteTextView input;
    private Button sent;
    private MsgAdapter msgAdapter;
    private List<Msg> msgList;

    public LayerView(MsgAdapter msgAdapter, AutoCompleteTextView input, Button sent, ListView msgListView, List<Msg> msgList) {
        this.msgAdapter = msgAdapter;
        this.input = input;
        this.sent = sent;
        this.msgListView = msgListView;
        this.msgList = msgList;
    }

    public void msgUpdate(String msgContent,int typeSent){
        Msg msg = new Msg(msgContent, typeSent);
        msgList.add(msg);
        msgAdapter.notifyDataSetChanged();
        msgListView.setSelection(msgList.size());
        input.setText("");
    }
}