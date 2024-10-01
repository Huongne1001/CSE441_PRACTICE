package com.example.recyclerviewdemo;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class LandMarkAdapter extends RecyclerView.Adapter<LandMarkAdapter.LandmarkViewHolder> {


    @NonNull
    @Override
    public LandMarkAdapter.LandmarkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull LandMarkAdapter.LandmarkViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class LandmarkViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        LandmarkViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView2);
            textView = itemView.findViewById(R.id.txtCountry);
        }
    }

}
