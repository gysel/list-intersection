package ch.mgysel.lists

import io.kotlintest.matchers.*
import io.kotlintest.specs.StringSpec

class IntersectionTest : StringSpec({

    val intersections = Intersection.values()

    val firstList = listOf(2, 3, 1)
    val secondList = listOf(4, 3, 5)
    intersections.forEach { intersection ->

        "$intersection - simple intersection" {
            val result = intersection.apply(firstList, secondList)
            result shouldBe singleElement(3)
        }

        "$intersection - duplicates are shown once in result" {
            val result = intersection.apply(listOf(1, 2, 2, 3), listOf(2, 2, 3, 3))
            result should haveSize(2)
            result should containsAll(2, 3)
        }

    }

})

