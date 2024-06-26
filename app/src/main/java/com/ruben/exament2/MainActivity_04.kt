package com.ruben.exament2

import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.ComponentActivity

class MainActivity_04 : ComponentActivity() {

    private lateinit var cbOrigen: Spinner
    private lateinit var cbDestino: Spinner
    private lateinit var rgServicio: RadioButton
    private lateinit var rgPersona: RadioButton
    private lateinit var buttonCalcular: Button
    private lateinit var textResultado: TextView
    private lateinit var buttonLimpiar: Button

    private val tarifas = mapOf(
        "Ica (A)" to 100,
        "Arequipa (B)" to 450,
        "Chimbote (C)" to 300,
        "Trujillo (D)" to 350
    )

    private val incrementos = mapOf(
        "Ica (A)" to 0.0,
        "Arequipa (B)" to 0.25,
        "Chimbote (C)" to 0.35,
        "Trujillo (D)" to 0.45
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity_04)


        textResultado = findViewById(R.id.textResultado)
        cbOrigen = findViewById(R.id.cbOrigen)
        cbDestino = findViewById(R.id.cbDestino)
        rgServicio = findViewById(R.id.rbEmpresas)
        rgPersona = findViewById(R.id.rbPersona)
        buttonCalcular = findViewById(R.id.btnCalcular)
        buttonLimpiar = findViewById(R.id.btnLimpiar)

        buttonCalcular.setOnClickListener {
            calcularDestiono()
        }
        buttonLimpiar.setOnClickListener {
            limpiarCampos()
        }
    }

    private fun calcularDestiono() {

        val origen = cbOrigen.selectedItem.toString()
        val destino = cbDestino.selectedItem.toString()
        val esPersonaNatural = rgPersona.isChecked

        val tarifaBase = tarifas[destino] ?: 0
        val incremento = incrementos[destino] ?: 0.0

        var tarifaFinal = tarifaBase + (tarifaBase * incremento)
        if (esPersonaNatural) {
            tarifaFinal += tarifaFinal * 0.05
        }

        val tarifaEnDolares = tarifaFinal / 3.80

        textResultado.text = "Tarifa en Soles: S/ ${String.format("%.2f", tarifaFinal)}\n" +
                "Tarifa en Dólares: $ ${String.format("%.2f", tarifaEnDolares)}"
    }

    private fun limpiarCampos() {
        cbOrigen.setSelection(0)
        cbDestino.setSelection(0)
        rgServicio.isChecked = true
        textResultado.text = ""
    }
}

