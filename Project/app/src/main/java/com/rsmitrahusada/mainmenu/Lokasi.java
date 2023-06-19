package com.rsmitrahusada.mainmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.rsmitrahusada.R;
import com.rsmitrahusada.utils.Utils;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;
import java.util.Collections;

public class Lokasi extends AppCompatActivity {

    private MapView mapView;
    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lokasi);

        // Inisialisasi konfigurasi osmdroid
        Configuration.getInstance().load(getApplicationContext(), getPreferences(MODE_PRIVATE));

        // Inisialisasi MapView
        mapView = findViewById(R.id.map_view);
        mapView.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE);
        mapView.setMultiTouchControls(true);
        mapView.getController().setZoom(19.0);

        // Tambahkan marker untuk lokasi
        GeoPoint lokasi = new GeoPoint(-5.35862, 104.99164); // Koordinat latitude dan longitude
        Drawable icon = getResources().getDrawable(R.drawable.ic_location); // Menggunakan ikon kustom (misalnya ic_location.png di folder res/drawable)
        OverlayItem overlayItem = new OverlayItem("RS Mitra Husada", "RS MITRA HUSADA PRINGSEWU", lokasi);
        overlayItem.setMarker(icon);

        ItemizedIconOverlay<OverlayItem> overlay = new ItemizedIconOverlay<>(this, new ArrayList<>(Collections.singletonList(overlayItem)), null);
        overlay.addItem(overlayItem);
        mapView.getOverlays().add(overlay);
        mapView.getController().setCenter(lokasi);

        // Inisialisasi tombol kembali (back button) dan tambahkan listener
        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Panggil fungsi onBackPressed() saat tombol kembali ditekan
            }
        });

        // Periksa koneksi internet
        if (Utils.isInternetConnected(this)) {
            // Jika terhubung ke internet, tampilkan peta
            mapView.setVisibility(View.VISIBLE);
        } else {
            // Jika tidak terhubung ke internet, tampilkan pesan kesalahan
            Toast.makeText(this, "Tidak ada koneksi internet", Toast.LENGTH_SHORT).show();
        }

        // Inisialisasi tombol Lihat di Google Maps dan tambahkan listener
        Button btnGoogleMaps = findViewById(R.id.btn_google_maps);
        btnGoogleMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGoogleMaps();
            }
        });
    }

    private void openGoogleMaps() {
        double latitude = -5.35852;
        double longitude = 104.99075;
        String label = "RS Mitra Husada Pringsewu";
        String uri = "geo:" + latitude + "," + longitude + "?q=" + latitude + "," + longitude + "(" + label + ")";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        intent.setPackage("com.google.android.apps.maps");

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            // Handle jika Google Maps tidak terpasang di perangkat
            // Misalnya, buka browser dengan koordinat yang sama
            String mapUrl = "https://www.google.com/maps/search/?api=1&query=" + latitude + "," + longitude;
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mapUrl));
            startActivity(browserIntent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish(); // Kembali ke aktivitas sebelumnya
    }
}
