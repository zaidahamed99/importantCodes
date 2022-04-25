package com.example.egci428qz6180220

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.example.egci428qz6180220.Models.APoint
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions

class RouteViewActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private var latLng: LatLng? = null
    private var routeName: String? = null

    lateinit var pointList: MutableList<APoint>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_route_view)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        // (2.1) Get the route name received from MainActivity
        routeName = intent.getStringExtra("selRoute")



        /*(2.1) Set the value of routeName received from MainActivity*/
        val bundle = Bundle()
            if(bundle!=null){
                var routeName = intent.getStringExtra("selRoute")

                /*(2.2) Set the values of point1Lat, point1Lon, point2Lat, and point2Lon receiving from MainActivity*/
                var point1Lat = intent.getDoubleExtra("point1lat", 100.0)
                var point1Lon = intent.getDoubleExtra("point1lon", 100.0)
                var point2Lat = intent.getDoubleExtra("point2lat", 100.0)
                var point2Lon = intent.getDoubleExtra("point2lon", 100.0)

                    /*(2.3) Set and Add the values of these points in (2.2) to pointList.*/
                pointList = mutableListOf()
                pointList.add(APoint(point1Lat, point1Lon))
                pointList.add(APoint(point2Lat, point2Lon))
            }


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        /*(2.4) Set up the mapFragment.*/
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
            mapFragment.getMapAsync(this)
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


        if(pointList.isNotEmpty()) {
            var i = 0
            /*(2.5) Plot the 2 markers on maps according to pointList.*/
            var point: LatLng? = null
            var point2: LatLng? = null
            for(i in 0..1) {

                point = (LatLng(pointList[0].lat, pointList[0].lon))
                mMap.addMarker(
                    MarkerOptions()
                    .position(LatLng(point.latitude, point.longitude)))

                point2 = (LatLng(pointList[1].lat, pointList[1].lon))
                mMap.addMarker(
                    MarkerOptions()
                        .position(LatLng(point.latitude, point.longitude)))

                Log.d("Marker", point.toString())
                Log.d("Marker added: ", i.toString())
            }
            /*(2.6) Draw the line between markers according to the pointList.*/
            mMap.addPolyline(
                PolylineOptions()
                    .add(point, point2)
                    .width(5F)
                    .color(Color.DKGRAY))


            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(point!!, 5f))
        } else{
            Log.d("Marker added: ", "Zero")
        }
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