package com.rsmitrahusada;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.rsmitrahusada.adapter.SliderAdapter;
import com.rsmitrahusada.mainmenu.Dokter;
import com.rsmitrahusada.mainmenu.Layanan;
import com.rsmitrahusada.mainmenu.Lokasi;
import com.rsmitrahusada.mainmenu.Pendaftaran;
import com.rsmitrahusada.mainmenu.Sarana;
import com.rsmitrahusada.mainmenu.TentangKami;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private Handler handler;
    private ViewPager viewPager;
    private SliderAdapter sliderAdapter;
    private LinearLayout indicatorLayout;
    private final long DELAY_MS = 3000; // Waktu jeda antara perpindahan halaman
    private final long PERIOD_MS = 3000; // Waktu interval perpindahan halaman

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.view_pager);
        sliderAdapter = new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);
        indicatorLayout = findViewById(R.id.indicator_Layout);
        sliderAdapter.setIndicatorLayout(indicatorLayout);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                sliderAdapter.setPrimaryItem(viewPager, position, null);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        // Inisialisasi handler
        handler = new Handler();
        startSliderAutoSwipe();
    }

    private void startSliderAutoSwipe() {
        final Runnable update = new Runnable() {
            public void run() {
                int currentPage = viewPager.getCurrentItem();
                int totalItems = viewPager.getAdapter().getCount();

                // Menghitung indeks slide berikutnya
                int nextPage = (currentPage + 1) % totalItems;

                viewPager.setCurrentItem(nextPage, true);

                // Memanggil method ini kembali setelah periode waktu tertentu
                handler.postDelayed(this, PERIOD_MS);
            }
        };

        // Memulai otomatis perpindahan slide
        handler.postDelayed(update, DELAY_MS);
    }

    private void stopSliderAutoSwipe() {
        // Menghentikan otomatis perpindahan slide
        handler.removeCallbacksAndMessages(null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopSliderAutoSwipe();
    }


    public void Layanan(View view) {
        startActivity(new Intent(MainActivity.this, Layanan.class));
    }

    public void Dokter(View view) {
        startActivity(new Intent(MainActivity.this, Dokter.class));
    }

    public void Sarana(View view) {
        startActivity(new Intent(MainActivity.this, Sarana.class));
    }

    public void Lokasi(View view) {
        startActivity(new Intent(MainActivity.this, Lokasi.class));
    }

    public void Pendaftaran(View view) { startActivity(new Intent(MainActivity.this, Pendaftaran.class)); }

    public void TentangKami(View view) { startActivity(new Intent(MainActivity.this, TentangKami.class)); }


    public void Alamat(View view) {
        openGoogleMaps();
    }

    public void openGoogleMaps() {
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

    public void Email(View view) {
        String email = "cs@mitrahusadapringsewu.com";
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:" + email));
        startActivity(intent);
    }

    public void Phone(View view) {
        String phoneNumber = "+6282281666655";
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(intent);
    }

    public void Facebook(View view) {
        redirectToWebsite("https://web.facebook.com/rsmitra.husada.7?_rdc=1&_rdr");
    }

    public void Instagram(View view) {
        redirectToWebsite("https://www.instagram.com/rs.mitrahusada/");
    }

    public void Youtube(View view) {
        redirectToWebsite("https://www.youtube.com/channel/UCrjPtLtJGYYLx3EO7wd7g-g?view_as=subscriber");
    }

    private void redirectToWebsite(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}