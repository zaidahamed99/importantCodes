package com.example.ex17_map

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.ex17_map.databinding.ActivityMapsBinding
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.PolylineOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
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

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        val mahidol = LatLng(13.7934, 100.3225)

        mMap.addMarker(MarkerOptions().position(mahidol).title("Marker in Mahidol"))
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(mahidol))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mahidol, 8F))

        mMap.setOnMapLongClickListener {
                latLng -> mMap.addMarker(
            MarkerOptions().position(latLng)
                .title(latLng.toString())
        )
        }

        mMap.setOnMapClickListener {
                latLang -> mMap.animateCamera(CameraUpdateFactory.newLatLng(latLang))
        }

        mMap.addMarker(MarkerOptions()
            .position(LatLng(15.4231, 100.45612))
            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
            .title("Color Marker")
            .snippet("Hello World!")
        )

        mMap.addMarker(MarkerOptions()
            .position(LatLng(15.0, 100.45612))
            .icon(BitmapDescriptorFactory.fromResource(R.drawable.image10101))
            .title("Custom Marker")
            .snippet("Hello World!")
        )

        mMap.addPolyline(
            PolylineOptions()
            .add(LatLng(15.0, 100.45612), LatLng(15.4231, 100.45612),LatLng(15.5, 100.7))
            .width(5F)
            .color(Color.DKGRAY)
        )

        /*mMap.addPolygon(PolygonOptions()
            .add(LatLng(15.0, 100.45612), LatLng(15.4231, 100.45612),LatLng(15.5, 100.7), LatLng(15.0, 100.45612))
            .strokeColor(Color.DKGRAY)
            .fillColor(Color.YELLOW)
        )*/

    }
}