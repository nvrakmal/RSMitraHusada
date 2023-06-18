package com.rsmitrahusada.mainmenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.rsmitrahusada.R;

public class Sarana extends AppCompatActivity {
    private ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sarana);

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Panggil fungsi onBackPressed() saat tombol kembali ditekan
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish(); // Kembali ke aktivitas sebelumnya
    }

}
