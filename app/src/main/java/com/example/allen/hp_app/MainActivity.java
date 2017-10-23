package com.example.allen.hp_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lan on 10/22/17.
 */

public class MainActivity extends AppCompatActivity {

    private ListView msgListView;
    private EditText input;
    private Button sent;
    private MsgAdapter msgAdapter;


    private List<Msg> msgList = new ArrayList<Msg>();

    @Override

    protected void onCreate (Bundle savedInstanceState){

        super.onCreate(savedInstanceState);


        setContentView(R.layout.conversation_main);

        msgAdapter = new MsgAdapter( MainActivity.this, R.layout.msg_item, msgList);
        input = (EditText)findViewById(R.id.input_text);
        sent = (Button) findViewById(R.id.send);
        msgListView = (ListView) findViewById(R.id.msg_list_view);
        msgListView.setAdapter(msgAdapter);

        sent.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                String msgContent = input.getText().toString();
                if (!"".equals(msgContent)){
                    Msg msg = new Msg(msgContent, Msg.TypeSent);
                    msgList.add(msg);

                    msgAdapter.notifyDataSetChanged();
                    msgListView.setSelection(msgList.size());
                    input.setText("");
                }
            }

        });

    }


}
