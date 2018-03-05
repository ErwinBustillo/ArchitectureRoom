package com.uninorte.roomdata.architectureroom.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.uninorte.roomdata.architectureroom.AppDatabase;
import com.uninorte.roomdata.architectureroom.entity.User;

import java.util.List;

/**
 * Created by erwin on 3/4/2018.
 */

public class UserViewModel extends AndroidViewModel {
    private AppDatabase appDatabase;
    private LiveData<List<User>> data;

    public UserViewModel(@NonNull Application application) {
        super(application);

        appDatabase=AppDatabase.getInstance(this.getApplication());

        data= appDatabase.userDao().getAll();
    }

    public LiveData<List<User>> getUsers(){return data;}


}
