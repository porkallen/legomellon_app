package com.example.allen.hp_app;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.util.List;

/**
 * Created by Lan on 10/22/17.
 */

public class MsgAdapter extends ArrayAdapter<Msg> {

    private int resourceId;

    public MsgAdapter(Context context, int textViewSourceId, List<Msg> objects){

        super(context,textViewSourceId,objects);
        resourceId = textViewSourceId;
    }

    public View getView(int position, View convertview, ViewGroup parent){

        Msg msg = getItem(position);
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


        if( msg.getType() == Msg.TypeReceived) {
         //   Uri uri = Uri.parse("android.resource://com.example.allen.hp_app/drawable/bob.png");
        //    viewHolder.left_img.setImageURI(uri);

            viewHolder.LeftLayout.setVisibility(view.VISIBLE);
            viewHolder.left_img.setVisibility(view.VISIBLE);
            viewHolder.RightLayout.setVisibility(view.GONE);
            viewHolder.Right_img.setVisibility(view.GONE);
            viewHolder.Left_msg.setText(msg.getMessage());
        }  else if (msg.getType() == Msg.TypeSent) {
            viewHolder.LeftLayout.setVisibility(view.GONE);
            viewHolder.left_img.setVisibility(view.GONE);
            viewHolder.RightLayout.setVisibility(view.VISIBLE);
            viewHolder.Right_img.setVisibility(view.VISIBLE);
            viewHolder.Right_msg.setText(msg.getMessage());
        }
        return view;
    }


    class ViewHolder{
        LinearLayout LeftLayout;
        LinearLayout RightLayout;
        TextView Left_msg;
        TextView Right_msg;
        ImageView left_img;
        ImageView Right_img;

    }

}
