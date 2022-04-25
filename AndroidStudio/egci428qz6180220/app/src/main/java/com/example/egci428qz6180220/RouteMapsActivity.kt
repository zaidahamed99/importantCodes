package com.example.egci428qz6180220

import android.content.Context
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.PolylineOptions
import java.util.*

class RouteMapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private var mMap: GoogleMap? = null
    private var latLng: LatLng? = null

    private var sensorManager: SensorManager? = null
    private var lastUpdate: Long = 0
    private var view: View? = null
    private var color = false
    private var countMarker = 0
    private var previousLatLng = LatLng(50.0,50.0)
    lateinit var colorBtn: Button
    lateinit var addBtn: Button
    lateinit var latTextView: TextView
    lateinit var lonTextView: TextView




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_route_maps)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        latTextView = findViewById<TextView>(R.id.latTextView)
        lonTextView = findViewById<TextView>(R.id.lonTextview)
        addBtn = findViewById<Button>(R.id.addBtn)
        colorBtn = findViewById(R.id.colorBtn)

        /* (3.1) Set Lat/Lon values receiving from textViews.*/
        val point1: Double = latTextView.getText().toString().toDouble()
        val point2: Double = lonTextView.getText().toString().toDouble()
        latLng = LatLng(point1, point2)

            view = findViewById(R.id.colorBtn)
        view!!.setBackgroundColor(Color.RED)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        lastUpdate = System.currentTimeMillis()

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


        colorBtn.setOnClickListener {
            /* (3.2) Switch background color of addBtn between red and green color */
            if (color==false) {
                colorBtn.setBackgroundColor(Color.RED)
            } else {
                colorBtn.setBackgroundColor(Color.GREEN)
            }
            color = !color

            /* (3.2) call function getMarker to random location */
            /*..........(3.2)..............*/
        }
        addBtn.setOnClickListener {
            /* (3.3) If the colorBtn is red then add the red marker else add the green marker to the Lat/Lon location from textViews. */
            if(color==false) {
                mMap?.addMarker(MarkerOptions()
                    .position(latLng!!)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                )
            }
            else {
                mMap?.addMarker(MarkerOptions()
                    .position(latLng!!)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                )
            }

            /* (3.4) Move the map camera to the last Lat/Lon location and set the map's Zoom level = 5.*/
            mMap?.moveCamera(CameraUpdateFactory.newLatLng(latLng!!))
            mMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng!!, 5F))

            /* (3.5) If there are more than one marker, draw the black line connecting between 2 markers*/
            if(countMarker>0){
                mMap?.addPolyline(
                    PolylineOptions()
                        .add(latLng, previousLatLng)
                        .width(5F)
                        .color(Color.DKGRAY))
            }
            countMarker = countMarker++
                previousLatLng = latLng!!
        }

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
    }



    private fun getMarker(){
        latLng = randomLocation(latLng!!)
        latTextView.setText(latLng!!.latitude.toString())
        lonTextView.setText(latLng!!.longitude.toString())
    }

    private fun randomLocation(currntLatLng: LatLng): LatLng{
        var randLat = (Random().nextInt(170)-85).toDouble()
        var randLon = (Random().nextInt(180)-360).toDouble()
        return LatLng(randLat, randLon)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId()
        if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

}