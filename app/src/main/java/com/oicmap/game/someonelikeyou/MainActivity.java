package com.oicmap.game.someonelikeyou;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facepp.error.FaceppParseException;
import com.oicmap.game.libs.someonelikeyou.FaceLibs;
import com.oicmap.game.libs.someonelikeyou.model.Face;
import com.oicmap.game.libs.someonelikeyou.model.FaceInfo;

import java.io.IOException;

/**
 * Facepp SDK Android test
 * <p/>
 * Look result at debug area.(Log cat)
 *
 * @author moon5ckq
 */
public class MainActivity extends BaseActivity {

    private static final int CAMERA_REQUEST = 1888; // field

    private FaceLibs mLibrary;

    ImageView image;
    TextView text;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = (ImageView) findViewById(R.id.image);
        text = (TextView) findViewById(R.id.textView);

        mLibrary = FaceLibs.getInstance(this);

    }

    public void takePicture(View view){
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }

    public void uploadFile(View view){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final FaceInfo result = getLibrary().getDetection().detectBitmap(null);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            drawFaces(result);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void faceDetection(View view) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    String imageUrl = getString(R.string.ex_image_url);
                    final FaceInfo result = mLibrary.getDetection().detect(imageUrl);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            drawFaces(result);
                        }
                    });
                } catch (FaceppParseException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void drawFaces(FaceInfo faceInfo) {

        if(faceInfo == null){
            Toast.makeText(this,"server down",Toast.LENGTH_LONG).show();
            return;
        }

        StringBuilder strInfo = new StringBuilder();
        strInfo.append("id: "+faceInfo.imgId);
        strInfo.append("\n");
        strInfo.append(String.format("size: %s-%s", faceInfo.imgWidth,faceInfo.imgHeight));
        strInfo.append("\n");
        strInfo.append("session:"+faceInfo.sessionId);
        strInfo.append("\n");

        for (Face face : faceInfo.faces) {
            strInfo.append("the face: " + face.faceId);
            strInfo.append("\n");
            strInfo.append(String.format("from %s to %s years old", face.attribute.age.getFrom(), face.attribute.age.getTo()));
            strInfo.append("\n");
            strInfo.append(face.attribute.gender.value);
            strInfo.append("\n");
            strInfo.append(face.attribute.race.value + String.format(" (%s)", face.attribute.race.confidence));
            strInfo.append("\n");
            strInfo.append("smiling:" + (face.attribute.smiling.value > 50 ? "yes" : "no"));
        }

        text.setText(strInfo.toString());

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            final Bitmap picture = (Bitmap) data.getExtras().get("data");
            image.setImageBitmap(picture);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        final FaceInfo result = getLibrary().getDetection().detectBitmap(picture);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                drawFaces(result);
                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
