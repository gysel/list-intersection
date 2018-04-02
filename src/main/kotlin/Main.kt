import javafx.application.Application

fun main(args: Array<String>) {
    // JavaFX uses java.util.logging, redirect that to Log4J
    System.setProperty("java.util.logging.manager", "org.apache.logging.log4j.jul.LogManager")

    Application.launch(IntersectionApp::class.java, *args)

}

