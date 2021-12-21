package com.razachar.freestylegame.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.razachar.freestylegame.R;
import com.razachar.freestylegame.model.Player;

import java.util.ArrayList;
import java.util.Objects;

public class EndgameActivity extends AppCompatActivity {

    private ArrayList<Player> classement;
    private Button btn_restart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endgame);

        classement= Objects.requireNonNull(this.getIntent().getExtras()).getParcelableArrayList("classement");

        btn_restart=(Button)findViewById(R.id.restart);
        RecyclerView mRecyclerView=(RecyclerView)findViewById(R.id.recyclerview_endgame);

        System.out.println(mRecyclerView.toString());

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        AdapterClassement mAdapter=new AdapterClassement(classement);
        mRecyclerView.setAdapter(mAdapter);

        btn_restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent MainActivity=new Intent(EndgameActivity.this,com.razachar.freestylegame.controller.MainActivity.class);
                Bundle bundle=new Bundle();
                bundle.putParcelableArrayList("players",classement);
                bundle.putInt("id",1);
                MainActivity.putExtras(bundle);

                startActivity(MainActivity);
            }
        });
    }
}
