package me.jameshunt.slideviews.slide.data

import android.view.View
import me.jameshunt.slideviews.Dimensions
import me.jameshunt.slideviews.slide.eval.SlideEval

/**
 * Created by James on 10/4/2017.
 */
abstract class SlideData(
        var yStart: Float = 0f,
        var currentHeight: Int = Dimensions.dpToPx(56).toInt(),
        var lastY: Float = 0f,
        var mainView: View,  //the one you can touch
        var secondaryView: View  //the one that needs to move out of the way
) {

    val closedHeight = Dimensions.dpToPx(56).toInt()
    
    abstract fun fixOverlap()

    abstract fun followFinger(rawY: Int)

    abstract fun convertDirection(direction: Boolean): Boolean

    abstract fun getEvaluator(direction: Boolean): SlideEval
}