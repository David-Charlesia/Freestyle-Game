package com.energer.freestylegame.controller.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import com.energer.freestylegame.controller.MyAdapter;
import com.energer.freestylegame.model.Player;
import com.energer.freestylegame.R;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SetgameFragment extends Fragment{

    private ArrayList<Player> players;
    private MyAdapter adapter;

    private TextView aff_nb_players;
    private ImageButton btn_plus;
    private ImageButton btn_moins;
    private ImageButton btn_play;




    public SetgameFragment() {
        this.players= new ArrayList<>();
    }

    public SetgameFragment(ArrayList<Player> players){
        this.players=players;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View result=inflater.inflate(R.layout.fragment_setgame, container, false);

        //deserialization
        RecyclerView mRecyclerView=(RecyclerView)result.findViewById(R.id.recyclerview);
        final View loading=result.findViewById(R.id.loading);
        final View layout_set=result.findViewById(R.id.layout_set);

        //create adapter
        adapter=new MyAdapter(players);

        //attach adapter to recyclerview
        mRecyclerView.setAdapter(adapter);

        //set layout manager to position the items
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //deserialization :
        btn_moins=(ImageButton)result.findViewById(R.id.button_moins);
        btn_plus=(ImageButton)result.findViewById(R.id.button_plus);
        btn_play=(ImageButton)result.findViewById(R.id.button_play);
        aff_nb_players=(TextView)result.findViewById(R.id.textview_nbplayer);

        //setListener
        setListener();


        // Inflate the layout for this fragment
        layout_set.setVisibility(View.VISIBLE);
        loading.setVisibility(View.GONE);
        return result;
    }

    private void setListener(){
        btn_plus.setOnClickListener(listener_btn);
        btn_moins.setOnClickListener(listener_btn);
        btn_play.setOnClickListener(listener_btn);
    }

    //listeners :

    private View.OnClickListener listener_btn=new View.OnClickListener() {
        @SuppressLint("SetTextI18n")
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.button_moins:
                    if(players.size()>1){
                        players.remove(players.size()-1);
                        adapter.notifyItemRemoved(players.size());
                        aff_nb_players.setText(Integer.toString(players.size()));
                    }
                    break;

                case R.id.button_plus:
                    players.add(new Player("Player #"+(players.size()+1)));
                    adapter.notifyItemInserted(players.size()-1);
                    aff_nb_players.setText(Integer.toString(players.size()));
                    break;

                case R.id.button_play:
                    Intent GameActivity=new Intent(getContext(), com.energer.freestylegame.controller.GameActivity.class);

                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList("players", players);
                    GameActivity.putExtras(bundle);

                    startActivity(GameActivity);
                    break;
            }
        }
    };

}
