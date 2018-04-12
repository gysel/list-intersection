package ch.mgysel.lists

import io.kotlintest.matchers.*
import io.kotlintest.specs.StringSpec

class IntersectionTest : StringSpec({

    val intersection = Intersection<Int>()

    val implementations = Intersection.Implementation.values()

    val firstList = listOf(2, 3, 1)
    val secondList = listOf(4, 3, 5)
    implementations.forEach { implementation ->
        val function = intersection.getImplementation(implementation)

        "$implementation - simple intersection" {
            val result = function(firstList, secondList)
            result shouldBe singleElement(3)
        }

        "$implementation - duplicates are shown once in result" {
            val result = function(listOf(1, 2, 2, 3), listOf(2, 2, 3, 3))
            result should haveSize(2)
            result should containsAll(2, 3)
        }

    }

})

