package com.example.newninebank

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(true,validateCpf("12345678909")
        )
    }

}

fun validateCpf(cpf: String): Boolean {

    val lastDigit = cpf.last().digitToInt()
    val secondToLastDigit = cpf[9]
    var count = cpf.length
    val calTimes: (Int, Int) -> Int = { digit: Int, countL: Int -> digit.times(countL) }
    var sum = 0
    for (i in cpf) {
        if (count > 2) {
            count--
            sum += calTimes(i.digitToInt(), count)
        }
    }
    var digit1Result: Int = 11 - (sum % 11)
    if (digit1Result > 9) {
        digit1Result = 0
    }
    count = cpf.length
    sum = 0
    val newCPF = cpf.replaceRange(9, 11, digit1Result.toString())
    for (a in newCPF) {
        if (count >= 2) {
            sum += calTimes(a.digitToInt(), count)
            count--
        }
    }
    var digit2Result = 11 - (sum % 11)
    if (digit2Result > 9) {
        digit2Result = 0
    }
    val result: (Int, Int) -> Boolean = { digit1: Int, digit2: Int ->

        (digit1 == secondToLastDigit.digitToInt()) && (digit2 == lastDigit)
    }
    return result(digit1Result, digit2Result)
}