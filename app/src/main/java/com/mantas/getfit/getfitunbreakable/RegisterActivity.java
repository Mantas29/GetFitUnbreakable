package com.mantas.getfit.getfitunbreakable;

import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    static final String TAG = "EMAILREGISTER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_register);
    }

    public void onClickRegister(View view){
        TextView emailView = (TextView) findViewById(R.id.email_input);
        TextView passwordView = (TextView) findViewById(R.id.password_input);
        TextView repeatPasswordView = (TextView) findViewById(R.id.repeat_password_input);
        TextView fullNameView = (TextView) findViewById(R.id.full_name_input);

        String email = emailView.getText().toString();
        String password = passwordView.getText().toString();
        String repeatPassword = repeatPasswordView.getText().toString();
        String fullName = fullNameView.getText().toString();

        if (email.trim().equals("") || password.trim().equals("") || repeatPassword.trim().equals("") || fullName.trim().equals("")){
            Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_LONG).show();
            resetPasswordFields(passwordView, repeatPasswordView);
            return;
        }

        if(!isEmailValid(email)){
            Toast.makeText(this, "Email format is incorrect", Toast.LENGTH_LONG).show();
            resetPasswordFields(passwordView, repeatPasswordView);
            return;
        }

        if(!password.equals(repeatPassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_LONG).show();
            resetPasswordFields(passwordView, repeatPasswordView);
            return;
        }

        if(password.length() < 6){
            Toast.makeText(this, "Password must be at least 6 characters long", Toast.LENGTH_LONG).show();
            resetPasswordFields(passwordView, repeatPasswordView);
            return;
        }

        createAccount(fullName ,email, password);

    }

    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    void resetPasswordFields(TextView password, TextView repeatPassword){
        password.setText("");
        repeatPassword.setText("");
    }

    public void createAccount(final String fullName, String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            UserManager.updateUserDisplayName(fullName, user);
                            updateUI(user);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }

    private void updateUI(FirebaseUser currentUser) {
        System.out.println("CURRENT USER IS ::::::::::::::::::::" + currentUser.getUid());
        Intent mainActivityIntent = new Intent(this, MainActivity.class);
        startActivity(mainActivityIntent);
    }


}
