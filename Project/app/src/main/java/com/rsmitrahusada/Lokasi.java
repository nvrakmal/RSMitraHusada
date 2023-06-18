package com.rsmitrahusada;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

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
        mapView.getController().setZoom(20.0);

        // Tambahkan marker untuk lokasi
        GeoPoint lokasi = new GeoPoint(-5.35862, 104.99164); // Koordinat latitude dan longitude
        Drawable icon = getResources().getDrawable(R.drawable.ic_location); // Menggunakan ikon kustom (misalnya ic_location.png di folder res/drawable)
        icon.setBounds(0, 0, icon.getIntrinsicWidth() * 2, icon.getIntrinsicHeight() * 2); // Memperbesar ukuran ikon
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
    }

    private void openGoogleMaps() {
        // Ganti dengan koordinat latitude dan longitude RS Mitra Husada Pringsewu
        double latitude = -7.8649;
        double longitude = 111.2635;
        String label = "RS Mitra Husada Pringsewu";
        String uri = "geo:" + latitude + "," + longitude + "?q=" + latitude + "," + longitude + "(" + label + ")";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        intent.setPackage("com.google.android.apps.maps");
        startActivity(intent);
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
