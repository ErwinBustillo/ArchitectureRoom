package com.uninorte.roomdata.architectureroom;


import android.app.Application;

public class BasicApp extends Application{

    public AppDatabase getDatabase(){
        return AppDatabase.getInstance(this);
    }
}
