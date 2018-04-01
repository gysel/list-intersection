package ch.mgysel.lists

import ch.mgysel.lists.Intersection.Implementation.*
import io.kotlintest.matchers.shouldBe
import io.kotlintest.matchers.singleElement
import org.junit.Test

class IntersectionTest {

    private val implementations = Intersection<Int>()
    private val firstList = listOf(2, 3, 1)
    private val secondList = listOf(4, 3, 5)

    @Test
    fun `Kotlin Stdlib implementation`() {
        val implementation = implementations.getImplementation(KOTLIN_STDLIB)

        val intersection = implementation(firstList, secondList)
        intersection shouldBe singleElement(3)
    }

    @Test
    fun `Iteration implementation`() {
        val implementation = implementations.getImplementation(ITERATION)

        val intersection = implementation(firstList, secondList)
        intersection shouldBe singleElement(3)
    }

    @Test
    fun `Sequence implementation`() {
        val implementation = implementations.getImplementation(SEQUENCE)

        val intersection = implementation(firstList, secondList)
        intersection shouldBe singleElement(3)
    }

}