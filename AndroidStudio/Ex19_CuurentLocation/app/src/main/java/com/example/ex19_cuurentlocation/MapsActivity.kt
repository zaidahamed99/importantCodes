package com.example.ex19_cuurentlocation

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.ex19_cuurentlocation.databinding.ActivityMapsBinding

//First, import API KEY
//UPDATE PERMISSIONS IN MANIFEST FILE
//ALSO CHECK META DATA CODE IN MANIFEST FILE
//ALSO CHECK IMPLEMENTATION IN BUILD GRADLE
class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private var locationManager: LocationManager? = null
    private var locationListener: LocationListener? = null
    private var currentLatLng: LatLng? = null
    private var gpsBtn: Button? = null
    private var mapbtn: Button? = null
    private var latText: TextView? = null
    private var lonText: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        val textView = findViewById<TextView>(R.id.textView)
        mapbtn = findViewById(R.id.mapBtn)
        gpsBtn = findViewById(R.id.gpsBtn)
        latText = findViewById(R.id.latText)
        lonText = findViewById(R.id.lonText)


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        locationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                textView.text = location.latitude.toString() + " , " + location.longitude.toString()
                currentLatLng = LatLng(location.latitude, location.longitude)
            }

            override fun onProviderDisabled(provider: String) {
                super.onProviderDisabled(provider)
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        }
        requestLocation()
    }

    private fun requestLocation() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                //requestPermissions(arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.INTERNET), 10)
                requestPermissions(arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION), 10)
            }
            return
        }
        locationManager!!.requestLocationUpdates("gps", 5000, 0F, locationListener!!)
        gpsBtn!!.setOnClickListener {
            if (currentLatLng != null){
                latText!!.setText(currentLatLng!!.latitude.toString())
                lonText!!.setText((currentLatLng!!.longitude.toString()))
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            10 -> requestLocation()
            else -> {
                //Log.d("PermissionResult", "Fail request code")
            }
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

        mapbtn!!.setOnClickListener {
            mMap.addMarker(MarkerOptions().position(currentLatLng).title(currentLatLng.toString()))
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 8F))
        }
    }


}