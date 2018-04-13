package ch.mgysel.lists.view

import ch.mgysel.lists.controller.IntersectionController
import ch.mgysel.lists.css.Styles
import ch.mgysel.lists.valueobject.IntersectionParameters
import javafx.beans.property.SimpleIntegerProperty
import javafx.collections.FXCollections.observableArrayList
import javafx.collections.ObservableList
import javafx.geometry.Orientation.VERTICAL
import javafx.scene.chart.NumberAxis
import javafx.scene.chart.XYChart
import tornadofx.*

class IntersectionView : View("Intersection Simulator") {

    private val sizes = observableArrayList((4..7).map { Math.pow(10.toDouble(), it.toDouble()).toInt() })
    private val rounds = observableArrayList(1, 5, 10, 20, 50)

    private val model = object : ViewModel() {
        val sizeA = bind { SimpleIntegerProperty(100_000) }
        val sizeB = bind { SimpleIntegerProperty(100_000) }
        val repetitions = bind { SimpleIntegerProperty(10) }
        fun toDto() = IntersectionParameters(
                sizeA.value.toInt(),
                sizeB.value.toInt(),
                repetitions.value.toInt())
    }

    private val status: TaskStatus by inject()
    private val controller: IntersectionController by inject()

    override val root = vbox {
        addClass(Styles.root)

        form {
            hbox(20) {
                fieldset("Params") {
                    field("Size of list A") {
                        combobox(property = model.sizeA, values = sizes)
                    }
                    field("Size of list B") {
                        combobox(property = model.sizeB, values = sizes)
                    }
                    field("Repetitions") {
                        combobox(property = model.repetitions, values = rounds)
                    }
                }
                fieldset("Algorithms", labelPosition = VERTICAL) {
                    field("KOTLIN_STDLIB") {
                        label("The Iterable.intersect in the Kotlin standard library.")
                    }
                    field("ITERATION") {
                        label("Creates a HashSet from A and iterates over B searching for entries present in the HashSet.")
                    }
                    field("SEQUENCE") {
                        label("Same as ITERATION but uses a Kotlin Sequence instead of a normal iteration.")
                    }
                }

            }
            hbox {
                button("Run") {
                    action {
                        controller.runIntersection(model.toDto())
                    }
                    enableWhen(status.running.not())
                }
                progressbar(status.progress)
                label(status.message) {
                    addClass(Styles.statusClass)
                }
            }
        }

        separator()

        linechart<Number, Number>("Intersection Performance", createNumberAxis("Repetition"), createNumberAxis("ms")) {
            controller.intersections().forEach {
                val elements: ObservableList<XYChart.Data<Number, Number>> = controller.getDataList(it)
                series(name = "$it", elements = elements)
            }
        }

    }

    private fun createNumberAxis(name: String): NumberAxis {
        return NumberAxis().apply {
            label = name
        }
    }

}