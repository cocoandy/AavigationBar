package com.wulias.menu;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.wulias.navigation.bean.ItemBean;
import com.wulias.navigation.listener.OnNavigationClickListener;
import com.wulias.navigation.widget.NavigationBar;

import java.util.ArrayList;
import java.util.List;

public class ViewPageNavigationActivity extends AppCompatActivity implements OnNavigationClickListener {

    ViewPager viewPager;
    NavigationBar navigationBar;
    AFragment aFragment;
    BFragment bFragment;
    CFragment cFragment;
    DFragment dFragment;
    List<Fragment> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_page_navigation);
        navigationBar = findViewById(R.id.menu);
        viewPager = findViewById(R.id.viewpager);

        aFragment = new AFragment();
        bFragment = new BFragment();
        cFragment = new CFragment();
        dFragment = new DFragment();

        mList =new ArrayList<>();
        mList.add(aFragment);
        mList.add(bFragment);
        mList.add(cFragment);
        mList.add(dFragment);

        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return mList.size();
            }

            @Override
            public Fragment getItem(int position) {
                return mList.get(position);
            }
        };

        viewPager.setAdapter(adapter);

        navigationBar.bindViewPage(viewPager);//绑定Viewpage
        navigationBar.setSelectTextColor(R.color.colorPrimary);
        navigationBar.setNormalTextColor(R.color.bottom_textcolor);
        navigationBar.addItem("首页", R.mipmap.ic_main_home, R.mipmap.ic_main_home_select);
        navigationBar.addItem("订场地", R.mipmap.ic_main_ground, R.mipmap.ic_main_ground_select);
        navigationBar.addItem("购物车", R.mipmap.ic_main_car, R.mipmap.ic_main_car_select);
        navigationBar.addItem("我的", R.mipmap.ic_main_self, R.mipmap.ic_main_self_select);
        navigationBar.addOnNavigationListener(this); //点击事件
    }

    @Override
    public void onNavigationItem(int position, ItemBean bean) {
        Toast.makeText(this, "你选的的是：" + bean.getTitle(), Toast.LENGTH_SHORT).show();
    }
}
