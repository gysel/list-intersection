package ch.mgysel.lists.view

import tornadofx.*

class IntersectionParameters(sizeA: Int = 100_000, sizeB: Int = 100_000, rounds: Int = 10) {

    var sizeA: Int by property(sizeA)
    fun sizeAProperty() = getProperty(IntersectionParameters::sizeA)

    var sizeB: Int by property(sizeB)
    fun sizeBProperty() = getProperty(IntersectionParameters::sizeB)

    var repetitions: Int by property(rounds)
    fun repetitionsProperty() = getProperty(IntersectionParameters::repetitions)

    fun toDto() = IntersectionParametersValueObject(sizeA, sizeB, repetitions)

}

