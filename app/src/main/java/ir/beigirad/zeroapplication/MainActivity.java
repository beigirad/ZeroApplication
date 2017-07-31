package ir.beigirad.zeroapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import ir.beigirad.zeroapplication.R;

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
