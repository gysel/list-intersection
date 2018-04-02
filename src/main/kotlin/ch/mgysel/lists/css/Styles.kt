package ch.mgysel.lists.css

import tornadofx.*

class Styles : Stylesheet() {
    init {
        statusClass {
            padding = box(5.px)
        }
        progressBar {
            padding = box(4.px, 10.px)
        }
        separator {
            padding = box(10.px, 0.px)
        }
    }

    companion object {
        val statusClass by cssclass()
    }
}