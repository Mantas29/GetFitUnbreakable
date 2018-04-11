package com.mantas.getfit.getfitunbreakable;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.InputStream;
import java.util.UUID;

/**
 * Created by Mantas on 10/04/2018.
 */

public class StorageManager {

    public void storeFile(){

    }

    public static void uploadImage(InputStream filePath){

        FirebaseStorage storage;
        StorageReference storageReference;

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference("image.png");

            if(filePath != null)
            {
                storageReference.putStream(filePath);
            }
        }
    }

