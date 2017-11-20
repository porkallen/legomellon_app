package com.app.hp_app.conversation;

import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.app.hp_app.R;
import com.app.hp_app.botMsg.BotMsgFormater;
import com.app.hp_app.botMsg.BotNameList;
import com.app.hp_app.botMsg.BotMsgHandl;
import com.app.hp_app.chapter.ChapterPool;
import com.app.hp_app.charMenu.CharPool;


public class ConversationActivity extends AppCompatActivity {

    private ListView msgListView;
    private EditText input;
    private Button sent;
    private MsgAdapter msgAdapter;
    private List<MsgNode> msgList = new ArrayList<MsgNode>();
    private MsgLayerView lv;
    private BotMsgHandl mhl;
    private AutoCompleteTextView autocompleteView;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);
        Bundle b = getIntent().getExtras();
        int charIdx = -1; // or other values
        if(b != null)
            charIdx = b.getInt("pos");

        msgAdapter = new MsgAdapter( this, R.layout.item_msg_bubble, msgList);
        sent = (Button) findViewById(R.id.send);
        msgListView = (ListView) findViewById(R.id.msg_list_view);
        msgListView.setAdapter(msgAdapter);
        autocompleteView = (AutoCompleteTextView) findViewById(R.id.autoText);
        lv = new MsgLayerView(msgAdapter,autocompleteView,sent,msgListView,msgList);
        int layoutItemId = android.R.layout.simple_dropdown_item_1line;
        List<String> BotList;
        Log.i("AsyncTask", "charStartup CH: "+ChapterPool.curChapter+
                "charIdx: "+ charIdx);

        if(charIdx != (-1)){
            String[] list = CharPool.charPool.get(ChapterPool.curChapter).
                    get(charIdx).charSet;
            BotList = Arrays.asList(list);

            if(!CharPool.charPool.get(ChapterPool.curChapter).get(charIdx).oneShot){
                String tmpStr;
                try {
                    mhl = new BotMsgHandl(lv);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                tmpStr = CharPool.charPool.get(ChapterPool.curChapter).
                        get(charIdx).startCmd;
                Log.i("AsyncTask", "charStartup -->"+tmpStr);
                CharPool.charPool.get(ChapterPool.curChapter).get(charIdx).oneShot = true;
                mhl.execute(tmpStr);
            }
        }
        else {
            BotList = Arrays.asList(BotNameList.botName);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, layoutItemId, BotList);
        autocompleteView.setAdapter(adapter);

        sent.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //String msgContent = input.getText().toString();import android.support.v7.app.
                String msgContent = autocompleteView.getText().toString();
                if (!"".equals(msgContent)){
                    try {
                        mhl = new BotMsgHandl(lv);
                        mhl.execute(msgContent);
                    } catch (Exception e) {
                        lv.updateLVMsg("Socket Failed ", MsgNode.TypeSent,0);
                        String stackTrace = Log.getStackTraceString(e);
                        lv.updateLVMsg(stackTrace, MsgNode.TypeSent,0);
                    }
                    lv.updateLVMsg(msgContent, MsgNode.TypeSent,0);
                }
            }

        });
    }
}


