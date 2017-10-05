package me.jameshunt.slideviews.slide.data

import android.view.View
import android.widget.FrameLayout
import me.jameshunt.slideviews.slide.eval.SlideEval
import me.jameshunt.slideviews.slide.eval.TopSlideHeightEval

/**
 * Created by James on 10/4/2017.
 */

class SlideDataTop(mainView: View, secondaryView: View): SlideData(mainView = mainView, secondaryView = secondaryView) {

    override fun fixOverlap() {

        val screenHeight = (mainView.parent as FrameLayout).height

        val bottomYMain = mainView.y.toInt() + mainView.layoutParams.height

        val secondaryHeight = screenHeight - bottomYMain

        if(secondaryView.y < mainView.y + mainView.layoutParams.height) {
            secondaryView.layoutParams.height = secondaryHeight
        } else {

            if(bottomYMain > screenHeight - closedHeight) {
                secondaryView.layoutParams.height = secondaryHeight
            } else {
                secondaryView.layoutParams.height = closedHeight
            }

        }
    }

    override fun followFinger(rawY: Int) {
        val totalHeightDiff = rawY - yStart

        val params = mainView.layoutParams
        params.height = currentHeight + totalHeightDiff.toInt()
        mainView.layoutParams = params
    }

    override fun convertDirection(direction: Boolean): Boolean {
        return !direction
    }

    override fun getEvaluator(direction: Boolean): SlideEval {
        return TopSlideHeightEval(mainView, secondaryView, direction, closedHeight)
    }
}