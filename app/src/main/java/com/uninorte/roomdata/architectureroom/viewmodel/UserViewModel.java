package com.uninorte.roomdata.architectureroom.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.uninorte.roomdata.architectureroom.AppDatabase;
import com.uninorte.roomdata.architectureroom.entity.User;

import java.util.List;

public class UserViewModel extends AndroidViewModel {
    private AppDatabase appDatabase;
    private LiveData<List<User>> data;

    public UserViewModel(@NonNull Application application) {
        super(application);

        appDatabase=AppDatabase.getInstance(this.getApplication());

        data= appDatabase.userDao().getAll();
    }

    public LiveData<List<User>> getUsers(){return data;}

    public void addUser(User user){

        new AddItemTask().execute(user); // la ejecuta
    }

    public void deleteUser(User user){

        new DeleteItemTask().execute(user); // la ejecuta
    }

    // tarea asyncrona que escribe en el dao e inserta el usuario
    private class AddItemTask extends AsyncTask<User, Void, Void>{

        @Override
        protected Void doInBackground(User... item) {
            appDatabase.userDao().insert(item[0]);
            return null;
        }
    }

    //eliminar
    private class DeleteItemTask extends AsyncTask<User, Void, Void>{

        @Override
        protected Void doInBackground(User... item) {
            appDatabase.userDao().delete(item[0]);
            return null;
        }
    }


}
