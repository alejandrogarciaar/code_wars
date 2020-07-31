package com.alejandrog.codewars

import org.junit.Assert.assertEquals
import org.junit.Test
import java.math.BigInteger


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class CodeWarTestWorkSpace {

    @Test
    fun `Replace all strings with quotes`() {
        val quoteWord = "This awesome, \"asdasd\", there is support to \"quote\" strings!"
        val newQuoteArray = ArrayList(quoteWord.split(" "))
        for ((index, char) in newQuoteArray.withIndex()) {
            if (char.contains("\"")) {
                if (char.contains(",")) {
                    newQuoteArray[index] = "\"replaced!\","
                } else {
                    newQuoteArray[index] = "\"replaced!\""
                }
            }
        }
        val newFinalQuote = newQuoteArray.joinToString(" ")
        assertEquals("This awesome, \"replaced!\", there is support to \"replaced!\" strings!", newFinalQuote)
    }

    @Test
    fun `Convert string to camel case`() {
        val inputString = "the_stealth_warrior"
        val newString = toCamelCase(inputString)
        assertEquals("theStealthWarrior", newString)
    }

    private fun toCamelCase(str: String): String {
        val newString = StringBuilder()
        var dashUnderscore = false
        for (char in str) {
            dashUnderscore = if (char.toString() == "_" || char.toString() == "-") {
                true
            } else {
                if (dashUnderscore) {
                    newString.append(char.toUpperCase())
                } else {
                    newString.append(char)
                }
                false
            }
        }
        return newString.toString()
    }

    @Test
    fun `Return the number (count) of vowels in the given string`() {
        val str = "abracadabra"
        //val str = "test"
        assertEquals(5, getCount(str))
        assertEquals(5, str.count { it in "aeiuo" })
    }

    private fun getCount(str: String): Int {
        val vowels = arrayListOf("a", "e", "i", "o", "u")
        var count = 0
        for (s in str) {
            if (vowels.contains(s.toString().toLowerCase())) count += 1
        }
        return count
    }

    @Test
    fun `Get perimeter of squares in a rectangle`() {
        assertEquals(80.toBigInteger(), perimeter(5))
    }

    //0, 1, 2, 3, 4, 5, 6, 7
    //0, 1, 1, 2, 3, 5, 8, 13
    private fun perimeter(n: Int): BigInteger {
        var aux = 1L
        var fib = 0L
        var finalValue = 0L
        if (n > 0) {
            (1..n + 1).forEach { _ ->
                aux += fib
                fib = aux - fib
                finalValue += fib
            }
        } else {
            return 0.toBigInteger()
        }
        return (4 * finalValue).toBigInteger()
    }

    @Test
    fun `Remove first and last character`() {
        assertEquals("loquen", removeFirstAndLastCharacter("eloquent"))
        assertEquals("ountr", removeFirstAndLastCharacter("country"))
        assertEquals("iv", removeFirstAndLastCharacter("live"))
    }

    private fun removeFirstAndLastCharacter(str: String): String {
        return str.drop(1).dropLast(1)
    }

    @Test
    fun `Opposite number`() {
        assertEquals(-1, opposite(1))
        assertEquals(25, opposite(-25))
        assertEquals(0, opposite(0))
    }

    private fun opposite(number: Int): Int {
        return number * -1
    }

    @Test
    fun `Return negative`() {
        assertEquals(-42, Kata().makeNegative(42).toLong())
    }

    private class Kata {
        fun makeNegative(x: Int): Int {
            return if (x < 0) x else -x
        }
    }

    @Test
    fun `Basic tests`() {
        assertEquals(7, oddCount(15))
        assertEquals(7511, oddCount(15023))
    }

    private fun oddCount(n: Int): Int = n / 2

    @Test
    fun `Alternate capitalization`() {
        assertEquals(listOf("AbCdEf", "aBcDeF"), capitalize("abcdef"))
        assertEquals(listOf("CoDeWaRs", "cOdEwArS"), capitalize("codewars"))
        assertEquals(listOf("AbRaCaDaBrA", "aBrAcAdAbRa"), capitalize("abracadabra"))
        assertEquals(listOf("CoDeWaRrIoRs", "cOdEwArRiOrS"), capitalize("codewarriors"))
    }

    private fun capitalize(text: String): List<String> {
        val firstStr = StringBuilder()
        val secondStr = StringBuilder()
        text.toList()
            .mapIndexed { index, character ->
                if (index == 0 || index % 2 == 0) {
                    firstStr.append(character.toUpperCase())
                    secondStr.append(character)
                } else {
                    firstStr.append(character)
                    secondStr.append(character.toUpperCase())
                }
            }
        return listOf(firstStr.toString(), secondStr.toString())
    }


}