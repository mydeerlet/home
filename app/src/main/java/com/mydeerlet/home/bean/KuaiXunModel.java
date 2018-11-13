package com.mydeerlet.home.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class KuaiXunModel implements Parcelable {


    /**
     * id : 30973
     * title : 欧盟委员会副主席：欧盟将重点关注数字货币资产分类和监管计划
     * content : <p>据cointelegraph报道，欧盟委员会副主席Valdis Dombrovskis最近表示，欧盟(EU)将重点关注数字货币资产分类和监管计划。根据Dombrovskis的说法，尽管最近出现了动荡，但数字货币资产仍然存在 ，数字货币市场仍继续增长。</p>

     * date : 2018-09-11
     * time : 14:05
     */

    private String id;
    private String title;
    private String content;
    private String date;
    private String time;
    private String bull;
    private String bear;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getBull() {
        return bull;
    }

    public void setBull(String bull) {
        this.bull = bull;
    }

    public String getBear() {
        return bear;
    }

    public void setBear(String bear) {
        this.bear = bear;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeString(this.content);
        dest.writeString(this.date);
        dest.writeString(this.time);
        dest.writeString(this.bull);
        dest.writeString(this.bear);
    }

    public KuaiXunModel() {
    }

    protected KuaiXunModel(Parcel in) {
        this.id = in.readString();
        this.title = in.readString();
        this.content = in.readString();
        this.date = in.readString();
        this.time = in.readString();
        this.bull = in.readString();
        this.bear = in.readString();
    }

    public static final Creator<KuaiXunModel> CREATOR = new Creator<KuaiXunModel>() {
        @Override
        public KuaiXunModel createFromParcel(Parcel source) {
            return new KuaiXunModel(source);
        }

        @Override
        public KuaiXunModel[] newArray(int size) {
            return new KuaiXunModel[size];
        }
    };
}
