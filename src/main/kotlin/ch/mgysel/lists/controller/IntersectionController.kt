package ch.mgysel.lists.controller

import ch.mgysel.lists.domain.Intersection
import ch.mgysel.lists.util.createRandomNumbers
import ch.mgysel.lists.valueobject.IntersectionParameters
import io.reactivex.Observable
import tornadofx.*
import java.util.concurrent.atomic.AtomicLong
import kotlin.system.measureTimeMillis


class IntersectionController : Controller() {

    data class ChartDataRecord(val index: Int, val milliseconds: Int)
    data class DataRecordEvent(val intersection: Intersection, val record: ChartDataRecord)

    fun runIntersection(dto: IntersectionParameters): Observable<DataRecordEvent> {

        return Observable.create { emitter ->
            runAsync {
                log.info("Running with params $dto...")
                updateMessage("Preparing lists with random numbers...")
                val taskCount = intersections().size.toLong() * dto.repetitions + 2
                val taskCounter = AtomicLong(0)
                fun tick() {
                    updateProgress(taskCounter.getAndIncrement(), taskCount)
                }
                tick()

                val listA = createList(dto.sizeA)
                tick()
                val listB = createList(dto.sizeB)
                tick()

                intersections().forEach { intersection ->
                    updateMessage("Calculating intersections of $intersection...")
                    (1..dto.repetitions).forEach {

                        // Ask the VM to do GC to reduce probability that GC runs while we measure the time
                        System.gc()

                        val millis = measureTimeMillis {
                            val result = intersection.apply(listA, listB)
                            log.info("Found ${result.size} records")
                        }
                        tick()

                        log.info("Calculated intersection using $intersection in ${millis}ms")
                        val data = ChartDataRecord(it - 1, millis.toInt())
                        emitter.onNext(DataRecordEvent(intersection, data))
                    }
                }
                updateMessage("Done!")
            }
        }

    }

    fun intersections() = Intersection.values()

    private fun createList(size: Int) = createRandomNumbers(size, calculateMax(size))

    private fun calculateMax(size: Int) = (size * 1.5).toInt()

}