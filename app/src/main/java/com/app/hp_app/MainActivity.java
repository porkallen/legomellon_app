package com.app.hp_app;

import android.content.Intent;
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

import java.util.HashMap;
import 	java.util.Arrays;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

/**
 * Created by Lan on 10/22/17.
 */
public class MainActivity extends AppCompatActivity {

    public void introViewSetup() {
        /*Animation Usage*/
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LayoutParams default_layout_params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        int delayStart = 3000;
        int dur = 3000;
        /*View set*/
        int introViewIDSet[] = {R.layout.activity_welcome_page, R.layout.activity_main};
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

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        introViewSetup();
    }
    /** Called when the user taps the Send button */
    /** This is only for test*/
    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
    /** This is the function that link main activity and conversation page , can set get chapter later*/
    public void getChapter(View view) {
        Intent intent = new Intent(this, ConversationActivity.class);
        startActivity(intent);
    }



}
