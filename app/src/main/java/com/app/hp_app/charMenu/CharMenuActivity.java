package com.app.hp_app.charMenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.app.hp_app.R;
import com.app.hp_app.botMsg.BotMsgFormater;
import com.app.hp_app.conversation.ConversationActivity;
import com.app.hp_app.conversation.MsgLayerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by allen on 11/19/2017.
 */

public class CharMenuActivity extends AppCompatActivity {
    private List<CharNode> charList = new ArrayList<CharNode>();
    private ListView charListView;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_selection);
        CharAdapter charAdapter = new CharAdapter( this, R.layout.item_char_bubble, charList);
        charListView = (ListView) findViewById(R.id.charListView);
        charListView.setAdapter(charAdapter);
        CharLayerView charLv = new CharLayerView(charAdapter,charListView,charList);
        CharPool cPool = new CharPool();
        if(true){
            for(int i = 0; i < BotMsgFormater.gMilestone+1; i++){
                if(BotMsgFormater.gChapter < cPool.charPool.size()){
                    if(i < cPool.charPool.get(BotMsgFormater.gChapter).size()){
                        charLv.updateLVChar(cPool.charPool.get(BotMsgFormater.gChapter).get(i).name,
                                cPool.charPool.get(BotMsgFormater.gChapter).get(i).imgId);
                    }
                }
            }
        }
        else{
            charLv.updateLVChar("HIHI",0);
        }

        charListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view,
                                    int position,
                                    long id) {
                // TODO Auto-generated method stub
                //Log.d("############","Items "+position);
                getCharConversation(position);
            }

        });
    }
    public void getCharConversation(int pos) {
        Intent intent = new Intent(this,ConversationActivity.class);
        Bundle b = new Bundle();
        b.putInt("pos", pos); //Your id
        intent.putExtras(b); //Put your id to your next Intent
        startActivity(intent);
    }
}
