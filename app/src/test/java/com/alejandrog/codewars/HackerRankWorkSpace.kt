package com.alejandrog.codewars

import org.junit.Test
import java.math.RoundingMode

class HackerRankWorkSpace {

    /**
     * Given an array of integers, calculate the ratios of its elements that are positive, negative, and zero.
     * Print the decimal value of each fraction on a new line with  places after the decimal.
     */
    @Test
    fun `Plus Minus`() {
        plusMinus(arrayOf(-4, 3, -9, 0, 4, 1))
    }

    private fun plusMinus(arr: Array<Int>) {
        var ratioPositive = 0
        var ratioNegative = 0
        var ratioZero = 0

        for (number in arr) {
            if (number > 0) {
                ratioPositive++
            } else if (number < 0) {
                ratioNegative++
            } else {
                ratioZero++
            }
        }

        println(
            ratioPositive.toBigDecimal()
                .divide(arr.size.toBigDecimal(), 6, RoundingMode.CEILING)
        )
        println(
            ratioNegative.toBigDecimal()
                .divide(arr.size.toBigDecimal(), 6, RoundingMode.CEILING)
        )
        println(
            ratioZero.toBigDecimal()
                .divide(arr.size.toBigDecimal(), 6, RoundingMode.CEILING)
        )
    }

    /**
     * Staircase detail
     * #
     * ##
     * ###
     * ####
     * This is a staircase of size : N
     * Its base and height are both equal to . It is drawn using # symbols and spaces. The last line is not preceded by any spaces.
     * Write a program that prints a staircase of size .
     */

    @Test
    fun `Stair case`() {
        staircase(4)
        staircase(5)
    }

    private fun staircase(n: Int) {
        var stairCharacter = "#"
        for (stair in n downTo 1) {
            println(stairCharacter.padStart(n, ' '))
            stairCharacter += "#"
        }
    }

    /**
     * Given five positive integers, find the minimum and maximum values that can be calculated by summing exactly four of the five integers.
     * Then print the respective minimum and maximum values as a single line of two space-separated long integers.
     *
     * Example: [1, 3, 5, 7, 9]
     * 1 + 3 + 5 + 7 = 16
     * 3 + 5 + 7 + 9 = 24
     * Output
     * 16 24
     */

    @Test
    fun `Mini Max Sum`() {
        miniMaxSum(arrayOf(1, 3, 5, 7, 9))
        miniMaxSum(arrayOf(7, 69, 2, 221, 8974))
        miniMaxSum(arrayOf(256741038, 623958417, 467905213, 714532089, 938071625))
    }

    private fun miniMaxSum(arr: Array<Int>) {
        val result = mutableListOf<Long>()
        arr.forEach { num ->
            val total = arr.sumOf { it.toLong() } - num.toLong()
            result.add(total)
        }
        println("${result.minOf { it }} ${result.maxOf { it }}")
    }

    /**
     * Birthday Cake Candles
     * You are in charge of the cake for a child's birthday.
     * You have decided the cake will have one candle for each year of their total age.
     * They will only be able to blow out the tallest of the candles. Count how many candles are tallest.
     *
     * Example: [4, 4, 1, 3]
     * Output: 2 (2 candles of 4)
     */

    @Test
    fun `Birthday Cake Candles`() {
        println(birthdayCakeCandles(arrayOf(4, 4, 1, 3)))
    }

    private fun birthdayCakeCandles(candles: Array<Int>): Int {
        val maxValue = candles.maxOf { it }
        return candles.count { it == maxValue }
    }

    /**
     * Time Conversion
     *
     * Given a time in -hour AM/PM format, convert it to military (24-hour) time.
     * Note: - 12:00:00AM on a 12-hour clock is 00:00:00 on a 24-hour clock.
     * - 12:00:00PM on a 12-hour clock is 12:00:00 on a 24-hour clock.
     *
     */
    @Test
    fun `Time Conversion`() {
        println(timeConversion("07:05:45PM"))
        println(timeConversion("12:40:45PM"))
        println(timeConversion("12:40:00AM"))
        println(timeConversion("12:45:54PM"))
    }

    private fun timeConversion(s: String): String {
        val hour = s.split(":")[0]
        val minute = s.split(":")[1]
        val seconds = s.split(":")[2].slice(0..1)
        val format = s.split(":")[2].slice(2..3)
        return if (format == "PM") {
            if (hour.toInt() == 12) {
                "${hour.toInt()}:$minute:$seconds"
            } else {
                "${12 + hour.toInt()}:$minute:$seconds"
            }
        } else {
            if (hour.toInt() == 12) {
                "00:$minute:$seconds"
            } else {
                "$hour:$minute:$seconds"
            }
        }
    }

    @Test
    fun `Fizz Buzz`() {
        fizzBuzz(15)
    }

    private fun fizzBuzz(n: Int): Unit {
        for (value in 1..n) {
            if (value % 3 == 0 && value % 5 == 0) {
                println("FizzBuzz")
            } else {
                if (value % 3 == 0) {
                    println("Fizz")
                } else if (value % 5 == 0) {
                    println("Buzz")
                } else {
                    println(value)
                }
            }
        }
    }


    @Test
    fun `Get Stat For Requests`() {
        getStatForRequests(
            3,
            arrayOf(
                "0 sdsf www.google.com",
                "1 juytf www.google.com",
                "0 opoit www.kagle.com"
            ),
            arrayOf("juytf", "sdsf", "opoit")
        )
    }

    private fun getStatForRequests(m: Int, database: Array<String>, requests: Array<String>): Array<Array<String>> {
        val result = mutableListOf<Array<String>>()
        val usersAndUsage = mutableMapOf<String, Int>()
        val mapDatabase = mutableMapOf<String, Pair<String, String>>()

        // Populate database
        for (entry in database) {
            val userId = entry.split(" ")[0]
            val shortUrl = entry.split(" ")[1]
            val largeUrl = entry.split(" ")[2]

            // Short url -> Large url
            mapDatabase[shortUrl] = Pair(largeUrl, userId)

            // Add users
            usersAndUsage[userId] = 0
        }

        // Populate result
        for (request in requests) {
            val largeUrl = mapDatabase[request]?.first.orEmpty()
            val userId = mapDatabase[request]?.second.orEmpty()
            usersAndUsage[userId] = (usersAndUsage[userId] ?: 0) + 1
            result.add(arrayOf(largeUrl, usersAndUsage[userId].toString()))
        }
        return result.toTypedArray()
    }
}