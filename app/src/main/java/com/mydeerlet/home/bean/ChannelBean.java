package com.mydeerlet.home.bean;

public class ChannelBean {
    private int position;
    private String catid;
    private String colname;
    private String url;
    private int spanSize;
    private boolean isLock;


    public ChannelBean(int position, String catid, String colname, String url, int spanSize, boolean isLock) {
        this.position = position;
        this.catid = catid;
        this.colname = colname;
        this.url = url;
        this.spanSize = spanSize;
        this.isLock = isLock;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getCatid() {
        return catid;
    }

    public void setCatid(String catid) {
        this.catid = catid;
    }

    public String getColname() {
        return colname;
    }

    public void setColname(String colname) {
        this.colname = colname;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getSpanSize() {
        return spanSize;
    }

    public void setSpanSize(int spanSize) {
        this.spanSize = spanSize;
    }

    public boolean isLock() {
        return isLock;
    }

    public void setLock(boolean lock) {
        isLock = lock;
    }
}
