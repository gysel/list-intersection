package ch.mgysel.lists.util

import io.kotlintest.matchers.beEmpty
import io.kotlintest.matchers.haveSize
import io.kotlintest.matchers.should
import org.junit.Test

class RandomKtTest {

    @Test
    fun `create 10 random numbers`() {
        val numbers = createRandomNumbers(10, 50)
        numbers should haveSize(10)
        numbers.filter { it > 50 } should beEmpty()
    }
}