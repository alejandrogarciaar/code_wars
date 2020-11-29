package com.alejandrog.codewars

import org.junit.Test

class KotlinFunctions {

    private val mockPerson = Person(
        age = 31,
        name = "Alejandro",
        id = "123"
    )

    /**
     * Run expression
     * It is similar to Let - Unlikes -> 'it' is not available
     */
    @Test
    fun `testing run expression returning block unit`() {
        val value = mockPerson.run { age += 10 }
        // returning block unit
        println(value)
    }

    @Test
    fun `testing run expression returning string`() {
        val value = mockPerson.run {
            // update age
            age += 10
            // changing type
            "Hello world"
        }
        println(value)
    }

    /**
     * Also
     * Return same object but updated.
     * Allows 'it'
     * Expressions does some additional processing on the object it was invoked.
     * Unlike let, it returns the original object instead of any new return data. Hence the return data has always the same type.
     * Like let, also uses it to
     */

    @Test
    fun `testing also expression updating object requested`() {
        val value = mockPerson
            .also { it.age = it.age + 1 }
            .also { it.age = it.age + 2 }
        println(value)
    }

    /**
     * Let - Takes and object and returns the result of the lambda expression
     */

    @Test
    fun `testing let expression with block as return type`() {
        val value = mockPerson.let { it.age += 10 }
        val valueWithAlso = mockPerson.also { it.age = it.age + 10 }
        println(value)
        println(valueWithAlso)
    }

    @Test
    fun `testing let expression with int value`() {
        val value = mockPerson.let { it.age + 10 }
        println(value)
    }

    @Test
    fun `testing let expression with string return`() {
        val value = mockPerson.let {
            it.age += 10
            it
        }
        println(value)
    }

    @Test
    fun `testing let expression with chain`() {
        val value = mockPerson
            .let { it.age + 2 }
            .let {
                val newValue = it + 2
                newValue
            }
        println(value)
    }

    /**
     * Apply
     * Kotlin apply is an extension function on a type.
     * It runs on the object reference (also known as receiver) into the expression and returns the object reference on completion.

     */

    @Test
    fun `testing apply expression`() {
        val newPerson = mockPerson.apply {
            this.age = 50
            this.name = "Test"
        }
        println(newPerson)
    }


    /**
     * With
     * Like apply, with is used to change instance properties without the need to call dot operator over the reference every time.
     */
    @Test
    fun `testing with expression passing base object`() {
        with(mockPerson) {
            age += 10
            name = "Test with"
            id = "12345"
        }
        println(mockPerson)
    }

    @Test
    fun `testing with expression with unit block as return`() {
        val unit = with(mockPerson) {
            age += 10
            name = "Test 'with' with return"
            id = "12345"
        }
        println(unit)
        println(mockPerson)
    }

    @Test
    fun `testing with expression with person as return`() {
        val newPerson = with(mockPerson) {
            age += 10
            name = "Test 'with' with return"
            id = "12345"
            this
        }
        println(newPerson)
        println(mockPerson)
    }

    @Test
    fun `testing with expression with another type`() {
        val newText = with(mockPerson) {
            age += 10
            name = "Test 'with' with return"
            id = "12345"
            val xyz = "test"
            xyz
        }
        println(newText)
        println(mockPerson)
    }


    data

    class Person(
        var age: Int,
        var name: String,
        var id: String
    ) {
        fun printPerson() = println(this.toString())
    }
}