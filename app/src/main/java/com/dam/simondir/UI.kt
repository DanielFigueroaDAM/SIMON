package com.dam.simondir

import android.R
import android.text.style.BackgroundColorSpan
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Preview(showBackground = true)
    @Composable
    fun ViewAll() {

        Scaffold(modifier = Modifier,containerColor = Color.Black) { innerPadding ->
            Greeting(
                modifier = Modifier.padding(innerPadding)
            )
        }
    }

    @Composable
    fun Greeting(modifier: Modifier = Modifier) {
        Text(text = "Contador = ",
            fontSize = 20.sp,
            modifier = Modifier
                .padding(10.dp),
            color = Color.Blue,
        )
        Column(modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
            ) {

            Row {
                Column {
                    Boton(Colores.CLASE_ROJO.color, Colores.CLASE_ROJO.txt)
                    Boton(Colores.CLASE_VERDE.color, Colores.CLASE_VERDE.txt)
                }


                Column {
                    Boton(Colores.CLASE_AZUL.color, Colores.CLASE_AZUL.txt)
                    Boton(Colores.CLASE_AMARILLO.color, Colores.CLASE_AMARILLO.txt)

                }

            }
            Button(
                colors = ButtonDefaults.buttonColors(Color.Blue),
                onClick = { Log.d("Juego","Click!")},
                ) {
                Text(text = "juego", fontSize = 20.sp)
            }

        }


    }

@Composable
fun Boton(color: Color, text: String){
    Button(
        colors = ButtonDefaults.buttonColors(color),
        onClick = { Log.d("Juego","Click!")},
        modifier = Modifier
            .size(height = 100.dp, width = 160.dp )
            .padding(15.dp)
            ) {
                Text(text = text, fontSize = 20.sp)
            }

}
