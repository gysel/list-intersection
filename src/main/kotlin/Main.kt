import ch.mgysel.lists.view.IntersectionView
import javafx.application.Application
import javafx.scene.text.FontWeight
import tornadofx.*

fun main(args: Array<String>) {
    // JavaFX uses java.util.logging, redirect that to Log4J
    System.setProperty("java.util.logging.manager", "org.apache.logging.log4j.jul.LogManager")

    Application.launch(HelloWorldApp::class.java, *args)

}

class HelloWorldApp : App(IntersectionView::class, Styles::class)

class Styles : Stylesheet() {
    init {
        label {
            fontSize = 20.px
            fontWeight = FontWeight.BOLD
            backgroundColor += c("#cecece")
        }
    }
}
