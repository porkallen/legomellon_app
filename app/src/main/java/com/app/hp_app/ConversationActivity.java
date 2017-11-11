package com.app.hp_app;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;



public class ConversationActivity extends AppCompatActivity {


    private ListView msgListView;
    private EditText input;
    private Button sent;
    private MsgAdapter msgAdapter;
    private List<Msg> msgList = new ArrayList<Msg>();
    private LayerView lv;
    private BotMsgHandl mhl;
    private AutoCompleteTextView autocompleteView;



    public void PopulatePeopleList(ArrayList<Map<String, String>> mPeopleList) {
        mPeopleList.clear();
        Map<String, String> NamePhoneType = new HashMap<String, String>();
    }

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
       // IntroSetup();

        setContentView(R.layout.activity_conversation_main);


        //test log
        //System.out.print("oncreate conversation activity");

        msgAdapter = new MsgAdapter( this, R.layout.item_msg_bubble, msgList);
        //input = (EditText)findViewById(R.id.input_text);
        sent = (Button) findViewById(R.id.send);
        msgListView = (ListView) findViewById(R.id.msg_list_view);
        msgListView.setAdapter(msgAdapter);
        autocompleteView = (AutoCompleteTextView) findViewById(R.id.autoText);
        lv = new LayerView(msgAdapter,autocompleteView,sent,msgListView,msgList);
        //mhl.start();

       // Intent intent = getIntent();



        int layoutItemId = android.R.layout.simple_dropdown_item_1line;
        List<String> BotList = Arrays.asList(BotNameList.botName);
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
                        lv.updateLVMsg("Socket Failed ", Msg.TypeSent,0);
                        String stackTrace = Log.getStackTraceString(e);
                        lv.updateLVMsg(stackTrace, Msg.TypeSent,0);
                    }
                    lv.updateLVMsg(msgContent,Msg.TypeSent,0);
                }
            }

        });

    }


}


