package ch.mgysel.lists.controller

import ch.mgysel.lists.Intersection
import ch.mgysel.lists.util.createRandomNumbers
import ch.mgysel.lists.view.IntersectionParametersValueObject
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.scene.chart.XYChart
import tornadofx.Controller
import kotlin.system.measureTimeMillis

class IntersectionController : Controller() {

    private val intersection = Intersection<Int>()
    val intersectionRuns: ObservableList<XYChart.Data<Number, Number>> = FXCollections.observableArrayList()

    fun runIntersection(dto: IntersectionParametersValueObject) {

        runAsync {
            println("Running with params $dto...")
            updateMessage("Preparing lists with random numbers...")
            updateProgress(0, dto.rounds.toLong())

            val listA = createList(dto.sizeA)
            val listB = createList(dto.sizeB)

            val function = intersection.getImplementation(dto.implementation)

            updateMessage("Calculating intersections...")
            val data: List<XYChart.Data<Number, Number>> = (1..dto.rounds).map {

                // run GC before now to reduce probability that GC runs while we measure the time
                System.gc()

                val millis = measureTimeMillis {
                    val result = function(listA, listB)
                    println("Found ${result.size} records")
                }
                updateProgress(it.toLong(), dto.rounds.toLong())

                println("Calculated intersection using ${dto.implementation} in ${millis}ms")
                XYChart.Data<Number, Number>(it - 1, millis.toInt())
            }
            updateMessage("Done!")
            data
        } ui { data ->
            intersectionRuns.setAll(data)
        }

    }

    private fun createList(size: Int) = createRandomNumbers(size, calculateMax(size))

    private fun calculateMax(size: Int) = (size * 1.5).toInt()

}