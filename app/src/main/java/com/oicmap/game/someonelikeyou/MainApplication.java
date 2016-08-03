package com.oicmap.game.someonelikeyou;

import android.app.Application;

import com.oicmap.game.libs.someonelikeyou.FaceLibs;

/**
 * Created by khacpham on 8/3/16.
 */
public class MainApplication extends Application {

    private FaceLibs mLibrary;

    @Override
    public void onCreate() {
        super.onCreate();
        mLibrary = FaceLibs.getInstance(this);
    }

    public FaceLibs library() {
        return mLibrary;
    }
}
