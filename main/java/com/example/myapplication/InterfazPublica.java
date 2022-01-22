package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class InterfazPublica extends AppCompatActivity {

    EditText editTextNombre, editTextDescripcion, editTextNumber ;
    Button buttonCancelar, buttonRegistrar;
    Spinner spinnerTipoMascota;
    String tipoMascota = "";
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interfaz_publica);

        editTextNombre = (EditText) findViewById(R.id.editTextNombre);
        editTextDescripcion = (EditText) findViewById(R.id.editTextDescripcion);
        editTextNumber = (EditText) findViewById(R.id.editTextNumber);

        buttonCancelar = (Button) findViewById(R.id.buttonCancelar);
        buttonRegistrar = (Button) findViewById(R.id.buttonRegistrar);

        spinnerTipoMascota = (Spinner) findViewById(R.id.spinnerTipoMascota);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.combo_tipo_mascotas,
                android.R.layout.simple_spinner_item);
        spinnerTipoMascota.setAdapter(adapter);


        spinnerTipoMascota.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tipoMascota = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        buttonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre, descripcion, edad;
                nombre = editTextNombre.getText().toString();
                descripcion = editTextDescripcion.getText().toString();
                edad = editTextNumber.getText().toString();

                if(nombre.isEmpty() || descripcion.isEmpty() || edad.isEmpty() || tipoMascota.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Complete todos los campos", Toast.LENGTH_LONG).show();
                }else{
                    if(tipoMascota.equals("Seleccione tipo de mascota")){
                        Toast.makeText(getApplicationContext(), "Seleccione un tipo de mascota", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Map<String, Object> mascota = new HashMap<>();
                        mascota.put("idTipoMascota", 1);
                        mascota.put("nombre", nombre);
                        mascota.put("descripcion", descripcion);
                        mascota.put("edad", edad);
                        mascota.put("tipoMascota", tipoMascota);

                        db.collection("mascotas").add(mascota);
                        Toast.makeText(getApplicationContext(), "Datos guardados con éxito", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
/*
        buttonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent miIntent = new Intent(InterfazPublica.this,MainActivity.class);
                startActivity(miIntent);
            }
        });*/
    }

    public void onClick(View view) {
        Intent miIntent = new Intent(InterfazPublica.this,MainActivity.class);
        startActivity(miIntent);
    }
}
