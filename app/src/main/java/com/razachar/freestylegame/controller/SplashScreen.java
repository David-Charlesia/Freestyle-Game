package com.razachar.freestylegame.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

public class SplashScreen extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences user=this.getSharedPreferences("user",MODE_PRIVATE);

        if (FirebaseAuth.getInstance().getCurrentUser()==null) {
            startActivity(new Intent(this,PresentationActivity.class));
        } else {
            startActivity(new Intent(this,MainActivity.class));
        }
        finish();
    }
}
