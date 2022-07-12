package com.example.maptmockapplication

import android.app.ActionBar
import android.content.Intent
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE)
        setContentView(R.layout.activity_main)
        val button = findViewById<View>(R.id.buttonSwap)

        button.setOnClickListener {
            val intent = Intent(this, SwapActivity::class.java)
            startActivity(intent)
        }
        val buttondb = findViewById<View>(R.id.buttonDB)
        val dbHandler = DBHandler(this);
        buttondb.setOnClickListener {
            val textDescription = "description_test"
            val textName = "name_test"
            dbHandler.addNewRaw(textName, textDescription)
        }
        val buttonmap = findViewById<View>(R.id.buttonMAPS)
        buttonmap.setOnClickListener {
            val lm = getSystemService(LOCATION_SERVICE) as LocationManager
            val location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            val longitude = location!!.longitude
            val latitude = location!!.latitude
            Toast.makeText(this, "LONG:" + longitude, Toast.LENGTH_SHORT).show()
            Toast.makeText(this, "LAT:" + latitude, Toast.LENGTH_SHORT).show()
            val uri: String = java.lang.String.format(Locale.ENGLISH, "geo:%f,%f", latitude, longitude)
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
            startActivity(intent)
        }

    }
}