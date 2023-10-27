package com.example.xeiomicalculator2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewTreeObserver
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.Toast
import com.example.xeiomicalculator2.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        onNumberClicked()

        onOperatorClicked()


    }

    private fun onOperatorClicked() {

        binding.btnTaqsim.setOnClickListener {
            if (binding.txtExpression.text.isNotEmpty()) {
               val myChar =  binding.txtExpression.text.last()
                if (myChar != '+' && myChar != '*' && myChar != '/' && myChar != '-' ) {
                    appendText("/")
                }
            }
        }
        binding.btnZarb.setOnClickListener {
            if (binding.txtExpression.text.isNotEmpty()) {
                val myChar =  binding.txtExpression.text.last()
                if (myChar != '+' && myChar != '*' && myChar != '/' && myChar != '-' ) {
                    appendText("*")
                }
            }
        }
        binding.btnJam.setOnClickListener {
            if (binding.txtExpression.text.isNotEmpty()) {
                val myChar =  binding.txtExpression.text.last()
                if (myChar != '+' && myChar != '*' && myChar != '/' && myChar != '-' ) {
                    appendText("+")
                }
            }
        }
        binding.btnTafriq.setOnClickListener {
            if (binding.txtExpression.text.isNotEmpty()) {
                val myChar =  binding.txtExpression.text.last()
                if (myChar != '+' && myChar != '*' && myChar != '/' && myChar != '-' ) {
                    appendText("-")
                }
            }
        }
        binding.btnParantesBaz.setOnClickListener {
            appendText("(")
        }
        binding.btnParantesBasta.setOnClickListener {
            appendText(")")
        }
        binding.btnPakidan.setOnClickListener {

            val oldText = binding.txtExpression.text.toString()

            if (oldText.isNotEmpty()) {
                binding.txtExpression.text = oldText.substring(0, oldText.length - 1)
            }
        }

        binding.btnAC.setOnClickListener {
            binding.txtExpression.text = ""
            binding.txtJavab.text = ""
        }

    }

    private fun onNumberClicked() {

        binding.btn0.setOnClickListener {

            if (binding.txtExpression.text.isNotEmpty()) {
                appendText("0")
            } else {
                appendText("0.")
            }
        }

        binding.btn1.setOnClickListener {
            appendText("1")
        }

        binding.btn2.setOnClickListener {
            appendText("2")
        }

        binding.btn3.setOnClickListener {
            appendText("3")
        }
        binding.btn4.setOnClickListener {
            appendText("4")
        }
        binding.btn5.setOnClickListener {
            appendText("5")
        }

        binding.btn6.setOnClickListener {
            appendText("6")
        }
        binding.btn7.setOnClickListener {
            appendText("7")
        }
        binding.btn8.setOnClickListener {
            appendText("8")
        }
        binding.btn9.setOnClickListener {
            appendText("9")
        }
        binding.btnDot.setOnClickListener {
                if ( binding.txtExpression.text.isEmpty() || binding.txtJavab.text.isNotEmpty() ) {
                    appendText("0.")
                } else if (!binding.txtExpression.text.contains(".")) {
                    appendText(".")
                }
        }

        binding.btnMasavi.setOnClickListener {
            try {


                val result = binding.txtExpression.text.toString()
                val expression = ExpressionBuilder(result).build()
                val doubleResult = expression.evaluate()
                val longResult = doubleResult.toLong()
                if (doubleResult == longResult.toDouble()) {
                    binding.txtJavab.text = longResult.toString()
                } else {
                    binding.txtJavab.text = doubleResult.toString()
                }
            }
            catch (e: Exception){
                binding.txtExpression.text = ""
                binding.txtJavab.text = ""
                Toast.makeText(this, "wrong input!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun appendText(newText: String) {

        if (binding.txtJavab.text.isNotEmpty()) {
            binding.txtExpression.text = ""
        }
        binding.txtJavab.text = ""

        binding.txtExpression.append(newText)


        val viewTree: ViewTreeObserver = binding.scrollTextExpression.viewTreeObserver
        viewTree.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                binding.scrollTextExpression.viewTreeObserver.addOnGlobalLayoutListener(this)
                binding.scrollTextExpression.scrollTo(binding.txtExpression.width, 0)
            }

        })


    }
}