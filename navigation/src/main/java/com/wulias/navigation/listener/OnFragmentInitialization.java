package com.wulias.navigation.listener;

import android.support.v4.app.Fragment;

import com.wulias.navigation.bean.ItemBean;

/**
 * 界面初始化回调接口
 * Created by Administrator on 2018/7/30.
 */

public interface OnFragmentInitialization {
    Fragment onInitialization(int position, ItemBean bean);
}
