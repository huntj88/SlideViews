package me.jameshunt.slideviews

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import kotlinx.android.synthetic.main.bottom_slide.*
import android.animation.ValueAnimator
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.top_slide.*
import me.jameshunt.slideviews.slide.data.SlideData
import me.jameshunt.slideviews.slide.data.SlideDataBottom
import me.jameshunt.slideviews.slide.data.SlideDataTop


class MainActivity : AppCompatActivity() {

    private lateinit var bottomSlideData: SlideData
    private lateinit var topSlideData: SlideData

    private var directionDown = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomSlideData = SlideDataBottom(mainView = slide_view_bottom, secondaryView = slide_view_top)
        topSlideData = SlideDataTop(mainView = slide_view_top, secondaryView = slide_view_bottom)


        slide_handle.setOnTouchListener { view, motionEvent ->
            touchListener(motionEvent, bottomSlideData)
            true
        }

        slide_handle_top.setOnTouchListener { view, motionEvent ->
            touchListener(motionEvent, topSlideData)
            true
        }
    }


    private fun touchListener(motionEvent: MotionEvent, slideData: SlideData) {
        when (motionEvent.action) {
            MotionEvent.ACTION_DOWN -> {

                slideData.yStart = motionEvent.rawY
                slideData.lastY = motionEvent.rawY
                slideData.currentHeight = slideData.mainView.height

            }
            MotionEvent.ACTION_MOVE -> {

                slideData.followFinger(motionEvent.rawY.toInt())
                slideData.fixOverlap()

                directionDown = motionEvent.rawY > slideData.lastY
                slideData.lastY = motionEvent.rawY

            }
            MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_UP -> {

                val startHeight = slideData.mainView.height
                val endHeight = if (slideData.convertDirection(directionDown)) slideData.closedHeight else main_frame.height

                val animation = ValueAnimator.ofObject(
                        slideData.getEvaluator(directionDown),
                        startHeight,
                        endHeight).setDuration(300)

                animation.start()
            }
            else -> {}
        }
    }
}