package com.example.ptsganjil2021reza23;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class SportsModel extends RealmObject {

    public String getStrSport() {
        return strSport;
    }

    public void setStrSport(String strSport) {
        this.strSport = strSport;
    }

    public String getStrFormat() {
        return strFormat;
    }

    public void setStrFormat(String strFormat) {
        this.strFormat = strFormat;
    }

    public String getStrSportThumb() {
        return strSportThumb;
    }

    public void setStrSportThumb(String strSportThumb) {
        this.strSportThumb = strSportThumb;
    }

    public String getStrSportsDescription() {
        return strSportsDescription;
    }

    public void setStrSportsDescription(String strSportsDescription) {
        this.strSportsDescription = strSportsDescription;
    }

    @PrimaryKey
    private String strSport;
    private String strFormat;
    private String strSportThumb;
    private String strSportsDescription;

    public SportsModel() {
    }

    SportsModel(String strSport, String strFormat, String strSportThumb, String strSportDescription){
        this.strSport = strSport;
        this.strFormat = strFormat;
        this.strSportThumb = strSportThumb;
        this.strSportsDescription = strSportDescription;
    }


}
