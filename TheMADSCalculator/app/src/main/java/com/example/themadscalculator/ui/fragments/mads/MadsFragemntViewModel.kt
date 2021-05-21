package com.example.themadscalculator.ui.fragments.mads

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.themadscalculator.model.History

class MadsFragemntViewModel() : ViewModel() {

    var operationText: MutableLiveData<String> = MutableLiveData<String>()
    var resultText: MutableLiveData<String> = MutableLiveData<String>()
    var numbers: ArrayList<Int> = ArrayList<Int>()
    var oprations: ArrayList<String> = ArrayList<String>()
    var oprand: String = ""
    var opratoin: String = ""
    var historyList: ArrayList<History> = ArrayList()
    var numbersHistory: ArrayList<Int> = ArrayList<Int>()
    var oprationsHistory: ArrayList<String> = ArrayList<String>()


    fun onButtonClick(label: String) {
        when (label) {
            "*" -> {
                if (oprand.length > 0) {
                    numbers.add(oprand.toInt())
                } else {
                    opratoin = "0"
                    numbers.add(0)
                }
                oprations.add("*")
                oprand = ""
            }
            "+" -> {
                if (oprand.length > 0) {
                    numbers.add(oprand.toInt())
                } else {
//                    opratoin="0"
                    numbers.add(0)
                }
                oprations.add("+")
                oprand = ""
            }
            "/" -> {
                if (oprand.length > 0) {
                    numbers.add(oprand.toInt())
                } else {
                    opratoin = "0"
                    numbers.add(0)
                }
                oprations.add("/")
                oprand = ""
            }
            "-" -> {
                if (oprand.length > 0) {
                    numbers.add(oprand.toInt())
                } else {
//                    opratoin="0"
                    numbers.add(0)
                }
                oprations.add("-")
                oprand = ""
            }
            "=" -> {
                if (oprand.length > 0) {
                    numbers.add(oprand.toInt())
                }
                oprand = ""
                numbersHistory.clear()
                oprationsHistory.clear()

                numbersHistory.addAll(numbers)
                oprationsHistory.addAll(oprations)
                claculateOprations()
            }
            "History" -> {
                if (oprand.length > 0) {
                    oprand = oprand.dropLast(1)
                    opratoin = opratoin.dropLast(1)
                    operationText.value = opratoin
                } else {
                    if (oprations.size > 0 && numbers.size > 0) {
                        oprand = numbers[numbers.size - 1].toString()
                        numbers.removeAt(numbers.size - 1)
                        opratoin = opratoin.dropLast(1)
                        oprations.removeAt(oprations.size - 1)
                        operationText.value = opratoin
                    }
                }
            }
            else -> {
                oprand = oprand + label
            }

        }

        if (label != "=") {
            opratoin = opratoin + label
            operationText.value = opratoin
        }

    }

    private fun claculateOprations() {
        val multiplcationPosition = oprations.indexOf("*")
        if (multiplcationPosition >= 0) {
            val result = numbers[multiplcationPosition] * numbers[multiplcationPosition + 1]
            oprations.removeAt(multiplcationPosition)
            numbers.set(multiplcationPosition, result)
            numbers.removeAt(multiplcationPosition + 1)
            if (oprations.size > 0) {
                claculateOprations()
            } else {
                setResult(result)
            }
        }

        val additionPosition = oprations.indexOf("+")
        if (additionPosition >= 0) {
            val result = numbers[additionPosition] + numbers[additionPosition + 1]
            oprations.removeAt(additionPosition)
            numbers.set(additionPosition, result)
            numbers.removeAt(additionPosition + 1)
            if (oprations.size > 0) {
                claculateOprations()
            } else {
                setResult(result)
            }
        }

        val divisionPosition = oprations.indexOf("/")
        if (divisionPosition >= 0) {
            val result = numbers[divisionPosition] / numbers[divisionPosition + 1]
            oprations.removeAt(divisionPosition)
            numbers.set(divisionPosition, result)
            numbers.removeAt(divisionPosition + 1)
            if (oprations.size > 0) {
                claculateOprations()
            } else {
                setResult(result)
            }
        }

        val subtractionPosition = oprations.indexOf("-")
        if (subtractionPosition >= 0) {
            val result = numbers[subtractionPosition] - numbers[subtractionPosition + 1]
            oprations.removeAt(subtractionPosition)
            numbers.set(subtractionPosition, result)
            numbers.removeAt(subtractionPosition + 1)
            if (oprations.size > 0) {
                claculateOprations()
            } else {
                setResult(result)
            }
        }

        if (numbers.size == 1) {
            setResult(numbers[0])
        }
    }

    private fun setResult(result: Int) {
        addToHistory(result)
        resultText.value = "=" + result.toString()
        oprand = result.toString()
        opratoin = oprand
        numbers.clear()
        oprations.clear()


    }

    private fun addToHistory(result: Int) {
        if (historyList.size >= 10) {
            historyList.removeAt(0)
        }
        historyList.add(History(opratoin, result, numbersHistory, oprationsHistory))
//        numbersHistory.clear()
//        oprationsHistory.clear()
    }

    fun setFromHistory(history: History){
        resultText.value = "=" + history.result.toString()
        oprand = history.numbers[history.numbers.size-1].toString()
        opratoin = history.opration
        numbers.clear()
        oprations.clear()
        numbers.addAll(history.numbers)
        oprations.addAll(history.oprations)
        operationText.value = opratoin
    }

    fun allClear() {
        resultText.value = "=0"
        oprand = ""
        opratoin =""
        numbers.clear()
        oprations.clear()
        operationText.value = opratoin
    }
}
