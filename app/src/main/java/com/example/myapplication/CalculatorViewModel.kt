package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.DecimalFormat

class CalculatorViewModel: ViewModel() {

    private val _displayText = MutableLiveData<String>() // Создаем LiveData

    val displayText: LiveData<String> = _displayText // Создаем публичный LiveData для доступа в Activity

    private val calculator = Calculator()

    private val decimalFormat = DecimalFormat().apply {
        minimumFractionDigits = 0
        isGroupingUsed = false
    }

    private var isWaitingUserInput = true

    init {
        clear()
    }

    fun handleOperationButton(operation: Operation) {
        setCalculatorCurrentValue()
        if (operation == Operation.PERCENT) {
            calculator.handlePercent()
        } else {
            calculator.evaluate()
            calculator.pendingOperation = operation
            isWaitingUserInput = true
        }

        updateDisplayText()
    }

    fun appendDigit(digit: String) {
        if (isWaitingUserInput) {
            _displayText.value = ""
            isWaitingUserInput = false
        }

        val shouldAppendDot: Boolean by lazy {
            _displayText.value.let {
                !it.isNullOrEmpty() && !it.contains(".")
            }
        }

        if (digit == "." && !shouldAppendDot) {
            return
        }

        _displayText.value += digit
    }

    fun clear() {
        isWaitingUserInput = true
        calculator.clear()
        updateDisplayText()
    }

    fun toggleSign() {
        setCalculatorCurrentValue()
        calculator.toggleSign()
        updateDisplayText()
    }

    fun handleEquals() {
        setCalculatorCurrentValue()
        calculator.evaluate()
        updateDisplayText() // Обновляем текст после вычисления
    }

    private fun updateDisplayText() {
        val formattedText = decimalFormat.format(calculator.currentValue)
        _displayText.value = formattedText // Устанавливаем значение LiveData
    }

    private fun setCalculatorCurrentValue() {
        calculator.currentValue = _displayText.value?.let(decimalFormat::parse)?.toDouble() ?: 0.0
    }
}