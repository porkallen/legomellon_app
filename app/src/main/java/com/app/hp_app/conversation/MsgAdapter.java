package com.app.hp_app.conversation;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.hp_app.R;

import java.util.List;

/**
 * Created by Lan on 10/22/17.
 */
class ViewHolder{
    LinearLayout LeftLayout;
    LinearLayout RightLayout;
    TextView Left_msg;
    TextView Right_msg;
    ImageView left_img;
    ImageView Right_img;
}

public class MsgAdapter extends ArrayAdapter<MsgNode> {

    private int resourceId;

    public MsgAdapter(Context context, int textViewSourceId, List<MsgNode> objects){

        super(context,textViewSourceId,objects);
        resourceId = textViewSourceId;
    }
    public View getView(int position, View convertview, ViewGroup parent){

        MsgNode msg = getItem(position);
        View view;
        ViewHolder viewHolder;

        if (convertview == null){

            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewHolder();
            viewHolder.LeftLayout =(LinearLayout) view. findViewById(R.id.left_layout) ;
            viewHolder.RightLayout = (LinearLayout)view.findViewById(R.id.right_layout);
            viewHolder.Left_msg = (TextView)view.findViewById(R.id.left_msg);
            viewHolder.Right_msg = (TextView)view.findViewById(R.id.right_msg );
            viewHolder.left_img = (ImageView)view.findViewById(R.id.left_avarta);
            viewHolder.Right_img = (ImageView)view.findViewById(R.id.right_avarta);
            view.setTag(viewHolder);
        } else{
            view = convertview;
            viewHolder = (ViewHolder)view.getTag();
        }


        if( msg.getType() == MsgNode.TypeReceived) {
            int msgId = R.drawable.left_avatar;
            if(msg.getImgId() != 0) {
                msgId = msg.getImgId();
            }
            viewHolder.left_img.setImageResource(msgId);
            viewHolder.LeftLayout.setVisibility(view.VISIBLE);
            viewHolder.left_img.setVisibility(view.VISIBLE);
            viewHolder.RightLayout.setVisibility(view.GONE);
            viewHolder.Right_img.setVisibility(view.GONE);
            viewHolder.Left_msg.setText(msg.getMessage());
        }  else if (msg.getType() == MsgNode.TypeSent) {
            viewHolder.Right_img.setImageResource(R.drawable.hp);
            viewHolder.LeftLayout.setVisibility(view.GONE);
            viewHolder.left_img.setVisibility(view.GONE);
            viewHolder.RightLayout.setVisibility(view.VISIBLE);
            viewHolder.Right_img.setVisibility(view.VISIBLE);
            viewHolder.Right_msg.setText(msg.getMessage());
        }
        return view;
    }
}
