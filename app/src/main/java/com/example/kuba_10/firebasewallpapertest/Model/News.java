package com.example.kuba_10.firebasewallpapertest.Model;

/**
 * Created by Kuba-10 on 21.07.2017.
 */



import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class News implements Parcelable {


    @SerializedName("ang content")
    @Expose
    private String angContent;
    @SerializedName("ang title")
    @Expose
    private String angTitle;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("url")
    @Expose
    private String url;


    public News() {
    }

    protected News(Parcel in) {
        angContent = in.readString();
        angTitle = in.readString();
        content = in.readString();
        title = in.readString();
        url = in.readString();
    }

    public static final Creator<News> CREATOR = new Creator<News>() {
        @Override
        public News createFromParcel(Parcel in) {
            return new News(in);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };

    public String getangContent() {
        return angContent;
    }

    public void setangContent(String angContent) {
        this.angContent = angContent;
    }

    public String getangTitle() {
        return angTitle;
    }

    public void setangTitle(String angTitle) {
        this.angTitle = angTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(angContent);
        parcel.writeString(angTitle);
        parcel.writeString(content);
        parcel.writeString(title);
        parcel.writeString(url);
    }
}


