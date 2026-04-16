package com.example.formularios.;

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
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    public static final String dataUserCache = "datauser";
    private static final int modoPrivado = Context.MODE_PRIVATE;
    SharedPreferences sh;
    SharedPreferences.Editor editor;
    EditText nombre;
    EditText edad;
    Spinner spinnerCategoria;

    AppCompatButton btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        validar();
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        nombre=findViewById(R.id.nombre);
        edad=findViewById(R.id.edad);
        spinnerCategoria=findViewById(R.id.spinnerCategoria);
        btnGuardar=findViewById(R.id.btnGuardar);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.categorias,
                android.R.layout.simple_spinner_item
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategoria.setAdapter(adapter);

        sh = getSharedPreferences(dataUserCache, modoPrivado);
        editor = sh.edit();
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usr = nombre.getText().toString();
                int e=Integer.parseInt(edad.getText().toString());
                String categoria = spinnerCategoria.getSelectedItem().toString();

                if(usr.isEmpty() || categoria.equals("Selecciona una categoría")){
                    Toast.makeText(MainActivity.this,"Completa todos los campos correctamente",Toast.LENGTH_LONG).show();
                }else{
                    editor.putString("User", usr);
                    editor.putString("Categoria", categoria);
                    editor.putInt("edad", edad);
                    editor.commit();

                    Intent intent = new Intent(MainActivity.this, MainActivity_Pantalla_Principal.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }

    public void validar(){
        String usuario = this.getSharedPreferences(dataUserCache,modoPrivado).getString("User","0");
        if(!usuario.equalsIgnoreCase("0")){
            Intent intent = new Intent(MainActivity.this, Pantalla2.class);
            startActivity(intent);
            finish();
        }
    }




}