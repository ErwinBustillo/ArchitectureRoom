package com.uninorte.roomdata.architectureroom;

import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.TextView;

import com.uninorte.roomdata.architectureroom.entity.Address;
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

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(createHelperCallback()); // lo invocamos
        itemTouchHelper.attachToRecyclerView(rv); // se lo agregamos al recycler view

        model= ViewModelProviders.of(this).get(UserViewModel.class);

        model.getUsers().observe(this,users->{
            data = users;
            tv.setText(data.size()+"");
            adapter.setData(data);
            adapter.notifyDataSetChanged();
        });
    }

    private ItemTouchHelper.Callback createHelperCallback(){
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }


            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position= viewHolder.getAdapterPosition();

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this); // alert for confirm to delete
                builder.setMessage("ARE YOU SURE TO DELETE ?");

                builder.setPositiveButton("REMOVE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        model.deleteUser(data.get(position));
                        return;
                    }
                }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        adapter.notifyItemRemoved(position+1);
                        adapter.notifyItemRangeChanged(position,adapter.getItemCount());
                        return;
                    }
                }).show(); // show alert dialog
            }
        };
        return simpleItemTouchCallback;
    }


    public void onClickAdd(View view) {
        model.addUser(new User(data.size(),"Erwin", "Bustillo", new Address("Barranquilla","Los Andes")));
    }

    public void onClickEdit(View view) {
        User user = data.get(0);
        ArrayList<String> books = user.getBooks();
        books.add("the book"+books.size());
        user.setBooks(books);

        user.setFirstName(user.getFirstName()+"changed");
        model.addUser(user);
    }
}
