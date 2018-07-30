# AavigationBar
底部导航

这个是自己做项目的时候，自己写的，通过自定义View去画的，我不是大牛，我只是努力爬坑的孩子，不喜欢勿喷，有什么问题希望大家可以一起交流学习
QQ 915832847
email wx_bin@sina.com

 ![image](https://github.com/ButBueatiful/dotvim/raw/master/screenshots/vim-screenshot.jpg)

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
	        implementation 'com.github.cocoandy:AavigationBar:NavigationBar_V1.0'
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
  

  
