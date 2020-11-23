package com.energer.freestylegame.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.energer.freestylegame.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Information extends AppCompatActivity {
    private Button btn_save;
    private EditText pseudo;

    private DocumentReference doc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        String id= Objects.requireNonNull(this.getIntent().getExtras()).getString("id");
        doc= FirebaseFirestore.getInstance().collection("players")
                .document(id);

        pseudo=(EditText)findViewById(R.id.pseudo_info);

        btn_save=(Button)findViewById(R.id.save_info);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> info=new HashMap<>();
                info.put("pseudo",pseudo.getText().toString());

                doc.set(info).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Intent returnIntent=new Intent();
                        setResult(RESULT_OK,returnIntent);
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("bruh","nope");
                    }
                });
            }
        });
    }
}
