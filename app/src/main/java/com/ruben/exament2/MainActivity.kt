package com.ruben.exament2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ruben.exament2.ui.theme.ExamenT2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_principal)

        val button1: Button = findViewById(R.id.btn01)
        button1.setOnClickListener {
            val intent = Intent(this, MainActivity_01::class.java)
            startActivity(intent)
        }

        val button2: Button = findViewById(R.id.btn02)
        button2.setOnClickListener {
            val intent = Intent(this, MainActivity_02::class.java)
            startActivity(intent)
        }

        val button3: Button = findViewById(R.id.btn03)
        button3.setOnClickListener {
            val intent = Intent(this, MainActivity_03::class.java)
            startActivity(intent)
        }
        val button4: Button = findViewById(R.id.btn04)
        button4.setOnClickListener {
            val intent = Intent(this, MainActivity_04::class.java)
            startActivity(intent)
        }
    }
}