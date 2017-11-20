package com.app.hp_app.charMenu;

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
    ImageView charPic;
    TextView charName;
    TextView charChatTime;
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
            charHolder.charPic =(ImageView) view.findViewById(R.id.charPic);
            charHolder.charName = (TextView)view.findViewById(R.id.charName);
            view.setTag(charHolder);
        } else{
            view = convertview;
            charHolder = (CharHolder)view.getTag();
        }
        if( charNode.name.length() > 0) {
            charHolder.charName.setText(charNode.name);
            charHolder.charPic.setVisibility(view.VISIBLE);
            charHolder.charName.setVisibility(view.VISIBLE);
        }
        return view;
    }
}
