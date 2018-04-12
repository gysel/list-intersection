package ch.mgysel.lists

import ch.mgysel.lists.css.Styles
import ch.mgysel.lists.view.IntersectionView
import javafx.application.Application
import javafx.scene.image.Image
import tornadofx.*

class IntersectionApp : App(Image("icon.png"), IntersectionView::class, Styles::class)

fun main(args: Array<String>) {
    // JavaFX uses java.util.logging, redirect that to Log4J
    System.setProperty("java.util.logging.manager", "org.apache.logging.log4j.jul.LogManager")

    Application.launch(IntersectionApp::class.java, *args)

}

