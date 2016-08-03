package com.oicmap.game.libs.someonelikeyou.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by khacpham on 8/3/16.
 */
public class Position {

    @SerializedName("center")
    public Coord center;

    @SerializedName("eye_left")
    public Coord eyeLeft;

    @SerializedName("eye_right")
    public Coord eyeRight;

    @SerializedName("mouth_left")
    public Coord mouthLeft;

    @SerializedName("mouth_right")
    public Coord mouthRight;

    @SerializedName("nose")
    public Coord nose;

    @SerializedName("width")
    public float width;

    @SerializedName("height")
    public float height;

    public static class Coord {
        @SerializedName("x")
        public float x;

        @SerializedName("y")
        public float y;
    }
}
