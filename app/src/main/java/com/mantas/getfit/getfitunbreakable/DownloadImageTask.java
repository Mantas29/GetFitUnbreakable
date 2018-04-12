package com.mantas.getfit.getfitunbreakable;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by Mantas on 11/04/2018.
 */

class DownLoadImageTask extends AsyncTask<String,Void,Bitmap> {
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
        if(result != null)
            imageView.setImageBitmap(result);
        else
            imageView.setImageResource(R.drawable.ic_user);

    }
}