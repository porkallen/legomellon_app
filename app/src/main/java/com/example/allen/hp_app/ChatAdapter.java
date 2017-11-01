package com.example.allen.hp_app;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.List;

/**
 * Created by Lan on 10/22/17.
 */

public class ChatAdapter extends ArrayAdapter<Msg> {

    private int resourceId;

    public ChatAdapter(Context context, int textViewSourceId, List<Msg> objects){

        super(context,textViewSourceId,objects);
        resourceId = textViewSourceId;
    }

    public View getView(int position, View convertview, ViewGroup parent){

        Msg msg = getItem(position);
        View view;
        ViewHolder viewHolder;

        Log.i("AsyncTask", "doInBackground: Socket created, streams assigned");

        if (convertview == null){

            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewHolder();
            viewHolder.chatPic = (ImageView)view.findViewById(R.id.chat_pic);
            viewHolder.characterName = (TextView)view.findViewById(R.id.character_name );
            viewHolder.chatTime = (TextView)view.findViewById(R.id.chat_heads_time);
            viewHolder.recentMessage = (TextView)view.findViewById(R.id.recentMessage);
            view.setTag(viewHolder);
        } else{
            view = convertview;
            viewHolder = (ViewHolder)view.getTag();
        }

            viewHolder.chatPic.setVisibility(view.VISIBLE);
            viewHolder.characterName.setVisibility(view.VISIBLE);
            viewHolder.chatTime.setVisibility(view.VISIBLE);
            viewHolder.recentMessage.setVisibility(view.VISIBLE);

        return view;
    }


    class ViewHolder{
        ImageView chatPic;
        TextView characterName;
        TextView chatTime;
        TextView recentMessage;
    }

}
