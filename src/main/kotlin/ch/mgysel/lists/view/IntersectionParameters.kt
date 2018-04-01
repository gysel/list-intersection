package ch.mgysel.lists.view

import ch.mgysel.lists.Intersection
import tornadofx.getProperty
import tornadofx.property

class IntersectionParameters(sizeA: Int = 100_000, sizeB: Int = 100_000, rounds: Int = 10, implementation: Intersection.Implementation = Intersection.Implementation.KOTLIN_STDLIB) {

    var sizeA: Int by property(sizeA)
    fun sizeAProperty() = getProperty(IntersectionParameters::sizeA)

    var sizeB: Int by property(sizeB)
    fun sizeBProperty() = getProperty(IntersectionParameters::sizeB)

    var rounds: Int by property(rounds)
    fun roundsProperty() = getProperty(IntersectionParameters::rounds)

    var implementation: Intersection.Implementation by property(implementation)
    fun implementationProperty() = getProperty(IntersectionParameters::implementation)

    fun toDto() = IntersectionParametersValueObject(sizeA, sizeB, rounds, implementation)

}

/**
 * A [Value Object](https://martinfowler.com/bliki/ValueObject.html) according to Martin Fowler.
 */
data class IntersectionParametersValueObject(val sizeA: Int,
                                             val sizeB: Int,
                                             val rounds: Int,
                                             val implementation: Intersection.Implementation)