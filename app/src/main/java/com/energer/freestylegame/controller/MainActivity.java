package com.energer.freestylegame.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.energer.freestylegame.controller.fragments.DiscoverFragment;
import com.energer.freestylegame.controller.fragments.HomeFragment;
import com.energer.freestylegame.R;
import com.energer.freestylegame.controller.fragments.RecFragment;
import com.energer.freestylegame.controller.fragments.SetgameFragment;
import com.energer.freestylegame.model.Player;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNav;
    Fragment selectedFragment = null;
    //FOR DATA
    // 1 - Identifier for Sign-In Activity
    private static final int RC_SIGN_IN = 123;

    private static final int info_request=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(FirebaseAuth.getInstance().getCurrentUser()==null){
            startSignInActivity();
        }else {
            setContentView(R.layout.activity_main);

            //deserialization :

            bottomNav = (BottomNavigationView) findViewById(R.id.bottom_navigation);


            //Listeners :
            bottomNav.setOnNavigationItemSelectedListener(navListener);

        /*
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.commit();
        */


            //set bot nav :

            bottomNav.setSelectedItemId(R.id.nav_home);
            Bundle extras = getIntent().getExtras();
            if (extras != null && extras.getInt("id") == 1) {
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new SetgameFragment(extras.<Player>getParcelableArrayList("players"))).commit();
            }
        }
    }


    //Listener :

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.nav_rec:
                    selectedFragment = new RecFragment();
                    break;
                case R.id.nav_home:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.nav_discover:
                    selectedFragment = new DiscoverFragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, selectedFragment).commit();

            return true;
        }
    };

    private void startSignInActivity() {
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(
                                Arrays.asList(new AuthUI.IdpConfig.EmailBuilder().build(),
                                        new AuthUI.IdpConfig.GoogleBuilder().build()))
                        .setIsSmartLockEnabled(false, true)
                        .build(),
                RC_SIGN_IN);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


                FirebaseFirestore.getInstance().collection("players").document(user.getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.getString("pseudo")==null){
                            setInfoUser(user);
                        }else{
                            startMain();
                        }
                    }
                });
            }else {
                startActivity(new Intent(this,SplashScreen.class));
            }
        }else if(requestCode==info_request) {
            if (resultCode == RESULT_OK) {
                startActivity(new Intent(this,MainActivity.class));
            }
        }
    }

    private void startMain(){
        startActivity(new Intent(this,MainActivity.class));
    }

    private void setInfoUser(com.google.firebase.auth.FirebaseUser user){
        Bundle bundle=new Bundle();
        bundle.putString("id", Objects.requireNonNull(user).getUid());
        Intent intent=new Intent(this,Information.class);
        intent.putExtras(bundle);
        startActivityForResult(intent,info_request);
    }


    /*
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }*/
}