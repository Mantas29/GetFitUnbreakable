package com.mantas.getfit.getfitunbreakable;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.login.LoginManager;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.URL;


public class ProfileActivity extends AppCompatActivity {

    private TextView userNameLabel;
    private TextView userEmailLabel;
    private ImageView profilePicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        userNameLabel = (TextView) findViewById(R.id.user_name_label);
        userEmailLabel = (TextView) findViewById(R.id.user_email_label);
        profilePicture = findViewById(R.id.user_profile_picture);
    }

    @Override
    protected void onStart() {
        super.onStart();
        populateData();
    }

    private void populateData(){
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();

        String displayName = currentUser.getDisplayName();
        String email = currentUser.getEmail();
        final String photoUri = currentUser.getPhotoUrl().toString();

        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        StorageReference storageReference = firebaseStorage.getReference();
        storageReference.child("profilePictures/" + currentUser.getUid()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(profilePicture);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                new DownLoadImageTask(profilePicture).execute(photoUri);
            }
        });

        userNameLabel.setText(displayName);
        userEmailLabel.setText(email);

    }

    public void navigateToSettingsActivity(View view){
        Intent settingsActivityIntent = new Intent(this, SettingsActivity.class);


        Pair[] pairs = new Pair[3];
        pairs[0] = new Pair<View, String>(userNameLabel, "user_name_transition");
        pairs[1] = new Pair<View, String>(userEmailLabel, "user_email_transition");
        pairs[2] = new Pair<View, String>(profilePicture, "user_profile_picture_transition");

        ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(this, pairs);

        startActivity(settingsActivityIntent, activityOptions.toBundle());
    }



}
