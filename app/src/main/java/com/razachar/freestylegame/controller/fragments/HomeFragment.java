package com.razachar.freestylegame.controller.fragments;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.razachar.freestylegame.R;
import com.razachar.freestylegame.controller.SplashScreen;
import com.razachar.freestylegame.model.Player;
import com.firebase.ui.auth.AuthUI;

import java.util.ArrayList;
import java.util.Objects;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import static com.google.firebase.auth.FirebaseAuth.getInstance;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private TextView welcome;

    private static final String id= Objects.requireNonNull(getInstance().getCurrentUser()).getUid();

    private static final DocumentReference player_doc= FirebaseFirestore.getInstance().collection("players").document(id);

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View result =inflater.inflate(R.layout.fragment_home, container, false);

        FrameLayout layout=(FrameLayout)result.findViewById(R.id.home_layout);
        AnimationDrawable animation=(AnimationDrawable)layout.getBackground();
        animation.setEnterFadeDuration(2000);
        animation.setExitFadeDuration(3500);
        animation.start();

        ImageButton btn=(ImageButton)result.findViewById(R.id.play);
        welcome=(TextView)result.findViewById(R.id.welcome);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ArrayList<Player> players=new ArrayList<>();
                player_doc.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        players.add(new Player(documentSnapshot.getString("pseudo"),id));
                        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new SetgameFragment(players)).commit();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("ouppps","dsl");
                    }
                });

            }
        });

        Button btn_out=(Button)result.findViewById(R.id.sign_out);
        btn_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AuthUI.getInstance()
                        .signOut(requireContext())
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.i("ok","sign out ok");
                                startActivity(new Intent(getContext(), SplashScreen.class));
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.e("oups","pas si vite");
                            }
                        });
            }
        });

        player_doc.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String ch=getString(R.string.welcome)+" "+documentSnapshot.getString("pseudo");
                welcome.setText(ch);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("getPseudo","Error");
            }
        });

        return result;
    }

}
