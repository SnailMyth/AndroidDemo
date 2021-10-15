package com.example.testdemo;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import com.facebook.common.logging.FLog;
import com.facebook.common.logging.FLogDefaultLoggingDelegate;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.tencent.mars.xlog.Xlog;

/**
 * @author :
 * @date : 2020/8/5 19:56
 * @description : AppContext
 */
public class AppContext extends Application{

    public static AppContext Context;
    public static boolean    sDeBug;
    private int mCount;
    private boolean mFront;//是否前台

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Context = this;
        sDeBug = BuildConfig.DEBUG;

        Fresco.initialize(this, ImagePipelineConfig.newBuilder(this)
            .experiment()
            .setWebpSupportEnabled(true)
            .build());

        if (sDeBug) {
            FLogDefaultLoggingDelegate.getInstance().setApplicationTag("Drawee-text");
            FLog.setMinimumLoggingLevel(Log.VERBOSE);
        }

        registerActivityLifecycleCallbacks();

        asyncInitSDK();
    }

    //异步初始化sdk
    private void asyncInitSDK() {

    }

    private final static String TAG = "AppContext";



    private void registerActivityLifecycleCallbacks() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {
                mCount++;
                if (!mFront) {
                    mFront = true;
                    Log.d("AppContext","AppContext------->处于前台");
                }
            }

            @Override
            public void onActivityResumed(Activity activity) {
            }

            @Override
            public void onActivityPaused(Activity activity) {
            }

            @Override
            public void onActivityStopped(Activity activity) {
                mCount--;
                if (mCount == 0) {
                    mFront = false;
                    Log.d("AppContext","AppContext------->处于后台");
                }
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
            }
        });
    }

}
