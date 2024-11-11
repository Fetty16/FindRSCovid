package com.example.findrs.view.activities

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.findrs.R
import com.example.findrs.utils.Constant
import com.example.findrs.view.adapter.DetailPagerAdapter
import com.example.findrs.viewmodel.PrimaryViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.findrs.databinding.ActivityDetailBinding

class DetailHospitalsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var primaryViewModel: PrimaryViewModel
    private lateinit var googleMaps: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mengatur status bar
        setStatusBar()

        // Mengatur toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        // Inisialisasi peta
        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)

        // Mengatur ViewPager dan TabLayout
        binding.viewPager.adapter = DetailPagerAdapter(supportFragmentManager)
        binding.viewPager.offscreenPageLimit = 2
        binding.tabsLayout.setupWithViewPager(binding.viewPager)

        // Mengatur Floating Action Button untuk panggilan telepon
        binding.fabPhone.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${Constant.phoneNumber}"))
            startActivity(intent)
        }
    }

    override fun onMapReady(gMapReady: GoogleMap) {
        // Inisialisasi ViewModel
        primaryViewModel = ViewModelProvider(this).get(PrimaryViewModel::class.java)
        primaryViewModel.setLocation(Constant.hospitalId)
        primaryViewModel.getLocation().observe(this, { modelData ->
            modelData?.let {
                // Mendapatkan LatLong
                val strLatitude = it.lat
                val strLongitude = it.long
                googleMaps = gMapReady
                val latLng = LatLng(strLatitude.toDouble(), strLongitude.toDouble())
                googleMaps.addMarker(MarkerOptions().position(latLng).title(Constant.hospitalName))
                googleMaps.moveCamera(CameraUpdateFactory.newLatLng(latLng))
                googleMaps.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14f))
                googleMaps.uiSettings.setAllGesturesEnabled(true)
                googleMaps.uiSettings.isZoomGesturesEnabled = true
                googleMaps.isTrafficEnabled = true
            }
        })
    }

    private fun setStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            window.statusBarColor = Color.TRANSPARENT
        }
    }

    companion object {
        fun setWindowFlag(activity: Activity, bits: Int, on: Boolean) {
            val window = activity.window
            val layoutParams = window.attributes
            if (on) {
                layoutParams.flags = layoutParams.flags or bits
            } else {
                layoutParams.flags = layoutParams.flags and bits.inv()
            }
            window.attributes = layoutParams
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}