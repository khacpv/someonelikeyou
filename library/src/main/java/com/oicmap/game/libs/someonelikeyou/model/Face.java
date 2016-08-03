package com.oicmap.game.libs.someonelikeyou.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by khacpham on 8/3/16.
 */
public class Face {

    @SerializedName("attribute")
    public Attribute attribute;

    @SerializedName("face_id")
    public String faceId;

    @SerializedName("position")
    public Position position;

    @SerializedName("tag")
    public String tag;
}
