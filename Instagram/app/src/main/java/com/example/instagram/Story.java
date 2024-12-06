package com.example.instagram;

public class Story {

    private boolean seen;
    private String id;
    private int imgId;
    int like=0;
    int save=0;

    public Story(boolean seen, String id, int imgId) {
        this.seen = seen;
        this.id = id;
        this.imgId = imgId;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
}
