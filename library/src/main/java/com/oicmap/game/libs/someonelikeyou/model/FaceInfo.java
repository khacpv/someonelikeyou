package com.oicmap.game.libs.someonelikeyou.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by khacpham on 8/4/16.
 */
public class FaceInfo {

    @SerializedName("img_id")
    public String imgId;

    @SerializedName("face")
    public List<Face> faces;

    @SerializedName("img_height")
    public int imgHeight;

    @SerializedName("img_width")
    public int imgWidth;

    @SerializedName("session_id")
    public String sessionId;

    @SerializedName("url")
    public String url;
}
