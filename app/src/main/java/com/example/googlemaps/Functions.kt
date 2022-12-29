package com.example.googlemaps

import android.content.Context
import android.graphics.Color
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.BitmapDescriptorFactory.HUE_VIOLET
import com.google.android.gms.maps.model.BitmapDescriptorFactory.defaultMarker
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.data.geojson.GeoJsonLayer
import com.google.maps.android.data.geojson.GeoJsonPointStyle


object Functions {

   fun initMap(mMap:GoogleMap, googleMap: GoogleMap){

        val cadiz = LatLng(36.517676, -6.276978)
        mMap.addMarker(
            MarkerOptions()
            .position(cadiz)
            .title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(cadiz))

       googleMap.apply {
           mapType = GoogleMap.MAP_TYPE_HYBRID
           moveCamera(CameraUpdateFactory.zoomTo(13F))
           isTrafficEnabled = true
           uiSettings.isZoomControlsEnabled = true
           uiSettings.isCompassEnabled = true
       }
     }

    fun loadCarrilBici(mMap:GoogleMap, context: Context){
        val carrilBici = GeoJsonLayer(mMap, R.raw.carril_bici, context)
        val styleCarril = carrilBici.defaultLineStringStyle
        styleCarril.color = Color.GREEN
        carrilBici.addLayerToMap()
    }

    fun loadAparcaBicis(mMap:GoogleMap, context: Context){
        val aparcabicis = GeoJsonLayer(mMap, R.raw.aparcabicis, context)
        for (feature in aparcabicis.features) {

            if (feature.getProperty("name") != null) {
                val name = feature.getProperty("name")
                val pointIcon = defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)
                val pointStyle = GeoJsonPointStyle()
                pointStyle.icon = pointIcon
                pointStyle.title = name
                feature.pointStyle = pointStyle
            }
        }
        aparcabicis.addLayerToMap()
    }

    fun loadFuentes(mMap:GoogleMap, context: Context){
        val fuentes = GeoJsonLayer(mMap, R.raw.fuentes, context)
        for (feature in fuentes.features) {

            if (feature.getProperty("type") != null) {
                val name = feature.getProperty("type")
                val pointIcon = defaultMarker(BitmapDescriptorFactory.HUE_AZURE)
                val pointStyle = GeoJsonPointStyle()
                pointStyle.icon = pointIcon
                pointStyle.title = name
                feature.pointStyle = pointStyle
            }
        }
        fuentes.addLayerToMap()
    }
}