package ch.mgysel.lists

import ch.mgysel.lists.Intersection.Implementation.*

class Intersection<T> {

    fun getImplementation(implementation: Implementation): (Iterable<T>, Iterable<T>) -> Iterable<T> {
        return when (implementation) {
            KOTLIN_STDLIB -> { first: Iterable<T>, second: Iterable<T> -> first intersect second }
            ITERATION -> { first: Iterable<T>, second: Iterable<T> -> intersectUsingIteration(first, second) }
            SEQUENCE -> { first: Iterable<T>, second: Iterable<T> -> intersectUsingSequence(first, second) }
        }
    }

    enum class Implementation {
        KOTLIN_STDLIB, ITERATION, SEQUENCE
    }

}

private fun <T> intersectUsingIteration(first: Iterable<T>, second: Iterable<T>): Iterable<T> {
    val set = first.toSet()
    return second.filter { set.contains(it) }.toSet()
}

private fun <T> intersectUsingSequence(first: Iterable<T>, second: Iterable<T>): Iterable<T> {
    val set = first.toSet()
    return second.asSequence().filter { set.contains(it) }.toSet()
}