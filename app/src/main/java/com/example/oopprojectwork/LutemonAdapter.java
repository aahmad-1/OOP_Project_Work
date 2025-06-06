package com.example.oopprojectwork;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oopprojectwork.Lutemon.Lutemon;

import java.util.ArrayList;

public class LutemonAdapter extends RecyclerView.Adapter<LutemonAdapter.LutemonViewHolder> {
    private ArrayList<Lutemon> lutemons;
    private Context context;
    private ArrayList<Lutemon> selectedLutemons;

    public LutemonAdapter(ArrayList<Lutemon> lutemons, Context context) {
        this.lutemons = lutemons;
        this.context = context;
        this.selectedLutemons = new ArrayList<>();
    }

    @NonNull
    @Override
    public LutemonAdapter.LutemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_lutemon, parent, false);
        return new LutemonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LutemonAdapter.LutemonViewHolder holder, int position) {
        Lutemon lutemon = lutemons.get(position);
        holder.lutemonName.setText(lutemon.getName());
        holder.lutemonDetails.setText(lutemon.toString());

        // Set the image based on Lutemon type
        holder.lutemonImage.setImageResource(lutemon.getImageResourceRight());

        holder.checkBox.setOnCheckedChangeListener(null);
        holder.checkBox.setChecked(selectedLutemons.contains(lutemon));
        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                selectedLutemons.add(lutemon);
                } else {
                selectedLutemons.remove(lutemon);
            }

        });


    }

    @Override
    public int getItemCount() {
        if (lutemons != null) {
            return lutemons.size();
        }
        return 0;
    }

    public ArrayList<Lutemon> getSelectedLutemons() {
        return selectedLutemons;
    }


    public static class LutemonViewHolder extends RecyclerView.ViewHolder {

        TextView lutemonName;
        TextView lutemonDetails;
        CheckBox checkBox;
        ImageView lutemonImage;

        public LutemonViewHolder(@NonNull View itemView) {
            super(itemView);
            lutemonName = itemView.findViewById(R.id.lutemon_name);
            lutemonDetails = itemView.findViewById(R.id.lutemon_stats);
            checkBox = itemView.findViewById(R.id.checkBox);
            lutemonImage = itemView.findViewById(R.id.imageView2);

        }

    }
}

