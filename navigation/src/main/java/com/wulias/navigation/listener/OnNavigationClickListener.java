package com.wulias.navigation.listener;

import com.wulias.navigation.bean.ItemBean;

/**
 *
 * 导航点击事件监听
 * Created by Administrator on 2018/7/30.
 */

public interface OnNavigationClickListener {
    void onNavigationItem(int position, ItemBean bean);
}
