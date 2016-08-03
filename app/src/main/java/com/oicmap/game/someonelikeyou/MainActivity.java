package com.oicmap.game.someonelikeyou;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import com.faceplusplus.api.FaceDetecter;
import com.facepp.error.FaceppParseException;
import com.facepp.http.HttpRequests;
import com.facepp.http.PostParameters;
import com.facepp.result.FaceppResult;
import java.io.IOException;
import org.json.JSONObject;

/**
 * Facepp SDK Android test
 *
 * Look result at debug area.(Log cat)
 * @author moon5ckq
 */
public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FaceDetecter detecter = new FaceDetecter();
        detecter.init(this,"038d3322ea5b6930755b3d453d2afa1a");
        FaceDetecter.Face[] faceinfo = detecter.findFaces(BitmapFactory.decodeResource(getResources(),R.drawable.image));
        Log.e("TAG","num face: "+faceinfo.length);


    }

    public void faceDetection(View view){
        new Thread(new Runnable() {
            public void run() {
                HttpRequests httpRequests = new HttpRequests("1f36a8542848a8d6f53dfc0054f52a0d", "PCzer74o5QJRZzr5AzuQhckacjU9qSRl", false, true);

                JSONObject resultJson = null;
                try {
                    PostParameters postParameters =
                        new PostParameters()
                            .setUrl("http://fullhdpictures.com/wp-content/uploads/2016/01/Most-Beautiful-Face-Girl-Wallpaper.jpg")
                            .setAttribute("all");

                    postParameters.getMultiPart().writeTo(System.out);

                    FaceppResult result = httpRequests.request("detection","detect",postParameters);

                    System.out.println("FacePlusPlus API Test:" + result.get("face").getCount());
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                catch (FaceppParseException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
