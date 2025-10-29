package com.dam.simondir

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel


@Preview(showBackground = true)
    @Composable
    fun ViewAll() {
        val myViewModel: MyViewModel = viewModel()

        Scaffold(modifier = Modifier,containerColor = Color.White) { innerPadding ->
            Greeting(
                myViewModel,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }



    @Composable
    fun Greeting(myViewModel: MyViewModel, modifier: Modifier = Modifier) {
        val newCounter by Datos.contadorAciertos.collectAsState()
        val _activo by myViewModel.estadoConStateFlow.collectAsState()
        val numberMax by Datos.numeroMax.collectAsState()
        val pista by myViewModel.pista.collectAsState()




        Text(text = "Contador = $newCounter",
            fontSize = 20.sp,
            modifier = Modifier
                .padding(10.dp).fillMaxSize().padding(top = 100.dp),
            color = Color.Blue,
            textAlign = TextAlign.Center
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
                enabled = _activo.start_activo,
                colors = ButtonDefaults.buttonColors(Color.Blue),
                onClick = {
                    myViewModel.resetContador()
                    myViewModel.crearRandom()
                    myViewModel.pista()
                }
                ) {
                Text(text = "juego", fontSize = 20.sp)
            }

        }
        Text(text = "Maxima racha = ${numberMax}",
            fontSize = 20.sp,
            modifier = Modifier
                .padding(10.dp).fillMaxSize().padding(top = 700.dp),
            color = Color.Blue,
            textAlign = TextAlign.Center
        )
        Text(text = pista,
            fontSize = 20.sp,
            modifier = Modifier
                .padding(10.dp).fillMaxSize().padding(top = 600.dp),
            color = Color.Blue,
            textAlign = TextAlign.Center
        )


    }

@Composable
fun Boton(myViewModel: MyViewModel, colores: Colores) {
    val _activo by myViewModel.estadoConStateFlow.collectAsState()

    Button(
        enabled = _activo.boton_activo,
        colors = ButtonDefaults.buttonColors(
            containerColor = colores.color,                          // color normal
            disabledContainerColor = colores.color.copy(alpha = 0.5f), // menos apagado (0.8 en vez de 0.38)
            contentColor = Color.Black,
            disabledContentColor = Color.Black.copy(alpha = 0.9f)      // texto casi igual de visible
        ),
        onClick = {
            myViewModel.comprobarOrdinalColor(colores)
            myViewModel.pista()
                  },
        modifier = Modifier
            .size(height = 100.dp, width = 160.dp)
            .padding(15.dp)
    ) {
        Text(
            text = colores.txt,
            fontSize = 20.sp,
            color = Color.Black
        )
    }
}


