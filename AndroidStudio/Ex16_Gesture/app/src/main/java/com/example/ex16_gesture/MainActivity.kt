package com.example.ex16_gesture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.TextView
import androidx.core.view.GestureDetectorCompat

class MainActivity : AppCompatActivity(), GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {
    private val MIN_SWIPE_DISTANCE_X = 100
    private val MAX_SWIPE_DISTANCE_X = 1000

    private val MIN_SWIPE_DISTANCE_Y = 100
    private val MAX_SWIPE_DISTANCE_Y = 1000

    private var statusMsg: TextView? = null
    private var gestureDetector: GestureDetectorCompat? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        statusMsg = findViewById(R.id.statusMsg)
        this.gestureDetector = GestureDetectorCompat(this, this)
        gestureDetector!!.setOnDoubleTapListener(this)

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        this.gestureDetector!!.onTouchEvent(event)
        return super.onTouchEvent(event)
    }

    override fun onDown(event: MotionEvent?): Boolean {
       statusMsg!!.text = "On Down"
        return true
    }

    override fun onShowPress(event: MotionEvent?) {
        statusMsg!!.text = "On Show Press"
    }

    override fun onSingleTapUp(event: MotionEvent?): Boolean {
        statusMsg!!.text = "On Single Tap up"
        return true
    }

    override fun onScroll(event1: MotionEvent?, event2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
        statusMsg!!.text = "On Scroll"
        return true
    }

    override fun onLongPress(event: MotionEvent?) {
        statusMsg!!.text = "On Long Press"
    }

    override fun onFling(event1: MotionEvent, event2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
        val deltaX = event1.x - event2.x
        val deltaY = event1.y - event2.y

        val deltaX_Abs = Math.abs(deltaX)
        val deltaY_Abs = Math.abs(deltaY)

        if ((deltaX_Abs >= MIN_SWIPE_DISTANCE_X) && (deltaX_Abs<=MAX_SWIPE_DISTANCE_X)){
            if (deltaX>0){
                statusMsg!!.text = "Swipe to Left"
            }
            else{
                statusMsg!!.text = "Swipe to Right"
            }
        }

        if ((deltaY_Abs >= MIN_SWIPE_DISTANCE_Y) && (deltaY_Abs<=MAX_SWIPE_DISTANCE_Y)){
            if (deltaY>0){
                statusMsg!!.text = "Swipe to Up"
            }
            else{
                statusMsg!!.text = "Swipe to Down"
            }
        }


        return true
    }

    override fun onSingleTapConfirmed(event: MotionEvent?): Boolean {
        statusMsg!!.text = "On Single Tap Confirmed"
        return true
    }

    override fun onDoubleTap(event: MotionEvent?): Boolean {
        statusMsg!!.text = "On Double Tap"
        return true
    }

    override fun onDoubleTapEvent(event: MotionEvent?): Boolean {
        statusMsg!!.text = "On Double Tap Event"
        return true
    }
}