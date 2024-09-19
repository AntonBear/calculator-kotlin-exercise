package com.example.myapplication

import android.os.Bundle
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

        binding.buttonNumber0.setOnClickListener { viewModel.appendDigit("0") }
        binding.buttonNumber1.setOnClickListener { viewModel.appendDigit("1") }
        binding.buttonNumber2.setOnClickListener { viewModel.appendDigit("2") }
        binding.buttonNumber3.setOnClickListener { viewModel.appendDigit("3") }
        binding.buttonNumber4.setOnClickListener { viewModel.appendDigit("4") }
        binding.buttonNumber5.setOnClickListener { viewModel.appendDigit("5") }
        binding.buttonNumber6.setOnClickListener { viewModel.appendDigit("6") }
        binding.buttonNumber7.setOnClickListener { viewModel.appendDigit("7") }
        binding.buttonNumber8.setOnClickListener { viewModel.appendDigit("8") }
        binding.buttonNumber9.setOnClickListener { viewModel.appendDigit("9") }
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