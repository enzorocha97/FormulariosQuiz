package com.example.formularios;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class DeportesActivity extends AppCompatActivity {

    Button btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_deportes);

        btnVolver = findViewById(R.id.btnVolver);

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DeportesActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }
}