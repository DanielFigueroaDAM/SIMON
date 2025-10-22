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
        val myViewModel = MyViewModel()

        Scaffold(modifier = Modifier,containerColor = Color.Black) { innerPadding ->
            Greeting(
                myViewModel,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }

    @Composable
    fun Greeting(myViewModel: MyViewModel, modifier: Modifier = Modifier) {
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
                    Boton(myViewModel, Colores.CLASE_ROJO)
                    Boton(myViewModel,Colores.CLASE_VERDE)
                }
                Column {
                    Boton(myViewModel,Colores.CLASE_AZUL)
                    Boton(myViewModel,Colores.CLASE_AMARILLO)

                }

            }
            Button(
                colors = ButtonDefaults.buttonColors(Color.Blue),
                onClick = { myViewModel.crearRandom() }
                ) {
                Text(text = "juego", fontSize = 20.sp)
            }

        }


    }

@Composable
fun Boton(myViewModel: MyViewModel, colores: Colores){
    Button(
        colors = ButtonDefaults.buttonColors(colores.color),
        onClick = { myViewModel.comprobarOrdinalColor(colores)},
        modifier = Modifier
            .size(height = 100.dp, width = 160.dp )
            .padding(15.dp)
            ) {
                Text(text = colores.txt, fontSize = 20.sp, color = Color.Black)
            }

}

