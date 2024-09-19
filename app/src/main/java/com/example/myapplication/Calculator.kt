package com.example.myapplication

class Calculator {
    var currentValue = 0.0
    var pendingOperation: Operation? = null

    private var bufferedValue = 0.0

    fun evaluate() {
        when (pendingOperation) {
            Operation.PLUS -> bufferedValue += currentValue
            Operation.MINUS -> bufferedValue -= currentValue
            Operation.MULTIPLY -> bufferedValue *= currentValue
            Operation.DIVIDE -> bufferedValue /= currentValue
            Operation.PERCENT -> {}

            null -> bufferedValue = currentValue
        }

        pendingOperation = null
        currentValue = bufferedValue
    }

    fun handlePercent() {
        when (pendingOperation) {
            Operation.PLUS, Operation.MINUS -> {
                currentValue *= bufferedValue / 100
            }
            Operation.MULTIPLY, Operation.DIVIDE, null -> {
                currentValue /= 100
            }
            Operation.PERCENT -> {}
        }
    }

    fun toggleSign() {
        currentValue = -currentValue
    }

    fun clear() {
        currentValue = 0.0
        bufferedValue = 0.0
        pendingOperation = null
    }
}