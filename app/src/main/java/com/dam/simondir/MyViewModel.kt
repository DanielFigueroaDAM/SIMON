package com.dam.simondir

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel: ViewModel() {
    private val TAG_LOG = "miDebug"

    // estados del juego
    // usamos LiveData para que la IU se actualice
    // patron de diseño observer
    val estadoLiveData: MutableLiveData<Estados> = MutableLiveData(Estados.INICIO)

    // este va a ser nuestra lista para la secuencia random
    // usamos mutable, ya que la queremos modificar
    var _numbers = mutableStateOf(0)

    init {
        // estado inicial
        Log.d(TAG_LOG, "Inicializamos ViewModel - Estado: ${estadoLiveData.value}")
    }

    /**
     * crear entero random
     */
    fun crearRandom() {
        // cambiamos estado, por lo tanto la IU se actualiza
        estadoLiveData.value = Estados.GENERANDO
        _numbers.value = (0..3).random()
        Log.d(TAG_LOG, "creamos random ${_numbers.value} - Estado: ${estadoLiveData.value}")
        actualizarNumero(_numbers.value)
    }
    fun actualizarNumero(numero: Int) {
        Log.d(TAG_LOG, "actualizamos numero en Datos - Estado: ${estadoLiveData.value}")
        Datos.numero = numero
        // cambiamos estado, por lo tanto la IU se actualiza
        estadoLiveData.value = Estados.ADIVINANDO
    }
    fun actualizarEstado(estado: Estados) {
        Log.d(TAG_LOG, "actualizamos estado - Estado: ${estadoLiveData.value}")
        estadoLiveData.value = estado
    }
    fun comprobarOrdinalColor(colorP: Colores){
        if(comprobarColor(colorP) == Datos.numero){
            Log.d(TAG_LOG, "Muy bien")
            Datos.contadorAciertos++
            comprobarNumero()
            crearRandom()
        }
        else {
            Log.d(TAG_LOG, "Mal")
            Datos.contadorAciertos = 0
            crearRandom()
        }
    }

    private fun comprobarNumero() {
        if(Datos.contadorAciertos == 3){
            // mostrar un panel de victoria
            actualizarEstado(Estados.INICIO)
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
}