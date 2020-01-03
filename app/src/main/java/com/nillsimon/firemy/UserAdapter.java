package com.nillsimon.firemy;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>  {


    private List<UserModel> list;

    public UserAdapter(List<UserModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_ltem, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final UserViewHolder holder, int position) {

        //((ListAdapter.ListViewHolder)holder).bindView(position);
        UserModel user = list.get(position);

        holder.nameFirst.setText(user.nameFirst);
        holder.nameTwo.setText(user.nameTwo);
        holder.age.setText(user.age + "");
        holder.hotText.setText(user.hotText);
        holder.descFirst.setText(user.descFirst);
        holder.descTwo.setText(user.descTwo);
        Picasso.get().load(list.get(position).getImageFirst()).into((holder.imageFirst));
        holder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {


            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class UserViewHolder extends RecyclerView.ViewHolder{

        TextView  nameFirst, nameTwo, hotText, descFirst, descTwo, age, key;
        ImageView imageFirst;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            imageFirst = itemView.findViewById(R.id.imageFirst);
            nameFirst = itemView.findViewById(R.id.nameFirst);
            nameTwo = itemView.findViewById(R.id.nameTwo);
            hotText = itemView.findViewById(R.id.hotText);
            descFirst = itemView.findViewById(R.id.descFirst);
            descTwo = itemView.findViewById(R.id.descTwo);
            age = itemView.findViewById(R.id.publish);
        }
    }


}
