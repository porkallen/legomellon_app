package com.app.hp_app.chapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.LayoutInflater;
import android.content.Context;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AlphaAnimation;
import android.widget.AdapterView;
import android.widget.ListView;
import com.app.hp_app.R;
import com.app.hp_app.botMsg.BotMsgFormater;
import com.app.hp_app.charMenu.CharMenuActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lan on 10/22/17.
 */
public class MainActivity extends AppCompatActivity {
    private List<ChapterNode> chapterList = new ArrayList<ChapterNode>();
    private ListView chapterListView;
    static boolean one_shot = false;
    public void introViewSetup() {
        /*Animation Usage*/
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LayoutParams default_layout_params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        int delayStart = 3000;
        int dur = 3000;
        /*View set*/
        int introViewIDSet[] = {R.layout.animation_ch1_1,
                R.layout.animation_ch1_2,R.layout.animation_ch1_3,R.layout.activity_chapter};
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
            dur += 1;
            addContentView(introView, default_layout_params);
            introView.startAnimation(tmpAnim);
            introView.setVisibility(tmpPostVisable);
        }
    }
    @Override
    protected void onCreate (Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if(one_shot == false) {
            introViewSetup();
            one_shot = true;
        }
        else{
            setContentView(R.layout.activity_chapter);
        }
        ChapterAdapter chapterAdapter = new ChapterAdapter( this, R.layout.item_chapter_bubble, chapterList);
        chapterListView = (ListView) findViewById(R.id.chapterListView);
        chapterListView.setAdapter(chapterAdapter);
        ChapterLayerView chapterLv = new ChapterLayerView(chapterAdapter,chapterListView,chapterList);
        ChapterPool cPool = new ChapterPool();
        for(int i = 0; i< BotMsgFormater.gChapter+1; i++){
            chapterLv.updateLVChapter(cPool.chapterPool.get(i).get(0),cPool.chapterPool.get(i).get(1));
        }
        chapterListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub
                Log.d("############","Items ");
                getChapter(chapterListView);
            }

        });
    }
    /** This is the function that link chapter activity and conversation page , can set get gChapter later*/
    public void getChapter(View view) {
        Intent intent = new Intent(this, CharMenuActivity.class);
        startActivity(intent);
    }
}
