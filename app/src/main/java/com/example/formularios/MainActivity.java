package com.example.formularios;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class MainActivity extends AppCompatActivity {

    public static final String dataUserCache = "datauser";
    private static final int modoPrivado = Context.MODE_PRIVATE;

    SharedPreferences sh;
    SharedPreferences.Editor editor;

    EditText nombre, edad;
    Spinner spinnerCategoria;
    AppCompatButton btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        nombre = findViewById(R.id.nombre);
        edad = findViewById(R.id.edad);
        spinnerCategoria = findViewById(R.id.spinnerCategoria);
        btnGuardar = findViewById(R.id.btnGuardar);

        String[] arraycategorias = {"Musica", "Deportes", "Cine"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,android.R.layout.simple_spinner_item,arraycategorias
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategoria.setAdapter(adapter);

        sh = getSharedPreferences(dataUserCache, modoPrivado);
        editor = sh.edit();

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String usr = nombre.getText().toString();
                String edadTexto = edad.getText().toString();
                String categoria = spinnerCategoria.getSelectedItem().toString();

                if(usr.isEmpty() || edadTexto.isEmpty() || categoria.equals("Selecciona una categoría")){
                    Toast.makeText(MainActivity.this,"Completa todos los campos correctamente",Toast.LENGTH_LONG).show();
                }else{

                    int e = Integer.parseInt(edadTexto);

                    editor.putString("nombre", usr);
                    editor.putInt("edad", e);
                    editor.putString("categoria", categoria);
                    editor.commit();

                    redirigirSegunCategoria(categoria);
                }
            }
        });

        validar();
    }

    public void validar(){
        String categoria = this.getSharedPreferences(dataUserCache,modoPrivado).getString("categoria","");

        if(!categoria.equals("")){
            redirigirSegunCategoria(categoria);
        }
    }

    public void redirigirSegunCategoria(String categoria){

        Intent intent;

        if(categoria.equals("Deportes")){
            intent = new Intent(MainActivity.this, DeportesActivity.class);
        }else if(categoria.equals("Música")){
            intent = new Intent(MainActivity.this, MusicaActivity.class);
        }else{
            intent = new Intent(MainActivity.this, CineActivity.class);
        }

        startActivity(intent);
        finish();
    }
}