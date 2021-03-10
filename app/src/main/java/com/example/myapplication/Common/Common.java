package com.example.myapplication.Common;

import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;

public class Common {
    public static String APi_KEY = "7a813ca0143764ae0a6ca13aedcbcddf";
    public static String API_LINK = "http://api.openweathermap.org/data/2.5/weather";

    public static String apiRequest (String lat, String lng){
        @stringBuilder sb = new SrtingBuilder(API_LINK);
        sb.append(String.format("?lat=%s&lon=%s&APPID=%s&units=metric",lat,lng,API_KEY));
        return sb.toString();

    }

    public static String unixTimeStampToDateTime(double unixTimeStamp){

        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        date.setTime((long)unixTimeStamp*1000);
        return dateFormat.format(date);
    }

    public static String getImage(String icon){
        return String.format("http://openweathermap.org/img/w/%s.png",icon);
    }

    public static String getDateNow(){
        DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy HH:mm");
        Date date = new Date();
        return dateFormat.format(date);
    }

}
