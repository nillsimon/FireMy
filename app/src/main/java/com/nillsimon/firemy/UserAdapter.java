package com.nillsimon.firemy;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> implements View.OnClickListener {

    public Button addedAsana;
    private List<UserModel> list;

    public UserAdapter(List<UserModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final UserViewHolder holder, int position) {

        UserModel user = list.get(position);

        holder.text_name.setText(user.firstName);
        holder.nameTwo.setText(user.nameTwo + " " + user.nameTwo);
        holder.age.setText(user.age + "");
        holder.text_job.setText(user.job);
        holder.nameTwo.setText(user.nameTwo);
        holder.DescFirst.setText(user.DescFirst);
        Picasso.get().load(list.get(position).getImage()).into((holder.image));
        Picasso.get().load(list.get(position).getImage()).into((holder.yogaIconGrand));

        holder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                menu.add(holder.getAdapterPosition(),0, 0, " Удалить");
                menu.add(holder.getAdapterPosition(),1, 0, " Изменить");
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View v) {
        addedAsana.setOnClickListener(this);
    }

    class UserViewHolder extends RecyclerView.ViewHolder{

        TextView  text_name, text_job, nameTwo, DescFirst, age, key;
        ImageView image, yogaIconGrand;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            yogaIconGrand = itemView.findViewById(R.id.yogaIconGrand);
            text_name = itemView.findViewById(R.id.asanaRu);
            text_job = itemView.findViewById(R.id.asanaEn);
            DescFirst = itemView.findViewById(R.id.DescFirst);
            nameTwo = itemView.findViewById(R.id.nameTwo);
            age = itemView.findViewById(R.id.publish);
        }
    }
}
