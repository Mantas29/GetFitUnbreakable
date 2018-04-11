package com.mantas.getfit.getfitunbreakable;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import java.io.InputStream;
import java.net.URL;


public class TestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        // Get the widgets reference from XML layout
        final RelativeLayout rl = (RelativeLayout) findViewById(R.id.rl);
        Button btn = (Button) findViewById(R.id.btn);
        final ImageView iv = (ImageView) findViewById(R.id.iv);

        final String imgURL  = "https://lookaside.facebook.com/platform/profilepic/?asid=2030835956945575&height=100&width=100&ext=1523572072&hash=AeT0f75UEYzqWQSt";

        /*
            You need the following permission in your app AndroidManifest.xml file.
                <uses-permission android:name="android.permission.INTERNET" />
         */
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                    Download the logo from online and set it as
                    ImageView image programmatically.
                */
                new DownLoadImageTask(iv).execute(imgURL);
            }
        });
    }

    /*
        AsyncTask enables proper and easy use of the UI thread. This class
        allows to perform background operations and publish results on the UI
        thread without having to manipulate threads and/or handlers.
     */

    /*
        final AsyncTask<Params, Progress, Result>
            execute(Params... params)
                Executes the task with the specified parameters.
     */
    private class DownLoadImageTask extends AsyncTask<String,Void,Bitmap>{
        ImageView imageView;

        public DownLoadImageTask(ImageView imageView){
            this.imageView = imageView;
        }

        /*
            doInBackground(Params... params)
                Override this method to perform a computation on a background thread.
         */
        protected Bitmap doInBackground(String...urls){
            String urlOfImage = urls[0];
            Bitmap logo = null;
            try{
                InputStream is = new URL(urlOfImage).openStream();
                /*
                    decodeStream(InputStream is)
                        Decode an input stream into a bitmap.
                 */
                logo = BitmapFactory.decodeStream(is);
            }catch(Exception e){ // Catch the download exception
                e.printStackTrace();
            }
            return logo;
        }

        /*
            onPostExecute(Result result)
                Runs on the UI thread after doInBackground(Params...).
         */
        protected void onPostExecute(Bitmap result){
            imageView.setImageBitmap(result);
        }
    }
}