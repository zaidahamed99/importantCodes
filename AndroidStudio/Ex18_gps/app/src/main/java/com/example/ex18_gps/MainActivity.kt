package com.example.ex18_gps

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
import java.util.jar.Manifest

//Check android manifest first, first grant permission
class MainActivity : AppCompatActivity() {
    private var locationManager: LocationManager? = null
    private var locationListener: LocationListener? = null
    private var button: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textview = findViewById<TextView>(R.id.textView)
        button = findViewById(R.id.button)

        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        locationListener = object : LocationListener{
            override fun onLocationChanged(location: Location) {
                textview.append("\n"+location.latitude + " , " + location.longitude)
            }

            override fun onProviderDisabled(provider: String) {
                super.onProviderDisabled(provider)
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        }
        request_location()
    }

    private fun request_location() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                    {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                        //requestPermissions(arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.INTERNET), 10)
                        requestPermissions(arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION), 10)
                    }
                    return
                }
        button!!.setOnClickListener {
            locationManager!!.requestLocationUpdates("gps", 5000, 0F, locationListener!!)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            10 -> request_location()
            else -> {
                Log.d("PermissionResult", "Fail request code")
            }
        }
    }
}

