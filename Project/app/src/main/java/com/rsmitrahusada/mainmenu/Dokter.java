package com.rsmitrahusada.mainmenu;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.rsmitrahusada.adapter.DokterAdapter;
import com.rsmitrahusada.model.DokterModel;
import com.rsmitrahusada.R;

public class Dokter extends AppCompatActivity {


    RecyclerView recyclerView;
    DokterAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dokter);

        recyclerView = (RecyclerView)findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<DokterModel> options =
                new FirebaseRecyclerOptions.Builder<DokterModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("JadwalDokter"), DokterModel.class)
                        .build();

        mainAdapter = new DokterAdapter(options);
        recyclerView.setAdapter(mainAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mainAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainAdapter.stopListening();
    }
}

