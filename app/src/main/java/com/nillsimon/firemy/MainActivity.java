package com.nillsimon.firemy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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



        initFragment();
        //updateList();
       // changeProgress();

    }

    private void authFragment() {
        AuthFragment fragment = new AuthFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.placeholder, fragment);
        fragmentTransaction.commit();
    }

    private void setFields() {
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("users");
        addUser();
        result = new ArrayList<>();
        recyclerView = findViewById(R.id.listRecyclerView);
        recyclerView.setHasFixedSize(false);
       // LinearLayoutManager llm = new LinearLayoutManager(this);
       // llm.setOrientation(LinearLayoutManager.VERTICAL);
       // recyclerView.setLayoutManager(llm);
        adapter = new UserAdapter(result);
        recyclerView.setAdapter(adapter);
    }

    private void initFragment() {
        ListFragment fragment = new ListFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.placeholder, fragment);
        fragmentTransaction.commit();

    }


    public void updateList () {
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


        private void addUser () {

            String id = reference.push().getKey();

            User newUser = new User("https://newyogalife.com/images/Photos/Asani/Anantasana.jpg",
                    "Анантасана",
                    "Поза на боку  с поднятой ногой",
                    "Аnantasana",
                    "Ананта ",
                    "Ананта — это имя Вишну и одновременно змея Шеши, который служит местом отдохновения Бога . В индийских мифах Вишну спит в первобытном океане на своем ложе — тысячеглавном змее Шеши.",
                    37);

            Map<String, Object> userValues = newUser.toMap();

            Map<String, Object> user = new HashMap<>();

            user.put(id, userValues);

            reference.updateChildren(user);

        }
        private void changeProgress () {
            if (result.size() == 0) {
                recyclerView.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.VISIBLE);

            } else {
                recyclerView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            getMenuInflater().inflate(R.menu.menu, menu);
            return true;
        }
        @Override
        public boolean onOptionsItemSelected (MenuItem item){
            switch (item.getItemId()) {
                case R.id.settings:
                    startActivity(new Intent(MainActivity.this,
                            AuthActivity.class));
                    Toast toast = Toast.makeText(this, "Изменить настройки Запущен AuthАктивити через интент", Toast.LENGTH_LONG);
                    toast.show();
                    break;
                case R.id.auth:
                   authFragment();
                   // startActivity(new Intent(MainActivity.this, AuthActivity.class));
                    Toast toast1 = Toast.makeText(this, "Пройти регистрацию", Toast.LENGTH_SHORT);
                    toast1.show();
                    break;
                case R.id.favorite:
                    startActivity(new Intent(MainActivity.this,
                            AuthFragment.class));
                    Toast toast2 = Toast.makeText(this, "Добавить в избранное Запущен AuthFragment через интент", Toast.LENGTH_LONG);
                    toast2.show();
                    break;
                case R.id.add:
                    addUser();
                    Toast toast3 = Toast.makeText(this, "Добавить новую асану", Toast.LENGTH_LONG);
                    toast3.show();
                    break;
                case R.id.fragmentMenu:
                    initFragment();
                    Toast toast4 = Toast.makeText(this, "Переход во фрагмент", Toast.LENGTH_LONG);
                    toast4.show();
                    break;
                default:
            }
            return super.onOptionsItemSelected(item);


        }

}
