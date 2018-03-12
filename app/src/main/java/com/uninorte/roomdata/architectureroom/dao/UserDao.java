package com.uninorte.roomdata.architectureroom.dao;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.uninorte.roomdata.architectureroom.entity.User;

import java.util.List;

@Dao
public interface UserDao {
    @Query("Select * from user")
    LiveData<List<User>> getAll();

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insert(User user);

    @Delete
    void delete(User user);

}
