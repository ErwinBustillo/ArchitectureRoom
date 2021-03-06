package com.uninorte.roomdata.architectureroom;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uninorte.roomdata.architectureroom.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by erwin on 3/4/2018.
 */

public class UserRecyclerViewAdapter extends RecyclerView.Adapter<UserRecyclerViewAdapter.ViewHolder>{

    private List<User> data;

    public UserRecyclerViewAdapter(List<User> data){
        this.data=data;
    }

    public UserRecyclerViewAdapter(){

    }

    @Override
    public UserRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_user,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.textoId.setText(data.get(position).getUid()+"");
        holder.textoNombre.setText(data.get(position).getFirstName()+"");
        holder.textoApellido.setText(data.get(position).getLastName()+"");
        holder.textoCity.setText(data.get(position).getAddress().getCity());
        ArrayList<String> books =data.get(position).getBooks();
        String libros = "";
        for(String text:books){
            libros = libros + ","+text;
        }
        holder.textoBooks.setText(libros);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<User> data){this.data=data;}

    public class ViewHolder extends RecyclerView.ViewHolder{
       TextView textoId;
       TextView textoNombre;
       TextView textoApellido;
       TextView textoCity;
       TextView textoBooks;


       public ViewHolder(View itemView){
           super(itemView);
           textoId = itemView.findViewById(R.id.textoId);
           textoNombre= itemView.findViewById(R.id.textoNombre);
           textoApellido = itemView.findViewById(R.id.textoApellido);
           textoCity = itemView.findViewById(R.id.textoCity);
           textoBooks = itemView.findViewById(R.id.textoBooks);
       }
    }
}
