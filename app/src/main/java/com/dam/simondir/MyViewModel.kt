package com.dam.simondir

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class MyViewModel: ViewModel() {
    private val TAG_LOG = "miDebug"

    // estados del juego
    // usamos LiveData para que la IU se actualice
    // patron de diseño observer
    val estadoConStateFlow = MutableStateFlow(Estados.INICIO)


    val pista = MutableStateFlow("")


    // este va a ser nuestra lista para la secuencia random
    // usamos mutable, ya que la queremos modificar
    var _numbers = mutableStateOf(0)

    init {
        // estado inicial
        Log.d(TAG_LOG, "Inicializamos ViewModel - Estado: ${estadoConStateFlow.value}")
    }

    /**
     * crear entero random
     */
    fun crearRandom() {
        // cambiamos estado, por lo tanto la IU se actualiza
        estadoConStateFlow.value = Estados.GENERANDO
        _numbers.value = (0..3).random()
        Log.d(TAG_LOG, "creamos random ${_numbers.value} - Estado: ${estadoConStateFlow.value}")
        actualizarNumero(_numbers.value)
    }
    fun actualizarNumero(numero: Int) {
        Log.d(TAG_LOG, "actualizamos numero en Datos - Estado: ${estadoConStateFlow.value}")
        Datos.numero = numero
        // cambiamos estado, por lo tanto la IU se actualiza
        actualizarEstado(Estados.ADIVINANDO)
    }
    fun actualizarEstado(estado: Estados) {
        Log.d(TAG_LOG, "actualizamos estado - Estado: ${estadoConStateFlow.value}")

        estadoConStateFlow.value = estado
    }
    fun comprobarOrdinalColor(colorP: Colores){
        if(comprobarColor(colorP) == Datos.numero){
            Log.d(TAG_LOG, "Muy bien")
            Datos.contadorAciertos.value = Datos.contadorAciertos.value + 1
            if(Datos.contadorAciertos.value > Datos.numeroMax.value){
                Datos.numeroMax.value = Datos.contadorAciertos.value
            }
            comprobarNumero()


        }
        else {
            Log.d(TAG_LOG, "Mal")
            Datos.contadorAciertos.value = 0
            crearRandom()
            estadoConStateFlow.value = Estados.INICIO
        }
    }

    private fun comprobarNumero() {
        if(Datos.contadorAciertos.value == 3){
            // mostrar un panel de victoria
            actualizarEstado(Estados.INICIO)
        }
        crearRandom()


    }
    public fun pista(){
        if(Datos.numero == 0 || Datos.numero == 3){
            pista.value = "Termina en o"
        }else{
            pista.value = "No termina en o"
        }
    }

    fun comprobarColor(colorP: Colores):Int{
        return when (colorP) {
            Colores.CLASE_ROJO -> 0
            Colores.CLASE_VERDE -> 1
            Colores.CLASE_AZUL -> 2
            Colores.CLASE_AMARILLO -> 3
        }
    }
    fun resetContador(){
        Datos.contadorAciertos.value = 0
        actualizarEstado(Estados.INICIO)
    }
}