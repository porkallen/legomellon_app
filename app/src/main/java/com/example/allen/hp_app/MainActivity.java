package com.example.allen.hp_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import android.view.LayoutInflater;
import android.content.Context;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AlphaAnimation;
import android.widget.LinearLayout;
import 	android.widget.AutoCompleteTextView;
import java.util.Map;
import android.widget.ArrayAdapter;
import java.util.HashMap;
import 	java.util.Arrays;

/**
 * Created by Lan on 10/22/17.
 */
public class MainActivity extends AppCompatActivity {

    private ListView msgListView;
    private EditText input;
    private Button sent;
    private MsgAdapter msgAdapter;
    private List<Msg> msgList = new ArrayList<Msg>();
    private layerView lv;
    private msgHandler mhl;
    private AutoCompleteTextView autocompleteView;

    public void PopulatePeopleList(ArrayList<Map<String, String>> mPeopleList) {
        mPeopleList.clear();
        Map<String, String> NamePhoneType = new HashMap<String, String>();
    }

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_welcome_page);

        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setStartOffset(3000);
        alphaAnimation.setDuration(3000);
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LayoutParams default_layout_params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

        View view2 = inflater.inflate(R.layout.activity_conversation_main, null);
        View view1 = inflater.inflate(R.layout.activity_welcome_page, null);
        View view3 = inflater.inflate(R.layout.activity_tab_view, null);

        view1.setVisibility(View.VISIBLE);
        view2.setVisibility(View.GONE);
        addContentView(view1, default_layout_params);
        addContentView(view2, default_layout_params);
        addContentView(view3, default_layout_params);


        view1.startAnimation(alphaAnimation);
        view2.setVisibility(View.VISIBLE);
        view3.bringToFront();
        view1.setVisibility(View.INVISIBLE);

        AlphaAnimation alphaAnimation2 = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation2.setStartOffset(3000);
        alphaAnimation2.setDuration(1000);
        //view2.setVisibility(View.INVISIBLE);
        view2.clearAnimation();
        view2.startAnimation(alphaAnimation2);
        LinearLayout one = (LinearLayout) findViewById(R.id.convContext);
      //  one.startAnimation(alphaAnimation2);

        msgAdapter = new MsgAdapter( MainActivity.this, R.layout.item_msg_bubble, msgList);
        //input = (EditText)findViewById(R.id.input_text);

        sent = (Button) findViewById(R.id.send);
        msgListView = (ListView) findViewById(R.id.msg_list_view);
        msgListView.setAdapter(msgAdapter);
        autocompleteView = (AutoCompleteTextView) findViewById(R.id.autoText);
        lv = new layerView(msgAdapter,autocompleteView,sent,msgListView,msgList);
        //mhl.start();


        int layoutItemId = android.R.layout.simple_dropdown_item_1line;
        String[] botName = getResources().getStringArray(R.array.botName);
        List<String> botList = Arrays.asList(botName);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, layoutItemId, botList);
        autocompleteView.setAdapter(adapter);

        sent.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //String msgContent = input.getText().toString();
                String msgContent = autocompleteView.getText().toString();
                if (!"".equals(msgContent)){
                    try {
                        mhl = new msgHandler(lv);
                        mhl.execute(msgContent);
                    } catch (Exception e) {
                        lv.msgUpdate("Socket Failed ", Msg.TypeSent);
                        String stackTrace = Log.getStackTraceString(e);
                        lv.msgUpdate(stackTrace, Msg.TypeSent);
                    }
                    lv.msgUpdate(msgContent,Msg.TypeSent);
                }
            }

        });

    }


}
