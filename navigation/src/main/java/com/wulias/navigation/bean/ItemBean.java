package com.wulias.navigation.bean;

import android.support.v4.app.Fragment;

/**
 * Created by Administrator on 2018/7/30.
 */

public class ItemBean {
    private String title;//菜单名
    private int selectIcon;//选中图片
    private int normalIcom;//未选中
    private int newNumber;//消息(暂时未做)
    private String tag;
    private Fragment fragment;

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSelectIcon() {
        return selectIcon;
    }

    public void setSelectIcon(int selectIcon) {
        this.selectIcon = selectIcon;
    }

    public int getNormalIcom() {
        return normalIcom;
    }

    public void setNormalIcom(int normalIcom) {
        this.normalIcom = normalIcom;
    }

    public int getNewNumber() {
        return newNumber;
    }

    public void setNewNumber(int newNumber) {
        this.newNumber = newNumber;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
