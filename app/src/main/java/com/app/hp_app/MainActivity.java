package com.app.hp_app;

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
import 	android.widget.AutoCompleteTextView;
import java.util.Map;
import android.widget.ArrayAdapter;

import com.app.hp_app.hp_app.R;

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
    private LayerView lv;
    private BotMsgHandl mhl;
    private AutoCompleteTextView autocompleteView;

    public void IntroSetup() {
        /*Animation Usage*/
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LayoutParams default_layout_params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        int delayStart = 3000;
        int dur = 3000;
        /*View set*/
        int introViewIDSet[] = {R.layout.activity_welcome_page, R.layout.activity_conversation_main};
        for(int i = 0; i< introViewIDSet.length; i++){
            AlphaAnimation tmpAnim;
            View introView = inflater.inflate(introViewIDSet[i], null);
            int tmpPostVisable = View.INVISIBLE;
            if(i == 0){//The first view should be Fade Out
                /*1.0f->0.0f means from appear to disapear*/
                tmpAnim = new AlphaAnimation(1.0f, 0.0f);
                /*When the animation will start up*/
                tmpAnim.setStartOffset(delayStart);
                /*How long the animation last*/
                tmpAnim.setDuration(dur);
            }
            else{// Others should be fade in
                tmpAnim = new AlphaAnimation(0.0f, 1.0f);
                tmpAnim.setStartOffset(delayStart);
                tmpAnim.setDuration(dur);
                /*The final one should be Visable*/
                if(i == (introViewIDSet.length - 1)){
                    tmpPostVisable = View.VISIBLE;
                }
            }
            delayStart+=dur+1000;
            addContentView(introView, default_layout_params);
            introView.startAnimation(tmpAnim);
            introView.setVisibility(tmpPostVisable);
        }
    }

    public void PopulatePeopleList(ArrayList<Map<String, String>> mPeopleList) {
        mPeopleList.clear();
        Map<String, String> NamePhoneType = new HashMap<String, String>();
    }

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        IntroSetup();
        msgAdapter = new MsgAdapter( MainActivity.this, R.layout.item_msg_bubble, msgList);
        //input = (EditText)findViewById(R.id.input_text);
        sent = (Button) findViewById(R.id.send);
        msgListView = (ListView) findViewById(R.id.msg_list_view);
        msgListView.setAdapter(msgAdapter);
        autocompleteView = (AutoCompleteTextView) findViewById(R.id.autoText);
        lv = new LayerView(msgAdapter,autocompleteView,sent,msgListView,msgList);
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
                        mhl = new BotMsgHandl(lv);
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
