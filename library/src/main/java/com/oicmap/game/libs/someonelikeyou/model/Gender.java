package com.oicmap.game.libs.someonelikeyou.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by khacpham on 8/3/16.
 */
public class Gender {

    @SerializedName("confidence")
    public float confidence;

    @SerializedName("value")
    public String value;    //Female or Male
}
