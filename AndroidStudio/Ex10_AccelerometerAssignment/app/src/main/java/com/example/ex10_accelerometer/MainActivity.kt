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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

        //Getting random number
        val rnds = (0..10).random()

        if (accel>=2){
            if (actualTime-lastUpdate < 200){
                return
            }
            lastUpdate = actualTime
            textView.setText(rnds.toString())
            if (rnds==5){
                Toast.makeText(this, "You Win", Toast.LENGTH_SHORT).show()
            }
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