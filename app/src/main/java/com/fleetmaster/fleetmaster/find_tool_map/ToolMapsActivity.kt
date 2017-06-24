package com.fleetmaster.fleetmaster.find_tool_map

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import com.fleetmaster.fleetmaster.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.activity_tool_maps.*


class ToolMapsActivity : AppCompatActivity(), OnMapReadyCallback {

    companion object {
        fun startActivity(lat: Double, long: Double, context: Context) {
            val intent = Intent(context, ToolMapsActivity::class.java)
            intent.putExtra("lat", lat)
            intent.putExtra("long", long)
            context.startActivity(intent)
        }
    }


    var lat: Double = 0.0
    var long: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tool_maps)

        lat = intent.getDoubleExtra("lat", 0.0)
        long = intent.getDoubleExtra("long", 0.0)
        if(lat == 0.0 || long == 0.0) {
            Toast.makeText(this, "INVALIDE COORDIANTES", Toast.LENGTH_LONG).show()
        }

        navigate_button.setOnClickListener {
            navigateToLocation()
        }


        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(p0: GoogleMap?) {
        val toolLocation = LatLng(lat, long)
        p0?.addMarker(MarkerOptions().position(toolLocation)
                .title("Hier ist dein Gegenstand"))
        p0?.moveCamera(CameraUpdateFactory.newLatLngZoom(toolLocation, 10f))

    }

    private fun navigateToLocation() {
        val gmmIntentUri = Uri.parse("google.navigation:q=$lat,$long%m=d")

        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.`package` = "com.google.android.apps.maps"

        if (mapIntent.resolveActivity(packageManager) != null) {
            startActivity(mapIntent)
        }
    }
}
