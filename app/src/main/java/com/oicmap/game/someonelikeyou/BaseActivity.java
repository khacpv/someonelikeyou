package com.oicmap.game.someonelikeyou;

import android.support.v7.app.AppCompatActivity;

import com.oicmap.game.libs.someonelikeyou.FaceLibs;

/**
 * Created by khacpham on 8/3/16.
 */
public class BaseActivity extends AppCompatActivity {

    protected FaceLibs getLibrary(){
        return ((MainApplication)(this.getApplication())).library();
    }
}
