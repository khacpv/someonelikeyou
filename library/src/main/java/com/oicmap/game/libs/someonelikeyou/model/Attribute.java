package com.oicmap.game.libs.someonelikeyou.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by khacpham on 8/3/16.
 */
public class Attribute {

    @SerializedName("age")
    public Age age;

    @SerializedName("gender")
    public Gender gender;

    @SerializedName("race")
    public Race race;

    @SerializedName("smiling")
    public Smiling smiling;
}
