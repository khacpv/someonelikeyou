package com.oicmap.game.libs.someonelikeyou.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by khacpham on 8/3/16.
 */
public class Age {

    @SerializedName("range")
    public int range;

    @SerializedName("value")
    public int value;

    public int getFrom(){
        return value - range;
    }

    public int getTo(){
        return value + range;
    }
}
