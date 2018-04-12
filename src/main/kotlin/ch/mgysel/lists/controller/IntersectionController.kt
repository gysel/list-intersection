package ch.mgysel.lists.controller

import ch.mgysel.lists.Intersection
import ch.mgysel.lists.util.createRandomNumbers
import ch.mgysel.lists.valueobject.IntersectionParameters
import javafx.collections.FXCollections
import javafx.scene.chart.XYChart
import tornadofx.*
import java.util.concurrent.atomic.AtomicLong
import kotlin.system.measureTimeMillis

typealias ChartDataRecord = XYChart.Data<Number, Number>

class IntersectionController : Controller() {

    private val intersection = Intersection<Int>()
    private val intersectionRuns = implementations()
            .map { it to FXCollections.observableArrayList<ChartDataRecord>() }.toMap()


    fun runIntersection(dto: IntersectionParameters) {

        runAsync {
            log.info("Running with params $dto...")
            updateMessage("Preparing lists with random numbers...")
            val taskCount = implementations().size.toLong() * dto.repetitions + 2
            val taskCounter = AtomicLong(0)
            val tick = { updateProgress(taskCounter.getAndIncrement(), taskCount) }
            tick()

            val listA = createList(dto.sizeA)
            tick()
            val listB = createList(dto.sizeB)
            tick()

            val data: Map<Intersection.Implementation, List<ChartDataRecord>> = implementations().map { implementation ->
                updateMessage("Calculating intersections of $implementation...")
                val dataOfImplementation = (1..dto.repetitions).map {

                    val function = intersection.getImplementation(implementation)
                    // run GC to reduce probability that GC runs while we measure the time
                    System.gc()

                    val millis = measureTimeMillis {
                        val result = function(listA, listB)
                        log.info("Found ${result.size} records")
                    }
                    tick()

                    log.info("Calculated intersection using $implementation in ${millis}ms")
                    XYChart.Data<Number, Number>(it - 1, millis.toInt())
                }
                Pair(implementation, dataOfImplementation)
            }.toMap()
            updateMessage("Done!")
            data
        } ui { data ->
            log.info("Updating UI with ${data.size} records")
            data.forEach { implementation, dataOfImplementation ->
                getDataList(implementation).setAll(dataOfImplementation)
            }
        }

    }

    fun implementations() = Intersection.Implementation.values()
    fun getDataList(implementation: Intersection.Implementation) = intersectionRuns[implementation]!!

    private fun createList(size: Int) = createRandomNumbers(size, calculateMax(size))

    private fun calculateMax(size: Int) = (size * 1.5).toInt()

}