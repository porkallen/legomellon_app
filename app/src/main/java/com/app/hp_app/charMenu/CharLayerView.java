package com.app.hp_app.charMenu;

import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.app.hp_app.R;

import java.util.List;

/**
 * Created by allen on 11/19/2017.
 */

public class CharLayerView {

    private ListView charListView;
    private CharAdapter charAdapter;
    private List<CharNode> charList;

    public CharLayerView(CharAdapter charAdapter, ListView charListView, List<CharNode> charList) {
        //Log.i("MsgLayerView","Test");
        this.charAdapter = charAdapter;
        this.charListView = charListView;
        this.charList = charList;
    }

    public void updateLVChar(String name, int imgId) {
        CharNode cNode = new CharNode(name,imgId);
        charList.add(cNode);
        charAdapter.notifyDataSetChanged();
        charListView.setSelection(charList.size());
    }
}
