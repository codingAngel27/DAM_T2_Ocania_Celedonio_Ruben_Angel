package com.ruben.exament2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity

class MainActivity_03 : ComponentActivity() {
    private lateinit var numeroHijos: EditText
    private lateinit var resultado: TextView
    private lateinit var rgEstadoCivil: RadioGroup
    private lateinit var buttonCalcular: Button
    private lateinit var buttonLimpiar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity_03)

        numeroHijos = findViewById(R.id.textNumeroHijos)
        resultado = findViewById(R.id.textResultado)
        rgEstadoCivil = findViewById(R.id.rgEstadoCivil)
        buttonCalcular = findViewById(R.id.btnCalcular)
        buttonLimpiar = findViewById(R.id.btnLimpiar)

        buttonCalcular.setOnClickListener {
            calcularSubsidioFinal()
        }
        buttonLimpiar.setOnClickListener {
            limpiarCampos()
        }
    }

    private fun calcularSubsidioFinal() {
        val hijosStr = numeroHijos.text.toString()
        if (hijosStr.isEmpty()) {
            resultado.text = ""
            return
        }

        val hijos = hijosStr.toInt()

        val estadoCivil = when (rgEstadoCivil.checkedRadioButtonId) {
            R.id.rbViuda -> "viuda"
            R.id.rbCasada -> "casada"
            R.id.rbSoltera -> "soltera"
            else -> {
                resultado.text = ""
                return
            }
        }
        val montoSubsidio = calcularSubsidio(hijos, estadoCivil)
        resultado.text = "El monto del subsidio es: S/. $montoSubsidio"
    }

    private fun calcularSubsidio(hijos: Int, estadoCivil: String): Double {
        val montoBase = when {
            hijos <= 3 -> 75.0 * hijos
            hijos in 4..6 -> 110.0 * hijos
            hijos > 6 -> 120.0 * hijos
            else -> 0.0
        }

        val montoAdicional = when (estadoCivil) {
            "viuda" -> 55.0
            "casada" -> 25.0
            "soltera" -> 10.0 * hijos
            else -> 0.0
        }

        return montoBase + montoAdicional
    }
    private fun limpiarCampos(){
        numeroHijos.setText("")
        rgEstadoCivil.clearCheck()
        resultado.text = ""
    }
}
