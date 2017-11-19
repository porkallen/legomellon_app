package com.app.hp_app.chat_menu;

import android.content.Context;
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
 * Created by allen on 11/19/2017.
 */
class CharHolder{
    LinearLayout LeftLayout;
    LinearLayout RightLayout;
}
public class CharAdapter extends ArrayAdapter<CharNode> {

    private int resourceId;

    public CharAdapter(Context context, int textViewSourceId, List<CharNode> objects){
        super(context,textViewSourceId,objects);
        resourceId = textViewSourceId;
    }
    public View getView(int position, View convertview, ViewGroup parent){

        CharNode charNode = getItem(position);
        View view;
        CharHolder charHolder;

        if (convertview == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            charHolder = new CharHolder();
            charHolder.LeftLayout =(LinearLayout) view. findViewById(R.id.left_layout) ;
            charHolder.RightLayout = (LinearLayout)view.findViewById(R.id.right_layout);
            view.setTag(charHolder);
        } else{
            view = convertview;
            charHolder = (CharHolder)view.getTag();
        }
        if( charNode.name.length() > 0) {
            charHolder.LeftLayout.setVisibility(view.VISIBLE);
            charHolder.RightLayout.setVisibility(view.GONE);
        }
        return view;
    }
}
