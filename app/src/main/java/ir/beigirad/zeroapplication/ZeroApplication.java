package ir.beigirad.zeroapplication;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;

/**
 * Created by farhad-mbp on 7/31/17.
 */

public class ZeroApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext=getApplicationContext();


        Stetho.initializeWithDefaults(this);
    }

    public static Context getAppContext(){
        return mContext;
    }
}
