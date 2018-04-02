package ch.mgysel.lists.view

/**
 * A [Value Object](https://martinfowler.com/bliki/ValueObject.html) according to Martin Fowler.
 */
data class IntersectionParametersValueObject(val sizeA: Int,
                                             val sizeB: Int,
                                             val repetitions: Int)