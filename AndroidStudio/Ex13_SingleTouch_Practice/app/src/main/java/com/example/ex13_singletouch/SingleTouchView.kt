package com.example.ex13_singletouch

import android.content.Context
import android.graphics.*
import android.view.MotionEvent
import android.view.View
import java.util.*
import kotlin.collections.ArrayList

class SingleTouchView(context: Context): View(context) {    //AFTER COLON : WAS SOMETHING THAT WOULD BE RETURNED
    //private val paint= Paint()
    private val paint1= Paint()
    private val paint2= Paint()
    private val paint3= Paint()
    //private val path = Path()
    private val path1 = Path()
    private val path2 = Path()
    private val path3 = Path()
    //TO CREATE THE X-Y POSITION OF THE FINGER ON THE SCREEN
    private var evenX: Float = 0F
    private var evenY: Float = 0F
    private var fingerDown = false  //CHECKS IF YOUR FINGER IS TOUCHING THE SCREEN OR NOT
    var colorControl = 1

    //INITIALIZATION
    init {
        paint1.isAntiAlias = true
        paint1.strokeWidth = 6F
        paint1.strokeJoin = Paint.Join.ROUND
        paint1.color = Color.BLACK
        paint1.style = Paint.Style.STROKE

        paint2.isAntiAlias = true
        paint2.strokeWidth = 6F
        paint2.strokeJoin = Paint.Join.ROUND
        paint2.color = Color.BLUE
        paint2.style = Paint.Style.STROKE

        paint3.isAntiAlias = true
        paint3.strokeWidth = 6F
        paint3.strokeJoin = Paint.Join.ROUND
        paint3.color = Color.RED
        paint3.style = Paint.Style.STROKE
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        evenX = event!!.x
        evenY = event!!.y

        when(event.action){
            MotionEvent.ACTION_DOWN -> {
                fingerDown = true
                //path.moveTo(evenX,evenY)
                if (colorControl==1){
                    path1.moveTo(evenX,evenY)
                }
                if (colorControl==2){
                    path2.moveTo(evenX,evenY)
                }
                if (colorControl==3){
                    path3.moveTo(evenX,evenY)
                }
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                //path.lineTo(evenX,evenY)    //WHEN YOU MOVE YOUR FINGER ON THE SCREEM, X AND Y KEEPS UPDATING AND IT DRAWS A LINE
                if (colorControl==1){
                    path1.lineTo(evenX,evenY)
                }
                if (colorControl==2){
                    path2.lineTo(evenX,evenY)
                }
                if (colorControl==3){
                    path3.lineTo(evenX,evenY)
                }
            }
            MotionEvent.ACTION_UP -> {
                fingerDown = false
                if (colorControl==3){
                    colorControl=1
                }
                else {
                    colorControl++
                }
            }
            else -> return false
        }
        invalidate()    //JUMP TO THE ON DRAW TO DRAW SOMETHING THAT WE DEFINE
        return super.onTouchEvent(event)
    }

    //THIS FUNCTION SHAPES OUT THE THING WE DRAW. MEANS IT WILL MAKE A LINE STRAIGH OR WILL MAKE A CIRCLE ROUND
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas!!.drawPath(path1, paint1)
        canvas!!.drawPath(path2, paint2)
        canvas!!.drawPath(path3, paint3)
    }
}
