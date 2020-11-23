package com.energer.freestylegame.controller;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.energer.freestylegame.R;
import com.energer.freestylegame.model.Player;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    private ArrayList<Player> listPlayer;

    public MyAdapter(ArrayList<Player> mlist){
        this.listPlayer=mlist;
    }

    public MyAdapter(){
        this.listPlayer=new ArrayList<Player>();
        listPlayer.add(new Player("Player #1"));
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);

        View view=inflater.inflate(R.layout.recyclerview_item,parent,false);

        ItemHolder mViewHolder=new ItemHolder(view);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ItemHolder mHolder=(ItemHolder)holder;

        Player player=listPlayer.get(position);

        mHolder.getName().setText(player.getName());
        mHolder.getImg().setImageResource(player.getImg());
    }

    @Override
    public int getItemCount() {
        if(listPlayer==null){
            return 0;
        }
        return listPlayer.size();
    }









    public class ItemHolder extends RecyclerView.ViewHolder {
        private EditText name;
        private ImageView img;


        public ItemHolder(@NonNull View itemView) {
            super(itemView);

            //deserialization
            this.name=itemView.findViewById(R.id.pseudo_player);
            this.img=itemView.findViewById(R.id.img_player);

            //set listener
            setlistener();
        }

        private void setlistener(){
            this.name.addTextChangedListener(pseudo_listener);
        }


        private TextWatcher pseudo_listener=new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                listPlayer.get(getAdapterPosition()).setName(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };



        public ImageView getImg() {return img;}

        public EditText getName() {return name;}
    }



}
