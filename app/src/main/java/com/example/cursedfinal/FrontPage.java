package com.example.cursedfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.cursedfinal.models.Doing;
import com.example.cursedfinal.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FrontPage extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference myRef;
    private List<String> DisorTasks;

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> listData;
    private List<Doing> listTemp;
    private DatabaseReference db;
    final private String USER_KEY = "Doings";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_page);
        init();
        getDataFromDB();
        setOnClickItem();
    }
    private void init(){
        listView = findViewById(R.id.listView);
        listData = new ArrayList<>();
        listTemp = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        listView.setAdapter(adapter);
        db = FirebaseDatabase.getInstance().getReference(USER_KEY);
    }

    private void getDataFromDB(){
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!listData.isEmpty()) listData.clear();
                if (!listTemp.isEmpty()) listTemp.clear();

                for (DataSnapshot child: dataSnapshot.getChildren()) {
                    Doing doing = child.getValue(Doing.class);
                    if (doing != null) {
                        listData.add(doing.name);
                        listTemp.add(doing);
                    }
                }

                updateUI();
             }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setOnClickItem() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Doing doing = listTemp.get(position);
                Intent i = new Intent(FrontPage.this, ShowActivity.class);
                i.putExtra("show_theme", doing.theme);
                i.putExtra("show_desc", doing.desc);
                startActivity(i);
            }
        });
    }

    private void updateUI() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_list_item_1, listData);

        listView.setAdapter(adapter);
    }
}
