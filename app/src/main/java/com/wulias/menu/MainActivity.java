package com.wulias.menu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.item_a).setOnClickListener(this);
        findViewById(R.id.item_b).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.item_a:
                intent.setClass(this,ViewPageNavigationActivity.class);
                break;
            case R.id.item_b:
                intent.setClass(this,NavigationActivity.class);
                break;
        }
        startActivity(intent);
    }
}
