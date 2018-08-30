# AavigationBar
底部导航

这个是自己做项目的时候，自己写的，通过自定义View去画的，我不是大牛，我只是努力爬坑的孩子，不喜欢勿喷，有什么问题希望大家可以一起交流学习

QQ 915832847

email wx_bin@sina.com

 ![image](https://github.com/cocoandy/AavigationBar/blob/master/res_intr/20545743895669897.png)

## 1、android studio Gradle 依赖

#### 1、工程下的Gradle
``` 
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	} 
 ```
  
#### 2、项目下的Gradle
``` 
dependencies {
	        implementation 'com.github.cocoandy:AavigationBar:NavigationBar_V1.1'
	}
```
## 2、用法解析
  ### 1、单独使用 Navigation + Fragment
  
    1、布局
    ```
    <FrameLayout
        android:id="@+id/main_fragment"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_above="@+id/menu"/>

    <com.wulias.navigation.widget.NavigationBar
        android:id="@+id/menu"
        android:layout_alignParentBottom="true"
        android:layout_width="match_parent"
        android:layout_height="48dp"
        />
       ````
     2、activity中：
     ```
     navigationBar.addOnInitialization(this);//初始化回调，一定要设置，非Viewpage
        navigationBar.addOnNavigationListener(this);

        navigationBar.setFragmentManager(getSupportFragmentManager(), R.id.main_fragment);//设置管理  R.id.main_fragment是fragment要显示的地方
        // 字体颜色
        navigationBar.setSelectTextColor(R.color.colorPrimary);
        navigationBar.setNormalTextColor(R.color.bottom_textcolor);
        //给导航添加item
        navigationBar.addItem("首页", R.mipmap.ic_main_home, R.mipmap.ic_main_home_select);
        navigationBar.addItem("订场地", R.mipmap.ic_main_ground, R.mipmap.ic_main_ground_select);
        navigationBar.addItem("购物车", R.mipmap.ic_main_car, R.mipmap.ic_main_car_select);
        navigationBar.addItem("我的", R.mipmap.ic_main_self, R.mipmap.ic_main_self_select);

        //设置默认 这个没有默认，需要用户自己设置
        navigationBar.setCurrentItem(0);
        ```
        
       3、初始化回调(可以根据tag 来初始化，这个用于有很多个Fragment 但是有时需要显示其中几个，就可以根据TAG 来决定显示谁)
       ```
       @Override
    public Fragment onInitialization(int position,ItemBean bean) {
        switch (position) {
            case 0:
                if (aFragment == null) {
                    aFragment = new AFragment();
                }
                return aFragment;
            case 1:
                if (bFragment == null) {
                    bFragment = new BFragment();
                }
                return bFragment;
            case 2:
                if (cFragment == null) {
                    cFragment = new CFragment();
                }
                return cFragment;
            case 3:
                if (dFragment == null) {
                    dFragment = new DFragment();
                }
                return dFragment;
        }
        return null;
    }
       ```
       
   ### 2、ViewPage+NavigationBar
   
   1、布局
   ```
   <?xml version="1.0" encoding="utf-8"?>
    <RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"

        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context="com.wulias.menu.ViewPageNavigationActivity">

        <android.support.v4.view.ViewPager
            android:id="@+id/viewpager"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:layout_above="@+id/menu" />

        <com.wulias.navigation.widget.NavigationBar
            android:id="@+id/menu"
            android:layout_width="match_parent"
            android:layout_height="48dp"
            android:layout_alignParentBottom="true" />
    </RelativeLayout>
   ```
   
   2、代码
   ```
        navigationBar.bindViewPage(viewPager);//绑定Viewpage
        navigationBar.setSelectTextColor(R.color.colorPrimary);
        navigationBar.setNormalTextColor(R.color.bottom_textcolor);
        navigationBar.addItem("首页", R.mipmap.ic_main_home, R.mipmap.ic_main_home_select);
        navigationBar.addItem("订场地", R.mipmap.ic_main_ground, R.mipmap.ic_main_ground_select);
        navigationBar.addItem("购物车", R.mipmap.ic_main_car, R.mipmap.ic_main_car_select);
        navigationBar.addItem("我的", R.mipmap.ic_main_self, R.mipmap.ic_main_self_select);
        navigationBar.addOnNavigationListener(this); //点击事件
   ```
   3、回调
   ```
    @Override
    public void onNavigationItem(int position, ItemBean bean) {
        Toast.makeText(this, "你选的的是：" + bean.getTitle(), Toast.LENGTH_SHORT).show();
    }
    
    @Override
    public void onScollingListener(int position, ItemBean bean) {
        Toast.makeText(this, "你滑到当前的：" + bean.getTitle(), Toast.LENGTH_SHORT).show();
    }
   ```
        
代码简简单单 使用也是简简单单

### 3、关于导航的方法：
```
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
```
  

  
