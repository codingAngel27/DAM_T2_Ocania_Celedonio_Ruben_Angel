package com.ruben.exament2

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
class MainActivity_01 : ComponentActivity() {
    val tarifas = mapOf(
        "Polo" to 0.50,
        "Camisa" to 1.00,
        "Pantalon" to 1.50
    )

    val bonificaciones = mapOf(
        "A" to 250.00,
        "B" to 150.00,
        "C" to 100.00,
        "D" to 50.00
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity01)

        val spinner: Spinner = findViewById(R.id.comboBoxTp)
        val calcularButton: Button = findViewById(R.id.btnProcesar)
        val limpiarButton: Button = findViewById(R.id.btnLimpiar)

        val items = arrayOf("Polo", "Camisa", "Pantalon")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        calcularButton.setOnClickListener {
            calcularSueldo()
        }

        limpiarButton.setOnClickListener {
            limpiarCampos()
        }
    }

    private fun calcularSueldo() {
        val spinner: Spinner = findViewById(R.id.comboBoxTp)
        val cantidad: EditText = findViewById(R.id.textCantidad)
        val categoria: EditText = findViewById(R.id.textCategoria)
        val resultado: TextView = findViewById(R.id.textResultado)

        val prenda = spinner.selectedItem.toString()
        val cantidad2 = cantidad.text.toString().toIntOrNull() ?: 0
        val tarifa = tarifas[prenda] ?: 0.0
        val categoria2 = categoria.text.toString().uppercase()
        val bonificacion = if (cantidad2 > 700) {
            bonificaciones[categoria2] ?: 0.0
        } else {
            0.0
        }

        val sueldoBase = cantidad2 * tarifa
        val ingresosTotales = sueldoBase + bonificacion
        val impuesto = ingresosTotales * 0.09
        val seguro = ingresosTotales * 0.02
        val solidaridad = ingresosTotales * 0.01
        val descuentosTotales = impuesto + seguro + solidaridad
        val sueldoNeto = ingresosTotales - descuentosTotales

        val resultadoFinal = """
            Sueldo Base: $sueldoBase
            Bonificación: $bonificacion
            Impuesto (9%): $impuesto
            Seguro (2%): $seguro
            Solidaridad (1%): $solidaridad
            Sueldo Neto: $sueldoNeto
        """.trimIndent()

        resultado.text = resultadoFinal
    }

    private fun limpiarCampos() {
        val cantidad: EditText = findViewById(R.id.textCantidad)
        val categoria: EditText = findViewById(R.id.textCategoria)
        val resultado: TextView = findViewById(R.id.textResultado)

        cantidad.text.clear()
        categoria.text.clear()
        resultado.text = ""
    }
}