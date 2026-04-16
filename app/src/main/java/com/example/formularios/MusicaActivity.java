package com.example.formularios;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MusicaActivity extends AppCompatActivity {

    Button btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_musica);

        btnVolver = findViewById(R.id.btnVolver);

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences preferences = getSharedPreferences(MainActivity.dataUserCache, MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();

                editor.clear();
                editor.commit();

                Intent intent = new Intent(MusicaActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }
}