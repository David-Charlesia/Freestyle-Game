package com.razachar.freestylegame.model;

import java.util.List;

public class Word {
    private String[] words;
    private int id;
    public Word(){
        this.words=new String[5];
        getNewWords();
    }

    public Word(List<String> list,int id){
        this.words=new String[5];
        this.words[0]=list.get(0);
        this.words[1]=list.get(1);
        this.words[2]=list.get(2);
        this.words[3]=list.get(3);
        this.words[4]=list.get(4);
        this.id=id;
    }

    private void getNewWords(){
        this.words[0]="aviation";
        this.words[1]="avion";
        this.words[2]="classe affaire";
        this.words[3]="hôtesse";
        this.words[4]="air";
    }




    public String[] getWords(){
        return this.words;
    }

    public void setWords(String[] words) {
        this.words = words;
    }
}
