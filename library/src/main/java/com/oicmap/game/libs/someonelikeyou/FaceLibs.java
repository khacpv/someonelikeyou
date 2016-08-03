package com.oicmap.game.libs.someonelikeyou;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;

import com.faceplusplus.api.FaceDetecter;
import com.facepp.error.FaceppParseException;
import com.facepp.http.HttpRequests;
import com.facepp.http.PostParameters;
import com.facepp.result.FaceppResult;
import com.google.gson.Gson;
import com.oicmap.game.libs.someonelikeyou.model.FaceInfo;
import com.oicmap.game.libs.someonelikeyou.network.FaceApi;
import com.oicmap.game.libs.someonelikeyou.network.ServiceGenerator;
import com.oicmap.game.libs.someonelikeyou.utils.BitmapUtils;

import org.apache.http.annotation.NotThreadSafe;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;

/**
 * Created by khacpham on 8/3/16.
 */
public class FaceLibs {

    private static FaceLibs sInstance;

    private Context mContext;

    private Detection mDetection;

    private FaceLibs(Context context) {
        this.mContext = context;

        this.mDetection = new Detection();
    }

    public static FaceLibs getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new FaceLibs(context);
        }
        return sInstance;
    }

    @Deprecated
    public FaceDetecter getFaceDetect() {
        throw new RuntimeException("unsupported Jni library");
//        FaceDetecter faceDetecter = new FaceDetecter();
//        faceDetecter.init(mContext, LibConstant.API_KEY);
//        return faceDetecter;
    }

    @Deprecated
    public FaceDetecter.Face[] findFaces(Bitmap bmp) {
        throw new RuntimeException("unsupported Jni library");
//        return getFaceDetect().findFaces(bmp);
    }

    public Detection getDetection() {
        return mDetection;
    }

    @NotThreadSafe
    public static class Detection {

        private static final String ACTION = "detection";

        public static final String ATTRIB_ALL = "all";

        private HttpRequests httpRequests;

        public Detection() {
            httpRequests = new HttpRequests(LibConstant.API_KEY, LibConstant.API_SECRET, false, true);
        }

        /**
         * detection with all attribute
         */
        public FaceInfo detect(String imageUrl) throws IOException, FaceppParseException {
            return detect(imageUrl, ATTRIB_ALL);
        }

        /**
         * get detection info
         */
        public FaceInfo detect(String imageUrl, String attributes) throws IOException, FaceppParseException {
            PostParameters postParameters =
                    new PostParameters()
                            .setUrl(imageUrl)
                            .setAttribute(attributes);
            if (BuildConfig.DEBUG) {
                postParameters.getMultiPart().writeTo(System.out);
            }
            FaceppResult result = httpRequests.request(ACTION, "detect", postParameters);
            Gson gson = new Gson();
            return gson.fromJson(result.toString(), FaceInfo.class);
        }

        public FaceInfo detectBitmap(Bitmap bitmap) throws IOException {
            FaceApi service = ServiceGenerator.createService(FaceApi.class);

            File file;
            if(bitmap != null) {
                file = BitmapUtils.bitmap2File(bitmap, "soly" + System.currentTimeMillis());
            }else {
                String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
                Log.e("TAG", extStorageDirectory);
                file = new File(extStorageDirectory , "screenshot.png");
            }

            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);

            MultipartBody.Part body = MultipartBody.Part.createFormData("img", file.getName(), requestFile);

            String descriptionString = "hello, this is description speaking";
            RequestBody description =
                    RequestBody.create(
                            MediaType.parse("multipart/form-data"), descriptionString);

            Call<FaceInfo> call = service.detect(description, body);
            FaceInfo faceInfo = call.execute().body();
            if(faceInfo == null){
                Log.e("TAG","server return NULL");
            }
            return faceInfo;
        }
    }
}
