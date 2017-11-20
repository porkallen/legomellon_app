package com.app.hp_app.chapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.hp_app.R;

import java.util.List;

/**
 * Created by allen on 11/19/2017.
 */
class ChapterHolder{
    TextView chapterName;
    TextView subtitle;
}

public class ChapterAdapter  extends ArrayAdapter<ChapterNode> {

    private int resourceId;

    public ChapterAdapter(Context context, int textViewSourceId, List<ChapterNode> objects){
        super(context,textViewSourceId,objects);
        resourceId = textViewSourceId;
    }
    public View getView(int position, View convertview, ViewGroup parent){

        ChapterNode chapterNode = getItem(position);
        View view;
        ChapterHolder chapterHolder;

        if (convertview == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            chapterHolder = new ChapterHolder();
            chapterHolder.chapterName =(TextView) view.findViewById(R.id.chapterName);
            chapterHolder.subtitle = (TextView)view.findViewById(R.id.chapterSubtitle);
            view.setTag(chapterHolder);
        } else{
            view = convertview;
            chapterHolder = (ChapterHolder)view.getTag();
        }
        if( chapterNode.chapterName.length() > 0 && chapterNode.subtitle.length() > 0 ) {
            chapterHolder.chapterName.setText(chapterNode.chapterName);
            chapterHolder.subtitle.setText(chapterNode.subtitle);
            chapterHolder.chapterName.setVisibility(view.VISIBLE);
            chapterHolder.subtitle.setVisibility(view.VISIBLE);
        }
        view.setBackgroundColor(Color.WHITE);
        return view;
    }
}
