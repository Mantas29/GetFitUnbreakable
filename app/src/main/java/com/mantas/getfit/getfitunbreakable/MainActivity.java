package com.mantas.getfit.getfitunbreakable;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void LaunchProfileActivity(View view){
        Intent profileActivityIntent = new Intent(this, ProfileActivity.class);
        startActivity(profileActivityIntent);
    }

    public void LaunchTestActivity(View view){
        Intent testActivityIntent = new Intent(this, TestActivity.class);
        startActivity(testActivityIntent);
    }

    public void CreateNewUser(View view){
        User user = new User("Testas Testuotojas", "testas.testuotojas@gmail.com");
        String userKey = UserManager.AddUserToDatabase(user);
        System.out.println(userKey);
    }




}
