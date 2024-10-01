package com.example.recyclerviewdemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class IntroSlideAdapter extends RecyclerView.Adapter<IntroSlideAdapter.IntroSlideViewHolder> {
    private List<IntroSlide> introSlides;
    public IntroSlideAdapter(List<IntroSlide> introSlides) {
        this.introSlides = introSlides;
    }
    @NonNull
    @Override
    public IntroSlideViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_intro_slide, parent, false);
        return new IntroSlideViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull IntroSlideViewHolder holder, int position) {
        IntroSlide slide = introSlides.get(position);
        holder.image.setImageResource(slide.imageResId);
        holder.title.setText(slide.title);
        holder.description.setText(slide.description);
    }
    @Override
    public int getItemCount() {
        return introSlides.size();
    }
    static class IntroSlideViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        TextView description;
        IntroSlideViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
        }
    }