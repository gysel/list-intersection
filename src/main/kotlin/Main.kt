import ch.mgysel.lists.view.IntersectionView
import javafx.application.Application
import javafx.scene.text.FontWeight
import tornadofx.App
import tornadofx.Stylesheet
import tornadofx.c
import tornadofx.px

fun main(args: Array<String>) {

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
