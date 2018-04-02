package ch.mgysel.lists

import ch.mgysel.lists.css.Styles
import ch.mgysel.lists.view.IntersectionView
import javafx.scene.image.Image
import tornadofx.*

class IntersectionApp : App(Image("icon.png"), IntersectionView::class, Styles::class)