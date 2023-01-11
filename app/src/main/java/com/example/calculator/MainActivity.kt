package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    private var tvInput: TextView? = null
    var lastNum: Boolean= false
    var lastDot: Boolean= false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.tvInput)
    }

    fun onDigit(view: View) {
        tvInput?.append((view as Button).text)  //when view is a button i want a text which is on it
        lastNum = true
        lastDot = false
    }

    fun onClR(view: View){
        tvInput?.text=""
    }

    fun onDot(view: View){
        if (lastNum && !lastDot){
            tvInput?.append(".")
            lastDot = true
            lastNum = false
        }
    }

    fun onEqual(view: View){
        if(lastNum){
            var tvValue = tvInput?.text.toString()
            var prefix = ""

            try {
                if(tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }

                if (tvValue.contains("-")){
                    val splitValue = tvValue.split("-")
                    var one = splitValue[0] //99 (99-1 therefore 99 will be one string and 1 will be another)
                    var two = splitValue[1] //1

                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    var result = one.toDouble() - two.toDouble()
                    tvInput?.text = removeZeroAfterDot(result.toString())

                }

                else if (tvValue.contains("+")){
                    val splitValue = tvValue.split("+")
                    var one = splitValue[0] //99 (99-1 therefore 99 will be one string and 1 will be another)
                    var two = splitValue[1] //1

                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    var result = one.toDouble() + two.toDouble()
                    tvInput?.text = removeZeroAfterDot(result.toString())

                }

                else if (tvValue.contains("×")){
                    val splitValue = tvValue.split("×")
                    var one = splitValue[0] //99 (99-1 therefore 99 will be one string and 1 will be another)
                    var two = splitValue[1] //1

                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    var result = one.toDouble() * two.toDouble()
                    tvInput?.text = removeZeroAfterDot(result.toString())

                }

                else if (tvValue.contains("÷")){
                    val splitValue = tvValue.split("÷")
                    var one = splitValue[0] //99 (99-1 therefore 99 will be one string and 1 will be another)
                    var two = splitValue[1] //1

                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    var result = one.toDouble() / two.toDouble()
                    tvInput?.text = removeZeroAfterDot(result.toString())
                }



            }catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    fun onOperator(view: View){
        tvInput?.text?.let{
            if (lastNum && !isOperatorAdding(it.toString())){
                tvInput?.append((view as Button).text)
                lastNum = false
                lastDot = false
            }
        }
    }

    private fun removeZeroAfterDot(result: String): String{
        var value = result
        if (result.contains(".0"))
            value = result.substring(0, result.length-2)   //99.0 =

        return value
    }

    private fun isOperatorAdding(value: String): Boolean{
        return if(value.startsWith("-")){
            false
        }else{
            value.contains(("÷"))||value.contains("+")||value.contains("-")||value.contains("×")
        }
    }
}
