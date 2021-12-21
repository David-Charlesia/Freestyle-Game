package com.razachar.freestylegame.controller;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.razachar.freestylegame.R;
import com.razachar.freestylegame.model.Player;

import java.util.ArrayList;

public class AdapterClassement extends RecyclerView.Adapter {
    private ArrayList<Player> classement;

    public AdapterClassement(ArrayList<Player> classement){
        this.classement=classement;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.classement_item,parent,false);
        return new Classement_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Classement_Holder mHolder=(Classement_Holder)holder;
        Player player=classement.get(position);

        mHolder.setImg(player.getImg());
        mHolder.setName(player.getName());
        mHolder.setRate((position+1));
    }

    @Override
    public int getItemCount() {
        return classement.size();
    }

    public static class Classement_Holder extends RecyclerView.ViewHolder{
        private ImageView img;
        private TextView name;
        private TextView rate;
        public Classement_Holder(@NonNull View itemView) {
            super(itemView);
            this.img=itemView.findViewById(R.id.img_player_classement);
            this.name=itemView.findViewById(R.id.pseudo_player_classement);
            this.rate=itemView.findViewById(R.id.rate_player);
        }

        public void setImg(int res) {
            this.img.setImageResource(res);
        }

        public void setName(String name) {
            this.name.setText(name);
        }

        @SuppressLint("SetTextI18n")
        public void setRate(int rate) {
            this.rate.setText(Integer.toString(rate));
        }
    }
}
