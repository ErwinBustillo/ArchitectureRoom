package com.uninorte.roomdata.architectureroom;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.uninorte.roomdata.architectureroom.dao.UserDao;
import com.uninorte.roomdata.architectureroom.entity.User;

/**
 * Created by erwin on 3/4/2018.
 */

@Database(entities = {User.class}, version=1)
public abstract class AppDatabase extends RoomDatabase{

    public abstract UserDao userDao();

    private static  AppDatabase INSTANCE;

    public static AppDatabase getInstance(final Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,"demo").build();
        }
        return INSTANCE;
    }
}
