package com.newdjk.bdmember.bean;

import com.newdjk.bdmember.R;

import java.util.ArrayList;
import java.util.List;

public class HomeItemBean {
    public List<Integer> mListIc;
    public List<Integer> mListText;

    public HomeItemBean() {
        this.mListIc = new ArrayList<>();
        this.mListText = new ArrayList<>();
        mListIc.add(R.mipmap.ic_home_item_second_1);
        mListIc.add(R.mipmap.ic_home_item_second_2);
        mListIc.add(R.mipmap.ic_home_item_second_3);
        mListIc.add(R.mipmap.ic_home_item_second_4);
        mListIc.add(R.mipmap.ic_home_item_second_5);
        mListIc.add(R.mipmap.ic_home_item_second_6);
        mListIc.add(R.mipmap.ic_home_item_second_7);
        mListIc.add(R.mipmap.ic_home_item_second_8);

        this.mListText.add(R.string.home_item_second_text1);
        this.mListText.add(R.string.home_item_second_text2);
        this.mListText.add(R.string.home_item_second_text3);
        this.mListText.add(R.string.home_item_second_text4);
        this.mListText.add(R.string.home_item_second_text5);
        this.mListText.add(R.string.home_item_second_text6);
        this.mListText.add(R.string.home_item_second_text7);
        this.mListText.add(R.string.home_item_second_text8);
    }
}
