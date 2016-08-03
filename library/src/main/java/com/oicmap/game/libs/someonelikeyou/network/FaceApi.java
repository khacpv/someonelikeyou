package com.oicmap.game.libs.someonelikeyou.network;

import com.oicmap.game.libs.someonelikeyou.model.FaceInfo;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by khacpham on 8/4/16.
 */
public interface FaceApi {

    @Multipart
    @POST("detection/detect")
    Call<FaceInfo> detect(@Part("img") RequestBody img,
                          @Part MultipartBody.Part file);
}
