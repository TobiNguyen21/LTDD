package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rcv;
    private Adapter userAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rcv = findViewById(R.id.rcv);
        userAdapter = new Adapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcv.setLayoutManager(linearLayoutManager);
        userAdapter.setData(getListUser());
        rcv.setAdapter(userAdapter);
    }
    private List<User> getListUser(){
        List<User> list = new ArrayList<>();
        list.add(new User(R.drawable.avatar,"User name 1"));
        list.add(new User(R.drawable.avatar,"User name 2"));
        list.add(new User(R.drawable.avatar,"User name 1"));
        list.add(new User(R.drawable.avatar,"User name 2"));
        list.add(new User(R.drawable.avatar,"User name 1"));
        list.add(new User(R.drawable.avatar,"User name 2"));
        list.add(new User(R.drawable.avatar,"User name 1"));
        list.add(new User(R.drawable.avatar,"User name 2"));
        list.add(new User(R.drawable.avatar,"User name 1"));
        list.add(new User(R.drawable.avatar,"User name 2"));
        list.add(new User(R.drawable.avatar,"User name 1"));
        list.add(new User(R.drawable.avatar,"User name 2"));
        list.add(new User(R.drawable.avatar,"User name 1"));
        list.add(new User(R.drawable.avatar,"User name 2"));
        list.add(new User(R.drawable.avatar,"User name 1"));
        list.add(new User(R.drawable.avatar,"User name 2"));
        return list;
    }
}