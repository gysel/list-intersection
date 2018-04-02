A little educational program to test different list intersection implementations.

![App Screenshot](https://raw.githubusercontent.com/gysel/list-intersection/master/IntersectionSimulator.png)

# Stack

* [Kotlin](https://kotlinlang.org/)
* [Gradle](https://gradle.org/)
* [TornadoFX](https://github.com/edvin/tornadofx)
* [Kotlintest](https://github.com/kotlintest/kotlintest)
* [Log4J 2](https://logging.apache.org/log4j/2.x/)

The exact library versions are all listed in `build.gradle`.

# Build

    ./gradlew clean build

Currently no executable jar is created. I recommend to run the main function from Gradle or an IDE.

    ./gradlew run

# Run Tests

    ./gradlew clean test

A JUnit test report is created: `build/reports/tests/test/index.html`.

# Ideas

Possible enhancements:

* [TestFX](https://github.com/TestFX/TestFX) UI Tests
* Live updates of result during a run, possibly using [RxKotlinFx](https://github.com/thomasnield/RxKotlinFX)
* Create a package using [javafx-gradle-plugin](https://github.com/FibreFoX/javafx-gradle-plugin)

# License

**"THE BEER-WARE LICENSE" (Revision 42)**

<mail@mgysel.ch> wrote this project. As long as you retain this notice you can do whatever you want with this stuff.
If we meet some day, and you think this stuff is worth it, you can buy me a beer in return - *Michael Gysel*.