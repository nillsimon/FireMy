package com.nillsimon.firemy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.telecom.TelecomManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<UserModel> result;
    private UserAdapter adapter;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("users");

        result = new ArrayList<>();

        recyclerView = findViewById(R.id.userList);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(llm);

        adapter = new UserAdapter(result);
        recyclerView.setAdapter(adapter);

        updateList();
        changeProgress();

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                removeUser(item.getItemId());
                break;
            case 1:
                changeUser(item.getItemId());
                break;
        }

        return super.onContextItemSelected(item);
    }
    public void updateList() {
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                result.add(dataSnapshot.getValue(UserModel.class));
                adapter.notifyDataSetChanged();
                changeProgress();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                UserModel model = dataSnapshot.getValue(UserModel.class);
                int index = getItemIndex(model);
                result.set(index, model);
                adapter.notifyItemChanged(index);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                UserModel model = dataSnapshot.getValue(UserModel.class);
                int index = getItemIndex(model);
                result.remove(index);
                adapter.notifyItemRemoved(index);
                changeProgress();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private int getItemIndex(UserModel user) {
        int index = -1;
        for (int i = 0; i < result.size(); i++) {
            if (result.get(i).key.equals(user.key)) {
                index = 1;
                break;
            }
        }
            return index;
    }

    private void removeUser(int position){
        reference.child(result.get(position).key).removeValue();
    }
    private void changeUser(int position){
        UserModel user = result.get(position);
        user.age = 200;

        Map<String, Object> userValues = user.toMap();
        Map<String, Object> newUser = new HashMap<>();

        newUser.put(user.key, userValues);

        reference.updateChildren(newUser);

    }
    private void changeProgress(){
        if(result.size() == 0){
            recyclerView.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.VISIBLE);

        }else {
            recyclerView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.INVISIBLE);
        }
    }
}
