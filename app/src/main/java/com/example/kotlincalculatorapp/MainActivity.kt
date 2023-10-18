package com.example.kotlincalculatorapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity

/*
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.kotlincalculatorapp.databinding.ActivityMainBinding
import com.example.kotlincalculatorapp.ui.theme.KotlinCalculatorAppTheme
*/
class MainActivity : ComponentActivity() {
    //Initiating reference variables
    private lateinit var workingsTV : TextView
    private lateinit var resultsTV : TextView


    var canAddOperation = false
    var canAddDecimal = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //Referencing my TextViews:
        workingsTV = findViewById<TextView>(R.id.workingsTV)
        resultsTV = findViewById<TextView>(R.id.resultsTV)



    }

    fun numberAction(view: View) {
        if (view is Button){
            if(view.text == "."){
                if(canAddDecimal) workingsTV.append(view.text)
                canAddDecimal = false
            }
            else workingsTV.append(view.text)

            canAddOperation = true

        }
    }


    fun operationAction(view: View) {
        if(view is Button && canAddOperation){
            workingsTV.append(view.text)
            canAddOperation = false
            canAddDecimal = true
        }
    }
    fun allClearAction(view: View) {
        workingsTV.text = ""
        resultsTV.text = ""
    }
    fun backSpaceAction(view: View) {
        val length = workingsTV.text.length
        if (length > 0) {
            workingsTV.text = workingsTV.text.substring(0, length - 1)
        }
    }
    fun equalsAction(view: View) {
        resultsTV.text = calculate()
    }
    private fun calculate() : String{
        var digitsAndOperators = digitsAndOperators()
        if(digitsAndOperators.isEmpty()) return("")

        var timesAndDivision = timesAndDivision(digitsAndOperators)
        if(timesAndDivision.isEmpty()) return("")


        val result = addSubtrackt(timesAndDivision)


        return(result.toString())
    }

    private fun addSubtrackt(parameterList: MutableList<Any>): Float {
        var result = parameterList[0] as Float
        for(x in parameterList.indices){
            if(parameterList[x] is Char && x != parameterList.lastIndex){
                val  operator = parameterList[x]
                val next = parameterList[x+1] as Float

                if(operator == '+') result += next
                if(operator == '-') result -= next

            }
        }
        return result
    }

    private fun digitsAndOperators () : MutableList<Any>{
        var currentDigit = ""
        var list = mutableListOf<Any>()

        for(char in workingsTV.text){

            if(char.isDigit() || char == '.') currentDigit += char
            else {
                //Adding to list
                list.add(currentDigit.toFloat())
                list.add(char)

                //Reset variable
                currentDigit = ""
            }
        }

        if(currentDigit != "") list.add(currentDigit.toFloat())
        return list
    }

    private fun timesAndDivision(parameterList: MutableList<Any>): MutableList<Any>{
        var returnList = mutableListOf<Any>()
        var restart = parameterList.size


        for(x in parameterList.indices){
            if(parameterList[x] is Char && x != parameterList.lastIndex && x < restart){

                var operator = parameterList[x]

                var last = parameterList[x-1] as Float
                var next = parameterList[x+1] as Float

                when(operator){
                    'x' ->{
                        returnList.add(last * next)
                        restart = x + 1
                    }

                    '/' ->{
                        returnList.add(last / next)
                        restart = x + 1
                    }

                    else ->{
                        returnList.add(last)
                        returnList.add(operator)
                    }
                }
            }

            if(x > restart) returnList.add(parameterList)
        }
        
        return returnList
    }


}

