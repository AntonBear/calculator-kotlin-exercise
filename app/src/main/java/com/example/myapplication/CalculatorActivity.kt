package com.example.myapplication

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding

class CalculatorActivity : AppCompatActivity() {

    private val viewModel: CalculatorViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Связываю LiveData с UI
        viewModel.displayText.observe(this) { newDisplayText ->
            binding.displayTextView.text = newDisplayText
        }

        binding.numbersGroup.referencedIds.forEach {
            findViewById<TextView>(it)?.let { textView ->
                textView.setOnClickListener { viewModel.appendDigit(textView.text.toString()) }
            }
        }

        binding.buttonAc.setOnClickListener { viewModel.clear() }
        binding.buttonDot.setOnClickListener { viewModel.appendDigit(".") }
        binding.buttonMultiply.setOnClickListener {
            viewModel.handleOperationButton(Operation.MULTIPLY)
        }

        binding.buttonDivide.setOnClickListener {
            viewModel.handleOperationButton(Operation.DIVIDE)
        }

        binding.buttonPlus.setOnClickListener {
            viewModel.handleOperationButton(Operation.PLUS)
        }

        binding.buttonMinus.setOnClickListener {
            viewModel.handleOperationButton(Operation.MINUS)
        }

        binding.buttonPercent.setOnClickListener {
            viewModel.handleOperationButton(Operation.PERCENT)
        }

        binding.buttonPlusMinus.setOnClickListener {
            viewModel.toggleSign()
        }

        binding.buttonEqual.setOnClickListener {
            viewModel.handleEquals()
        }
    }
}