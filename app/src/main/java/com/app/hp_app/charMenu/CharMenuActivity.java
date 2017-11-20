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
        charLv.updateLVChar("Dudley");
        charLv.updateLVChar("Petunia");
        charListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub
                Log.d("############","Items ");
                getCharConversation(charListView);
            }

        });
    }
    public void getCharConversation(View view) {
        Intent intent = new Intent(this,ConversationActivity.class);
        startActivity(intent);
    }
}
