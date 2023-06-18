package com.rsmitrahusada.mainmenu;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import com.rsmitrahusada.R;
public class TentangKami extends AppCompatActivity {

    private ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tentangkami);

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
