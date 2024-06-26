package com.ruben.exament2
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
class MainActivity_02 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity02)

        val priceInput: EditText = findViewById(R.id.textPrecio)
        val brandSpinner: Spinner = findViewById(R.id.cbTvs)
        val sizeSpinner: Spinner = findViewById(R.id.cbPulgadas)
        val calculateButton: Button = findViewById(R.id.btnCalcular)
        val clearButton: Button = findViewById(R.id.btnLimpiar)
        val resultTextView: TextView = findViewById(R.id.textResultado)

        val dolares = 3.80
        val igv = 0.18

        calculateButton.setOnClickListener {
            val precio = priceInput.text.toString().toDoubleOrNull()
            if (precio == null) {
                Toast.makeText(this, "Ingrese un precio válido", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

        clearButton.setOnClickListener {
            limpiarCampos()
        }

            val tele = brandSpinner.selectedItem.toString()
            val pulgadas = sizeSpinner.selectedItem.toString()

            val descuento = when (tele) {
                "Samsung" -> if (pulgadas== "19") 0.12 else 0.13
                "Panasonic" -> if (pulgadas== "19") 0.14 else 0.12
                "Lg" -> if (pulgadas == "19") 0.12 else 0.14
                "Sony" -> if (pulgadas == "19") 0.13 else 0.15
                else -> 0.0
            }

            val descuentoPrecio = precio * (1-descuento)
            val precioSoles = descuentoPrecio * dolares
            val precioIgv = precioSoles * (1+igv)

            val result = """
                Precio con descuento en USD: $descuentoPrecio
                Precio en soles sin IGV: $precioSoles
                Precio en soles con IGV: $precioIgv
            """.trimIndent()

            resultTextView.text = result
        }
    }
    private fun limpiarCampos() {
        val textPrecio: EditText = findViewById(R.id.textPrecio)
        val resultado: TextView = findViewById(R.id.textResultado)
        textPrecio.text.clear()
        resultado.text = ""
    }

}