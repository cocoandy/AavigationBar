package com.wulias.navigation.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import com.wulias.navigation.bean.ItemBean;
import com.wulias.navigation.listener.OnFragmentInitialization;
import com.wulias.navigation.listener.OnNavigationClickListener;
import com.wulias.navigation.listener.OnNavigationScollingListener;

/**
 * Created by Administrator on 2018/7/30.
 */

public class NavigationBar extends View implements ViewPager.OnPageChangeListener {

    private Context context;

    //Fragment 管理器（用于切换Fragmet）
    private FragmentManager manager;

    //文字画笔
    private Paint mPaint;

    //字体样式
    private Paint.FontMetrics mFontMetrics;

    //控件宽度
    private int mWidth;

    //控件高度
    private int mHeight;

    //一个菜单的宽度(除去间隔后的)
    private int mItenWidth;

    //菜单数据
    private List<ItemBean> mDatas;

    //被选择的菜单项
    private int selectItem = -1;

    //被选择的菜单的文字颜色
    private int selectTextColor;

    //违背选择菜单的字体颜色
    private int normalTextColor;

    //菜单的点击事件
    private OnNavigationClickListener onNavigationListener;

    //切换 Fragment 的初始化回调
    private OnFragmentInitialization onInitialization;

    //切换 Fragment 的初始化回调
    private OnNavigationScollingListener onScollingListener;

    //当前显示的界面
    private Fragment currFragment;

    //fragment 显示的布局的id
    private int fragmentId;

    //ViewPage对象(底部菜单+ViewPage)
    private ViewPager viewPager;

    public NavigationBar(Context context) {
        this(context, null);
    }

    public NavigationBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NavigationBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    public void init() {
        mDatas = new ArrayList<>();
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(dip2px(11));
        mFontMetrics = mPaint.getFontMetrics();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mWidth = w;
        mHeight = h;
        if (!isEmpty(mDatas)) {
            mItenWidth = mWidth / mDatas.size();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0, len = mDatas.size(); i < len; i++) {
            ItemBean item = mDatas.get(i);
            Bitmap bitmap = null;

            if (selectItem == i) {
                mPaint.setColor(selectTextColor);
                bitmap = BitmapFactory.decodeResource(context.getResources(), item.getSelectIcon());
            } else {
                mPaint.setColor(normalTextColor);
                bitmap = BitmapFactory.decodeResource(context.getResources(), item.getNormalIcom());
            }
            int textWidth = (int) mPaint.measureText(item.getTitle());
            //标题位置 x : 前面item总长 mItenWidth * i 加上 自身item长度减去 自身显示文字长度 再取一半 y : 控件高度减去文字预留空间(mHeight - dip2px(15) / 2 这个是得到预留给图片的空间)
            canvas.drawText(item.getTitle(), mItenWidth * i + (mItenWidth - textWidth) / 2, (mHeight - dip2px(15) / 2), mPaint);

            float bitmapHeight = bitmap.getHeight();
            // 计算图片缩略大小 ： 控件可显示高度/图片高度   注意： dip2px(15)文字所占空间高度 mHeight / 5 是四周的间隙大小
            float scal = (mHeight - dip2px(15) - mHeight / 5) / bitmapHeight * 1f;
            Matrix matrix = new Matrix();
            matrix.postScale(scal, scal, 0, 0);
            //计算图片平移距离 mHeight / 10 是类似panddingTop的值 上边mHeight / 5是因为包含上下距离 前面item总长 mItenWidth * i 加上 自身长度mHeight - dip2px(15) - mHeight / 5 包含了间隙
            matrix.postTranslate(mItenWidth * i + (mItenWidth - mHeight + dip2px(15) + mHeight / 5) / 2, mHeight / 10);
            canvas.drawBitmap(bitmap, matrix, null);
            bitmap.recycle();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //可以做按下的特效
                break;

            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:
                //只有松开的时候才做选择
                int position = getItemPosition((int) event.getX());
                selectCurrentItem(position);
                break;

        }

        return true;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //Viewpage 滑动过程（可扩展导航效果）
    }

