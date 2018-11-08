package com.mydeerlet.home.bean;

public class UpdateModel {


    /**
     * versionCode : 3
     * versionName : 3.0
     * presentation : 区块链资讯汇总，实时行情更新，励志打造一站式综合APP应用！ 升级提示： 1.此次更新新增积分板块，用户可以通过签到，阅读，评论等功能赚取积分，积分可以用于之后积分商城礼品兑换； 2.此次更新新增视频板块，用户可以在视频板块浏览到各种类型视频，视频涵盖科普类，访谈类，搞笑类等。
     * url : http://btc789.com/app-release.apk
     */

    private int versionCode;
    private String versionName;
    private String presentation;
    private String url;

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getPresentation() {
        return presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "UpdateModel{" +
                "versionCode='" + versionCode + '\'' +
                ", versionName='" + versionName + '\'' +
                ", presentation='" + presentation + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}

