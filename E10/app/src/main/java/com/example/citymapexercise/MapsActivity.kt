package com.example.citymapexercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.citymapexercise.databinding.ActivityMapsBinding
import com.google.android.gms.maps.model.Marker

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    override fun onMarkerClick(marker: Marker?): Boolean {
        Toast.makeText(this,marker!!.title,Toast.LENGTH_LONG).show();
        return true
    }

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

        // Add a marker in Dynamo and move the camera
        val helsinki = LatLng(60.192059, 24.945831)
        val tampere = LatLng(61.49911, 23.78712)
        val turku = LatLng(60.451813, 22.266630)
        val oulu = LatLng(65.012615, 25.471453)
        val kuopio = LatLng(62.89238, 27.67703)

        mMap.addMarker(MarkerOptions().position(helsinki).title("Marker in Helsinki"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(helsinki,5.0F))

        mMap.addMarker(MarkerOptions().position(tampere).title("Marker in Tampere"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tampere,5.0F))

        mMap.addMarker(MarkerOptions().position(turku).title("Marker in Turku"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(turku,5.0F))

        mMap.addMarker(MarkerOptions().position(oulu).title("Marker in Oulu"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(oulu,5.0F))

        mMap.addMarker(MarkerOptions().position(kuopio).title("Marker in Kuopio"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(kuopio,5.0F))


        //zoom
        mMap.uiSettings.isZoomControlsEnabled = true
        //listener
        mMap.setOnMarkerClickListener (this)
    }


}