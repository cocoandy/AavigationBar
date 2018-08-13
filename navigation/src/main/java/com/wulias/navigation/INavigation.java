package com.wulias.navigation;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.wulias.navigation.listener.OnFragmentInitialization;
import com.wulias.navigation.listener.OnNavigationClickListener;

/**
 * Created by 曹小贼 on 2018/8/13.
 */

public interface INavigation {

    /**
     * 添加菜单的数据
     *
     * @param title      菜单的标题
     * @param selectIcon 被选择的字体颜色
     * @param normalIcom 正常显示的字体颜色
     */
    void addItem(String title, int normalIcom, int selectIcon);

    /**
     * 添加菜单的数据
     *
     * @param title      菜单的标题
     * @param tag        标记
     * @param normalIcom 正常显示的字体颜色
     * @param selectIcon 被选择的字体颜色
     */
    void addItem(String title, String tag, int normalIcom, int selectIcon);

    /**
     * 更新导航某一项
     *
     * @param position   更新的第几项
     * @param title      更新的标题
     * @param tag        更新的标签
     * @param normalIcom 更新图标
     * @param selectIcon 更新选中图标
     */
    void updataItem(int position, String title, String tag, int normalIcom, int selectIcon);

    /**
     * 设置绑定 ViewPager
     *
     * @param viewPager
     */
    void bindViewPage(ViewPager viewPager);

    /**
     * 选择选中菜单item
     * @param position item下标
     */
    void selectCurrentItem(int position);

    /**
     * 设置Fragment管理器和fragment要显示的布局的id
     *
     * @param manager
     * @param fragmentId
     */
    void setFragmentManager(FragmentManager manager, int fragmentId);

    /**
     * 导航之点击事件监听
     *
     * @param onNavigationListener
     */
    void addOnNavigationListener(OnNavigationClickListener onNavigationListener);

    /**
     * 导航之界面切换 界面实例化
     *
     * @param onInitialization
     */
    void addOnInitialization(OnFragmentInitialization onInitialization);

    /**
     * 设置被选中字体颜色
     *
     * @param selectTextColor
     */
    void setSelectTextColor(int selectTextColor);

    /**
     * 设置字体正常显示颜色
     *
     * @param normalTextColor
     */
    void setNormalTextColor(int normalTextColor);

    /**
     * 设置字体大小 默认是11sp
     */
    void setTextSize(int textSize);

    /**
     * 设置图片到顶部的距离 默认是3dp
     */
    void setImgPandTop(int imgPandTop);

    /**
     * 设置图片到文字间距离 默认8dp
     */
    void setImgPandBottom(int imgPandBottom);

    /**
     * 设置文字到底部的距离 默认是0
     */
    void setTvPandBottom(int tvPandBottom);
}
