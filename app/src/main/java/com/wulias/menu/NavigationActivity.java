package com.wulias.menu;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.wulias.navigation.bean.ItemBean;
import com.wulias.navigation.listener.OnFragmentInitialization;
import com.wulias.navigation.listener.OnNavigationClickListener;
import com.wulias.navigation.widget.NavigationBar;

public class NavigationActivity extends AppCompatActivity implements OnFragmentInitialization,OnNavigationClickListener{

    NavigationBar navigationBar;
    AFragment aFragment;
    BFragment bFragment;
    CFragment cFragment;
    DFragment dFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        navigationBar = findViewById(R.id.menu);



        navigationBar.setFragmentManager(getSupportFragmentManager(), R.id.main_fragment);//设置管理  R.id.main_fragment是fragment要显示的地方
        // 字体颜色
        navigationBar.setSelectTextColor(R.color.colorPrimary);
        navigationBar.setNormalTextColor(R.color.bottom_textcolor);
        //给导航添加item
        navigationBar.addItem("首页", R.mipmap.ic_main_home, R.mipmap.ic_main_home_select);
        navigationBar.addItem("订场地", R.mipmap.ic_main_ground, R.mipmap.ic_main_ground_select);
        navigationBar.addItem("购物车", R.mipmap.ic_main_car, R.mipmap.ic_main_car_select);
        navigationBar.addItem("我的", R.mipmap.ic_main_self, R.mipmap.ic_main_self_select);
//        navigationBar.addOnInitialization(this);//初始化回调，一定要设置，非Viewpage
        navigationBar.addOnNavigationListener(this);
        //设置默认 这个没有默认，需要用户自己设置
//        navigationBar.selectCurrentItem(0);
    }

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

    @Override
    public void onNavigationItem(int position,ItemBean bean) {
        Toast.makeText(this,"你选的的是："+bean.getTitle(),Toast.LENGTH_SHORT).show();
    }
}
