package ch.mgysel.lists.view

import ch.mgysel.lists.Intersection
import ch.mgysel.lists.controller.IntersectionController
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.scene.chart.NumberAxis
import javafx.scene.chart.XYChart
import tornadofx.*

class IntersectionView : View("Intersection Simulator") {

    private val sizes = FXCollections.observableArrayList<Int>((4..7).map { Math.pow(10.toDouble(), it.toDouble()).toInt() })
    private val rounds = FXCollections.observableArrayList(1, 5, 10, 20, 50)
    private val implementations = FXCollections.observableArrayList(*Intersection.Implementation.values())
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
            }

            fieldset("Algorithm") {
                field("Implementation") {
                    combobox(property = parameters.implementationProperty(), values = implementations)
                }
                field("Rounds") {
                    combobox(property = parameters.roundsProperty(), values = rounds)
                }
            }

            hbox {
                button("Run") {
                    action {
                        controller.runIntersection(parameters.toDto())
                    }
                }
                progressbar(status.progress)
                label(status.message)
            }

            linechart<Number, Number>("Intersection Test", createNumberAxis("Round"), createNumberAxis("ms")) {
                val elements: ObservableList<XYChart.Data<Number, Number>> = controller.intersectionRuns
                series(name = "Implementation Performance", elements = elements)
            }

        }

    }

    private fun createNumberAxis(name: String): NumberAxis {
        return NumberAxis().apply {
            label = name
        }
    }

}