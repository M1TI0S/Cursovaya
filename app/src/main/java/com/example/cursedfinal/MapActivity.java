package com.example.cursedfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cursedfinal.models.Doing;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MapActivity extends AppCompatActivity {

    Button addNewDoings_btn, showDoings_btn;

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.make_application);

        setTitle("Добавление записи");

        EditText user_name = findViewById(R.id.userName);
        EditText theme = findViewById(R.id.theme_of_do);
        EditText desc = findViewById(R.id.doings);
        addNewDoings_btn = findViewById(R.id.addNewDoingButton);
        showDoings_btn = findViewById(R.id.showDoings);

        addNewDoings_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Doing doing = new Doing();

                doing.name = user_name.getText().toString();
                doing.theme = theme.getText().toString();
                doing.desc = desc.getText().toString();

                user_name.setText("");
                theme.setText("");
                desc.setText("");

                database.child("Doings").push().setValue(doing);

                Toast.makeText(MapActivity.this,"Новая заметка успешно добавлена!",Toast.LENGTH_LONG).show();
            }
        });
        showDoings_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MapActivity.this, FrontPage.class);
                startActivity(intent);

            }
        });

    }
}
