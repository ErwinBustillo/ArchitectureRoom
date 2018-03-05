package com.uninorte.roomdata.architectureroom;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.uninorte.roomdata.architectureroom.entity.User;
import com.uninorte.roomdata.architectureroom.viewmodel.UserViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv;
    private UserRecyclerViewAdapter adapter;
    private List<User> data;

    private UserViewModel model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = findViewById(R.id.textoNumber);
        rv=findViewById(R.id.recyclerView);
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new UserRecyclerViewAdapter(new ArrayList<User>());
        rv.setAdapter(adapter);

        model= ViewModelProviders.of(this).get(UserViewModel.class);

        model.getUsers().observe(this,users->{
            data = users;
            tv.setText(data.size()+"");
            adapter.setData(data);
            adapter.notifyDataSetChanged();
        });
    }

    public void onClickAdd(View view) {

    }
}
