package ch.mgysel.lists.view

import ch.mgysel.lists.controller.IntersectionController
import ch.mgysel.lists.css.Styles
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.scene.chart.NumberAxis
import javafx.scene.chart.XYChart
import tornadofx.*

class IntersectionView : View("Intersection Simulator") {

    private val sizes = FXCollections.observableArrayList<Int>((4..7).map { Math.pow(10.toDouble(), it.toDouble()).toInt() })
    private val rounds = FXCollections.observableArrayList(1, 5, 10, 20, 50)
    private val parameters = IntersectionParameters()

    private val status: TaskStatus by inject()
    private val controller by inject<IntersectionController>()

    override val root = vbox {

        form {
            fieldset("Params") {
                field("Size of list A") {
                    combobox(property = parameters.sizeAProperty(), values = sizes)
                }
                field("Size of list B") {
                    combobox(property = parameters.sizeBProperty(), values = sizes)
                }
                field("Repetitions") {
                    combobox(property = parameters.repetitionsProperty(), values = rounds)
                }
            }

            hbox {
                button("Run") {
                    action {
                        controller.runIntersection(parameters.toDto())
                    }
                    enableWhen(status.running.not())
                }
                progressbar(status.progress)
                label(status.message) {
                    addClass(Styles.statusClass)
                }
            }

            separator()

            linechart<Number, Number>("Intersection Performance", createNumberAxis("Repetition"), createNumberAxis("ms")) {
                controller.implementations().forEach {
                    val elements: ObservableList<XYChart.Data<Number, Number>> = controller.getDataList(it)
                    series(name = "$it", elements = elements)
                }
            }

        }

    }

    private fun createNumberAxis(name: String): NumberAxis {
        return NumberAxis().apply {
            label = name
        }
    }

}