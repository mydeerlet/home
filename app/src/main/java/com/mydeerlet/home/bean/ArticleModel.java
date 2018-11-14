package com.mydeerlet.home.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class ArticleModel implements Parcelable {

    private String id;
    private String catid;
    private String catname;
    private String title;
    private String thumb;
    private String keywords;
    private String description;
    private String content;
    private String url;
    private String username;
    private String nickname;
    private String time;
    private String date;
    private String imgkey;
    private String newsId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCatid() {
        return catid;
    }

    public void setCatid(String catid) {
        this.catid = catid;
    }

    public String getCatname() {
        return catname;
    }

    public void setCatname(String catname) {
        this.catname = catname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImgkey() {
        return imgkey;
    }

    public void setImgkey(String imgkey) {
        this.imgkey = imgkey;
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    @Override
    public String toString() {
        return "ArticleModel{" +
                "id='" + id + '\'' +
                ", catid='" + catid + '\'' +
                ", catname='" + catname + '\'' +
                ", title='" + title + '\'' +
                ", thumb='" + thumb + '\'' +
                ", keywords='" + keywords + '\'' +
                ", description='" + description + '\'' +
                ", content='" + content + '\'' +
                ", url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                ", time='" + time + '\'' +
                ", date='" + date + '\'' +
                ", imgkey='" + imgkey + '\'' +
                ", newsId='" + newsId + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.catid);
        dest.writeString(this.catname);
        dest.writeString(this.title);
        dest.writeString(this.thumb);
        dest.writeString(this.keywords);
        dest.writeString(this.description);
        dest.writeString(this.content);
        dest.writeString(this.url);
        dest.writeString(this.username);
        dest.writeString(this.nickname);
        dest.writeString(this.time);
        dest.writeString(this.date);
        dest.writeString(this.imgkey);
        dest.writeString(this.newsId);
    }

    public ArticleModel() {
    }

    protected ArticleModel(Parcel in) {
        this.id = in.readString();
        this.catid = in.readString();
        this.catname = in.readString();
        this.title = in.readString();
        this.thumb = in.readString();
        this.keywords = in.readString();
        this.description = in.readString();
        this.content = in.readString();
        this.url = in.readString();
        this.username = in.readString();
        this.nickname = in.readString();
        this.date = in.readString();
        this.time = in.readString();
        this.imgkey = in.readString();
        this.newsId = in.readString();
    }

    public static final Creator<ArticleModel> CREATOR = new Creator<ArticleModel>() {
        @Override
        public ArticleModel createFromParcel(Parcel source) {
            return new ArticleModel(source);
        }

        @Override
        public ArticleModel[] newArray(int size) {
            return new ArticleModel[size];
        }
    };
}