    @Override
    public void onPageSelected(int position) {
        //滑动后更新菜单选择状态

        if (onScollingListener != null) {
            onScollingListener.onScollingListener(position, mDatas.get(position));
        }

        setCurrentView(position);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void selectCurrentItem(int position){
        if (viewPager != null) {
            setCurrentView(position);
        } else {
            setCurrentItem(position);
        }

        if (onNavigationListener != null) {
            onNavigationListener.onNavigationItem(position, mDatas.get(position));
        }
    }

    /**
     * ViewPage 设置当前显示的Fragment
     *
     * @param position
     */
    private void setCurrentView(int position) {
        selectItem = position;
        viewPager.setCurrentItem(selectItem, false);
        invalidate();
    }

    /**
     * 设置当前被选中的菜单
     *
     * @param position
     */
    private void setCurrentItem(int position) {
        if (onInitialization != null) {
            if (selectItem != position) {
                selectItem = position;
                changeFragment(onInitialization.onInitialization(position, mDatas.get(position)));
                invalidate();
            }
        }
    }


    /**
     * 获取菜单下标
     * 根据点击的位置获取到导航条的item下标
     *
     * @param x 点击的x坐标
     * @return
     */
    private int getItemPosition(int x) {
        int position = 0;
        if (mDatas.size() == 0 || mWidth == 0 || (mWidth / mDatas.size()) == 0) {
            return 0;
        }
        position = x / (mWidth / mDatas.size());
        if (selectItem > mDatas.size()) {
            position = mDatas.size() - 1;
        } else if (selectItem < 0) {
            position = 0;
        }

        return position;
    }


    private boolean isEmpty(List list) {
        if (list != null && list.isEmpty()) return true;
        return false;
    }

    /**
     * @param dpValue
     * @return
     */
    public int dip2px(float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 添加菜单的数据
     *
     * @param title      菜单的标题
     * @param selectIcon 被选择的字体颜色
     * @param normalIcom 正常显示的字体颜色
     */
    public void addItem(String title, int normalIcom, int selectIcon) {
        addItem(title, "", normalIcom, selectIcon);
    }

    public void addItem(String title, String tag, int normalIcom, int selectIcon) {
        ItemBean item = new ItemBean();
        item.setTitle(title);
        item.setSelectIcon(selectIcon);
        item.setNormalIcom(normalIcom);
        item.setTag(tag);
        mDatas.add(item);
    }

    /**
     * 更新导航某一项
     * @param position
     * @param title
     * @param tag
     * @param normalIcom
     * @param selectIcon
     */
    public void updataItem(int position, String title, String tag, int normalIcom, int selectIcon) {
        if (position < mDatas.size() && position >= 0) {
            mDatas.get(position).setTitle(title);
            mDatas.get(position).setSelectIcon(selectIcon);
            mDatas.get(position).setNormalIcom(normalIcom);
            mDatas.get(position).setTag(tag);
            selectCurrentItem(position);
            invalidate();
        }
    }


    /**
     * 设置绑定 ViewPager
     *
     * @param viewPager
     */
    public void bindViewPage(ViewPager viewPager) {
        this.selectItem = 0;
        this.viewPager = viewPager;
        this.viewPager.addOnPageChangeListener(this);
    }

    public void setFragmentManager(FragmentManager manager, int fragmentId) {
        this.manager = manager;
        this.fragmentId = fragmentId;
    }

    /**
     * 导航之点击事件监听
     *
     * @param onNavigationListener
     */
    public void addOnNavigationListener(OnNavigationClickListener onNavigationListener) {
        this.onNavigationListener = onNavigationListener;
        setCurrentItem(0);
    }

    /**
     * 导航之界面切换 界面实例化
     *
     * @param onInitialization
     */
    public void addOnInitialization(OnFragmentInitialization onInitialization) {
        this.onInitialization = onInitialization;
    }

    /**
     * 设置被选中字体颜色
     *
     * @param selectTextColor
     */
    public void setSelectTextColor(int selectTextColor) {
        this.selectTextColor = context.getResources().getColor(selectTextColor);
    }

    /**
     * 设置字体正常显示颜色
     *
     * @param normalTextColor
     */
    public void setNormalTextColor(int normalTextColor) {
        this.normalTextColor = context.getResources().getColor(normalTextColor);
    }


    /**
     * 切换fragment 不会再次初始化
     *
     * @param fragment
     */
    public void changeFragment(Fragment fragment) {
        if (fragmentId == 0 || fragment == null) return;

        FragmentTransaction beginTransaction = manager.beginTransaction();

        if (!fragment.isAdded()) {
            beginTransaction.add(fragmentId, fragment, String.valueOf(selectItem));
        } else {
            beginTransaction.show(fragment);
        }
        if (currFragment != null) {
            beginTransaction.hide(currFragment);
        }
        beginTransaction.commit();

        currFragment = fragment;
    }

}
