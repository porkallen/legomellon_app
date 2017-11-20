package com.app.hp_app.chapter;

import android.widget.ListView;
import android.widget.TextView;

import com.app.hp_app.charMenu.CharAdapter;
import com.app.hp_app.charMenu.CharNode;

import java.util.List;

/**
 * Created by allen on 11/19/2017.
 */

public class ChapterLayerView {

    private ListView chapterListView;
    private ChapterAdapter chapterAdapter;
    private List<ChapterNode> chapterList;

    public ChapterLayerView(ChapterAdapter chapterAdapter, ListView chapterListView, List<ChapterNode> chapterList) {
        //Log.i("MsgLayerView","Test");
        this.chapterAdapter = chapterAdapter;
        this.chapterListView = chapterListView;
        this.chapterList = chapterList;
    }

    public void updateLVChapter(String chapterName,String subtitle) {
        ChapterNode cNode = new ChapterNode(chapterName,subtitle);
        chapterList.add(cNode);
        chapterAdapter.notifyDataSetChanged();
        chapterListView.setSelection(chapterList.size());
    }
}
