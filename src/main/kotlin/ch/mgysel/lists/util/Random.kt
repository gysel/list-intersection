package ch.mgysel.lists.util

import java.util.concurrent.ThreadLocalRandom

fun createRandomNumbers(size: Int, max: Int) = (1..size).map { ThreadLocalRandom.current().nextInt(0, max) }
