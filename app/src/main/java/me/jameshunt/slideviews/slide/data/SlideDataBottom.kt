package me.jameshunt.slideviews.slide.data

import android.view.View
import me.jameshunt.slideviews.slide.eval.BottomSlideHeightEval
import me.jameshunt.slideviews.slide.eval.SlideEval

/**
 * Created by James on 10/4/2017.
 */
class SlideDataBottom(mainView: View, secondaryView: View): SlideData(mainView = mainView, secondaryView = secondaryView) {

    override fun fixOverlap() {

        secondaryView.layoutParams.height = closedHeight

    }

    override fun followFinger(rawY: Int) {
        val totalHeightDiff = yStart - rawY

        val params = mainView.layoutParams
        params.height = currentHeight + totalHeightDiff.toInt()
        mainView.layoutParams = params
    }

    override fun convertDirection(direction: Boolean): Boolean {
        return direction
    }

    override fun getEvaluator(direction: Boolean): SlideEval {
        return BottomSlideHeightEval(mainView, secondaryView, direction, closedHeight)
    }
}