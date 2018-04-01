package ch.mgysel.lists

import ch.mgysel.lists.Intersection.Implementation.*

class Intersection<T> {

    fun getImplementation(implementation: Implementation): (Collection<T>, Collection<T>) -> Collection<T> {
        return when (implementation) {
            KOTLIN_STDLIB -> { first: Collection<T>, second: Collection<T> -> first intersect second }
            ITERATION -> { first: Collection<T>, second: Collection<T> -> intersectUsingIteration(first, second) }
            SEQUENCE -> { first: Collection<T>, second: Collection<T> -> intersectUsingSequence(first, second) }
        }
    }

    enum class Implementation {
        KOTLIN_STDLIB, ITERATION, SEQUENCE
    }

}

private fun <T> intersectUsingIteration(first: Collection<T>, second: Collection<T>): Collection<T> {
    val set = first.toSet()
    return second.filter { set.contains(it) }.toSet()
}

private fun <T> intersectUsingSequence(first: Collection<T>, second: Collection<T>): Collection<T> {
    val set = first.toSet()
    return second.asSequence().filter { set.contains(it) }.toSet()
}