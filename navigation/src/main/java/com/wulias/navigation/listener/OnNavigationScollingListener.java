package com.wulias.navigation.listener;

import com.wulias.navigation.bean.ItemBean;

/**
 *
 * 在ViewPage的时候 滑动监听
 * Created by Administrator on 2018/7/30.
 */

public interface OnNavigationScollingListener {
    void onScollingListener(int position, ItemBean bean);
}
