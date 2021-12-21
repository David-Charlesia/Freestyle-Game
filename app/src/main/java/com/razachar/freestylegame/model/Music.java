package com.razachar.freestylegame.model;

public class Music {
    private int id;
    private String title;
    private String author;
    private int start_bridge;
    private int start_verse;
    private int end_verse;
    private String url;

    public Music(){

    }

    public Music(int id,String title,String author,int start_bridge,int start_verse,int end_verse,String link_bd){
        this.id=id;
        this.title=title;
        this.author=author;
        this.start_bridge=start_bridge;
        this.start_verse=start_verse;
        this.end_verse=end_verse;
        this.url=url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getStart_bridge() {
        return start_bridge;
    }

    public void setStart_bridge(int start_bridge) {
        this.start_bridge = start_bridge;
    }

    public int getStart_verse() {
        return start_verse;
    }

    public void setStart_verse(int start_verse) {
        this.start_verse = start_verse;
    }

    public int getEnd_verse() {
        return end_verse;
    }

    public void setEnd_verse(int end_verse) {
        this.end_verse = end_verse;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String toString(){
        return this.title+" a été composer par "+this.author+".";
    }
}
