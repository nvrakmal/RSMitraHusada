package com.rsmitrahusada.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.rsmitrahusada.model.DokterModel;
import com.rsmitrahusada.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class DokterAdapter extends FirebaseRecyclerAdapter<DokterModel, DokterAdapter.myViewHolder> {

    private String searchQuery = "";

    public DokterAdapter(@NonNull FirebaseRecyclerOptions<DokterModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull DokterModel model) {
        if (model.getNama().toLowerCase().contains(searchQuery.toLowerCase())) {
            holder.itemView.setVisibility(View.VISIBLE);
            holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            holder.nama.setText(model.getNama());
            holder.spesialis.setText(model.getSpesialis());
            holder.jadwal.setText(model.getJadwal());

            Glide.with(holder.img.getContext())
                    .load(model.getSurl())
//                    .placeholder(R.drawable.placeholder_image)  // Placeholder image resource
                    .circleCrop()
//                    .error(R.drawable.error_image)  // Error image resource
                    .into(holder.img);
        } else {
            holder.itemView.setVisibility(View.GONE);
            holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
        }
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dokter_item, parent, false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder {

        CircleImageView img;
        TextView nama, spesialis, jadwal;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.img1);
            nama = itemView.findViewById(R.id.namatext);
            spesialis = itemView.findViewById(R.id.spesialistext);
            jadwal = itemView.findViewById(R.id.jadwaltext);
        }
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
        notifyDataSetChanged();
    }
}
