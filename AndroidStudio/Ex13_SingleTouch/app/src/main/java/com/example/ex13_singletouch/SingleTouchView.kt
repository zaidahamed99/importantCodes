package com.example.ex13_singletouch

import android.content.Context
import android.graphics.*
import android.view.MotionEvent
import android.view.View

class SingleTouchView(context: Context): View(context) {    //AFTER COLON : WAS SOMETHING THAT WOULD BE RETURNED
    private val paint= Paint()
    private val path = Path()
    //TO CREATE THE X-Y POSITION OF THE FINGER ON THE SCREEN
    private var evenX: Float = 0F
    private var evenY: Float = 0F
    private var fingerDown = false  //CHECKS IF YOUR FINGER IS TOUCHING THE SCREEN OR NOT

    //INITIALIZATION
    init {
        paint.isAntiAlias = true
        paint.strokeWidth = 6F
        paint.strokeJoin = Paint.Join.ROUND
        paint.color = Color.BLACK
        paint.style = Paint.Style.STROKE
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        evenX = event!!.x
        evenY = event!!.y

        when(event.action){
            MotionEvent.ACTION_DOWN -> {
                fingerDown = true
                path.moveTo(evenX,evenY)
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                path.lineTo(evenX,evenY)    //WHEN YOU MOVE YOUR FINGER ON THE SCREEM, X AND Y KEEPS UPDATING AND IT DRAWS A LINE
            }
            MotionEvent.ACTION_UP -> {
                fingerDown = false
            }
            else -> return false
        }
        invalidate()    //JUMP TO THE ON DRAW TO DRAW SOMETHING THAT WE DEFINE
        return super.onTouchEvent(event)
    }

    //THIS FUNCTION SHAPES OUT THE THING WE DRAW. MEANS IT WILL MAKE A LINE STRAIGH OR WILL MAKE A CIRCLE ROUND
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas!!.drawPath(path, paint)
        if (fingerDown){
            canvas.drawCircle(evenX, evenY, 10F, paint)
        }
    }

}
