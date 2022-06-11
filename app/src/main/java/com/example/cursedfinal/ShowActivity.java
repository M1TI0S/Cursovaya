package com.example.cursedfinal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ShowActivity extends AppCompatActivity {
    private TextView theme, desc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_layout);
        init();
        getIntentMain();
    }

    private void init() {
        theme = findViewById(R.id.show_theme);
        desc = findViewById(R.id.show_desc);
    }

    private void getIntentMain() {
        Intent i = getIntent();
        if(i != null) {
            theme.setText(i.getStringExtra("show_theme"));
            desc.setText(i.getStringExtra("show_desc"));
        }
    }
}
