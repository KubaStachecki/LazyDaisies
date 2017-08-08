package com.example.kuba_10.firebasewallpapertest.Model;

/**
 * Created by Kuba-10 on 21.07.2017.
 */



import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Staff implements Parcelable {

    @SerializedName("imie")
    @Expose
    private String imie;
    @SerializedName("obrazek")
    @Expose
    private String obrazek;
    @SerializedName("opis")
    @Expose
    private String opis;
    @SerializedName("zapowiedz")
    @Expose
    private String zapowiedz;

    public Staff() {
    }

    protected Staff(Parcel in) {
        imie = in.readString();
        obrazek = in.readString();
        opis = in.readString();
        zapowiedz = in.readString();
    }

    public static final Creator<Staff> CREATOR = new Creator<Staff>() {
        @Override
        public Staff createFromParcel(Parcel in) {
            return new Staff(in);
        }

        @Override
        public Staff[] newArray(int size) {
            return new Staff[size];
        }
    };

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getObrazek() {
        return obrazek;
    }

    public void setObrazek(String obrazek) {
        this.obrazek = obrazek;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getZapowiedz() {
        return zapowiedz;
    }

    public void setZapowiedz(String zapowiedz) {
        this.zapowiedz = zapowiedz;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(imie);
        parcel.writeString(obrazek);
        parcel.writeString(opis);
        parcel.writeString(zapowiedz);
    }
}



