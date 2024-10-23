package com.example.taller3

import android.app.AlertDialog
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.BaseColumns
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class Inicio : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GreetingPreview() //mostramos la pantalla de inicio haciendo uso de un preview
        }
    }
}

@Composable
fun lectorNombre(context: Context) {
    val nombre = remember { mutableStateOf("") }
    val nombresBase = remember { mutableStateOf("") }
    val sharedPreferences = remember {
        context.getSharedPreferences("UserData", Context.MODE_PRIVATE)
    }
    val savedColor = sharedPreferences.getInt("backgroundColor", android.graphics.Color.WHITE)
    val backgroundColor = remember { mutableStateOf(getComposeColor(savedColor)) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor.value),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = nombre.value,
            onValueChange = { nombre.value = it },
            label = { Text("Nombre") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para guardar en SQLite
        Button(onClick = {
            guardarEnSQLite(context, nombre.value)
        }) {
            Text("Guardar en SQLite")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para cargar desde SQLite
        Button(onClick = {
            cargarDeSQLite(context, nombresBase)

        }) {
            Text("Cargar desde SQLite")
        }

        Spacer(modifier = Modifier.height(16.dp))

        //val nombreGuardado = remember { sharedPreferences.getString("nombre", "") ?: "" }
        Text("Bienvenido ${nombresBase.value}")

        Spacer(modifier = Modifier.height(16.dp))

        BotonConfiguracion()
        Spacer(modifier = Modifier.height(16.dp))
    }
}

fun guardarEnSQLite(context: Context, nombre: String) {
    val dbHelper = SQLDatos(context)
    val db = dbHelper.writableDatabase

    val values = ContentValues().apply {
        put(SQLInicio.UserEntry.COLUMN_NAME_NOMBRE, nombre)
    }

    db?.insert(SQLInicio.UserEntry.TABLE_NAME, null, values)
    Toast.makeText(context, "Guardado en SQLite", Toast.LENGTH_SHORT).show()
}

fun cargarDeSQLite(context: Context, nombresState: MutableState<String>) {
    val dbHelper = SQLDatos(context)
    val db = dbHelper.readableDatabase

    val projection = arrayOf(BaseColumns._ID, SQLInicio.UserEntry.COLUMN_NAME_NOMBRE)

    val cursor = db.query(
        SQLInicio.UserEntry.TABLE_NAME,
        projection,
        null,
        null,
        null,
        null,
        null
    )

    val nombresList = mutableListOf<String>()

    with(cursor) {
        while (moveToNext()) {
            val nombre = getString(getColumnIndexOrThrow(SQLInicio.UserEntry.COLUMN_NAME_NOMBRE))
            nombresList.add(nombre)
        }
    }
    cursor.close()

    nombresState.value = nombresList.joinToString(separator = "\n Bienvenido ")
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val context = LocalContext.current
    lectorNombre(context)
    //llamamos a la funcion para que nos de un preview de la pantalla
}

@Composable
fun BotonConfiguracion() {
    Spacer(modifier = Modifier.height(16.dp))

    val context = LocalContext.current
    Button(onClick = {
        context.startActivity(
            Intent(context, Config::class.java)
        )
    }) {
        Text("CONFIGURACION")
    }

    //creamos un boton que nos llevara a la pantalla de configuracion
}
