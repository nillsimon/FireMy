package com.nillsimon.firemy;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<UserModel> result;
    private UserAdapter adapter;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private ProgressBar progressBar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        recyclerView = view.findViewById(R.id.listRecyclerView);
        progressBar = view.findViewById(R.id.progressBar);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("users");
        result = new ArrayList<>();
        recyclerView.setHasFixedSize(false);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        adapter = new UserAdapter(result);
        recyclerView.setAdapter(adapter);
        updateList();

        return view;
    }

    private void updateList() {
            reference.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    result.add(dataSnapshot.getValue(UserModel.class));
                    adapter.notifyDataSetChanged();

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
                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

    private void changeProgress() {
         if (result.size() == 0) {
                recyclerView.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.VISIBLE);

            } else {
                recyclerView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.INVISIBLE);
            }
        }


    private int getItemIndex (UserModel user){
            int index = -1;
            for (int i = 0; i < result.size(); i++) {
                if (result.get(i).key.equals(user.key)) {
                    index = 1;
                    break;
                }
            }
            return index;
        }
    }



