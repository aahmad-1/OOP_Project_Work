package com.example.oopprojectwork;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oopprojectwork.Lutemon.Lutemon;

import java.util.List;

public class LutemonStatsAdapter extends RecyclerView.Adapter<LutemonStatsAdapter.ViewHolder> {

    private final Context context;
    private final List<Lutemon> lutemonList;

    public LutemonStatsAdapter(Context context, List<Lutemon> lutemonList) {
        this.context = context;
        this.lutemonList = lutemonList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_lutemon_stats, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Lutemon lutemon = lutemonList.get(position);
        holder.lutemonName.setText(lutemon.getName());
        holder.lutemonStats.setText("Battles : " + lutemon.getTotalBattles() + ",\n Wins : " + lutemon.getWins());
        holder.lutemonImage.setImageResource(lutemon.getImageResource());
    }

    @Override
    public int getItemCount() {
        return lutemonList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView lutemonName, lutemonStats;
        ImageView lutemonImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lutemonName = itemView.findViewById(R.id.lutemon_nameS);
            lutemonStats = itemView.findViewById(R.id.lutemon_stats);
            lutemonImage = itemView.findViewById(R.id.imageViewS);
        }
    }
}