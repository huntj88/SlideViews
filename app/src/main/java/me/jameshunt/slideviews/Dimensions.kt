package me.jameshunt.slideviews

import android.content.res.Resources

/**
 * Created by James on 10/4/2017.
 */
class Dimensions {

    companion object {
        fun dpToPx(dp: Int): Float {
            return (dp * Resources.getSystem().displayMetrics.density)
        }
    }
}