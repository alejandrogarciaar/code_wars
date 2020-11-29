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

    @Test
    fun `Create a function that checks if a number n is divisible by two numbers x AND y -- All inputs are positive, non-zero digits`() {
        assertEquals(false, isDivisible(3, 3, 4))
        assertEquals(true, isDivisible(12, 3, 4))
        assertEquals(false, isDivisible(8, 3, 4))
        assertEquals(true, isDivisible(48, 3, 4))
        assertEquals(true, isDivisible(100, 5, 10))
        assertEquals(false, isDivisible(100, 5, 3))
        assertEquals(true, isDivisible(4, 4, 2))
        assertEquals(false, isDivisible(5, 2, 3))
        assertEquals(true, isDivisible(17, 17, 17))
        assertEquals(true, isDivisible(17, 1, 17))
    }

    private fun isDivisible(n: Int, x: Int, y: Int): Boolean {
        return n % x == 0 && n % y == 0
    }

    @Test
    fun `In this simple Kata your task is to create a function that turns a string into a Mexican Wave -- You will be passed a string and you must return that string in an array where an uppercase letter is a person standing up`() {
        assertEquals(listOf("A       b    ", "a       B    "), wave("a       b    "))
        assertEquals(listOf("This is a few words", "tHis is a few words", "thIs is a few words", "thiS is a few words", "this Is a few words", "this iS a few words", "this is A few words", "this is a Few words", "this is a fEw words", "this is a feW words", "this is a few Words", "this is a few wOrds", "this is a few woRds", "this is a few worDs", "this is a few wordS"), wave("this is a few words"))
        assertEquals(listOf<String>(), wave(""))
        assertEquals(listOf(" Gap ", " gAp ", " gaP "), wave(" gap "))
    }

    private fun wave(str: String): List<String> {
        val response = arrayListOf<String>()
        for ((index, element) in str.withIndex()) {
            if (element.isLetter()) {
                val strArray = str.toCharArray()
                strArray[index] = strArray[index].toUpperCase()
                response.add(String(strArray))
            }
        }
        return response
    }

    @Test
    fun `Find the total sum of internal angles (in degrees) in an n-sided simple polygon -- N will be greater than 2`() {
        assertEquals(180, angle(3))
        assertEquals(360, angle(4))
    }

    private fun angle(value: Int): Int {
        return (value - 2) * 180
    }

    @Test
    fun `You will be given a prime number p and your task is to find the smallest positive integer n such that p's divisibility testing is n-sum or n-alt-sum`() {
        assertEquals("1-sum", solve(3))
        assertEquals("3-altsum", solve(7))
        assertEquals("1-altsum", solve(11))
        assertEquals("3-altsum", solve(13))
        assertEquals("3-sum", solve(37))
        assertEquals("23-altsum", solve(47))
        assertEquals("4-altsum", solve(73))
        assertEquals("7-sum", solve(239))
        assertEquals("47006-altsum", solve(376049))
        assertEquals("499941-sum", solve(999883))
        assertEquals("12350861-sum", solve(24701723))
        assertEquals("11484850-altsum", solve(45939401))
    }

    private fun solve(prime: Int): String {
        return ""
    }
}