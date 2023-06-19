package com.rsmitrahusada.mainmenu;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.rsmitrahusada.R;

public class Pendaftaran extends AppCompatActivity {

    private ImageButton backButton;
    private WebView webView;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendaftaran);

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Panggil fungsi onBackPressed() saat tombol kembali ditekan
            }
        });

        webView = findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://103.126.172.66/pendaftaran-online-web/login");

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                webView.reload(); // Muat ulang halaman web saat melakukan refresh
            }
        });

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                swipeRefreshLayout.setRefreshing(false); // Menghentikan animasi refresh setelah halaman selesai dimuat
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack(); // Kembali ke halaman sebelumnya jika ada
        } else {
            super.onBackPressed();
            finish(); // Kembali ke aktivitas sebelumnya
        }
    }
}
