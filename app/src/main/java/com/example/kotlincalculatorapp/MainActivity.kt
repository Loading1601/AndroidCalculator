package com.example.kotlincalculatorapp

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.kotlincalculatorapp.ui.theme.KotlinCalculatorAppTheme

class MainActivity : ComponentActivity() {
    //private lateinit var bindingWorkingTV : ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun numberAction(view: View) {}
    fun operationAction(view: View) {}
    fun allClearAction(view: View) {
        val workingsTV = findViewById<TextView>(R.id.workingsTV)
        workingsTV.text = ""
    }
    fun backSpaceAction(view: View) {}
    fun equalsAction(view: View) {}
}

