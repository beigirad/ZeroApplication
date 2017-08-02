package ir.beigirad.zeroapplication.ui;

import android.os.Bundle;

import ir.beigirad.zeroapplication.R;
import ir.beigirad.zeroapplication.component.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected CharSequence getToolbarTitle() {
        return "MainActivity";
    }

}
