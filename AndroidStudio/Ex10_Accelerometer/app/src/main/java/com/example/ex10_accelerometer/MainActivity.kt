package com.example.ex10_accelerometer

import android.content.Context
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity(), SensorEventListener {
    private var sensorManager: SensorManager? = null
    private var view: View? = null
    private var lastUpdate: Long = 0
    private var changeColor: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.textView)
        textView.setBackgroundColor(Color.BLUE)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        lastUpdate = System.currentTimeMillis()

    }

    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.type == Sensor.TYPE_ACCELEROMETER){
            getAccelerometer(event)
        }
    }

    private fun getAccelerometer(event: SensorEvent){
        val textView = findViewById<TextView>(R.id.textView)
        val values = event.values

        val x = values[0]
        val y = values[1]
        val z = values[2]

        //To know how fast is your mobile device is moving
        val accel = (x*x + y*y + z*z)/(SensorManager.GRAVITY_EARTH*SensorManager.GRAVITY_EARTH)
        val actualTime = System.currentTimeMillis()

        if (accel>=2){
            if (actualTime-lastUpdate < 200){
                return
            }
            lastUpdate = actualTime
            Toast.makeText(this, "Device was shuffled", Toast.LENGTH_SHORT).show()
            if (changeColor){
                textView.setBackgroundColor(Color.BLUE)
            } else{
                textView.setBackgroundColor(Color.YELLOW)
            }
            changeColor = !changeColor
        }

    }

    override fun onAccuracyChanged(event: Sensor?, p1: Int) {

    }

    override fun onResume() {
        super.onResume()
        sensorManager!!.registerListener(this, sensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
        SensorManager.SENSOR_DELAY_FASTEST)
    }

    override fun onPause() {
        super.onPause()
        sensorManager!!.unregisterListener(this)
    }


}