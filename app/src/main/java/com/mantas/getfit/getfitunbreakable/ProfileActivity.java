package com.mantas.getfit.getfitunbreakable;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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
        populateData();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    public void signOut(View view){
        FirebaseAuth.getInstance().signOut();
        //Logging out from facebook
        LoginManager.getInstance().logOut();
        //restarting application
        Intent i = getBaseContext().getPackageManager()
                .getLaunchIntentForPackage( getBaseContext().getPackageName() );
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    private void populateData(){
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        String displayName = currentUser.getDisplayName();
        String email = currentUser.getEmail();
        String photoUri = currentUser.getPhotoUrl().toString();

        userNameLabel.setText(displayName);
        userEmailLabel.setText(email);
        new DownLoadImageTask(profilePicture).execute(photoUri);
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
