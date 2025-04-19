package com.example.oopprojectwork.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oopprojectwork.Lutemon.Lutemon;
import com.example.oopprojectwork.LutemonStatsAdapter;
import com.example.oopprojectwork.LutemonStorage;
import com.example.oopprojectwork.R;

import java.util.List;

public class StatisticsFragment extends Fragment {

    private RecyclerView recyclerView;
    private LutemonStatsAdapter adapter;
    private List<Lutemon> lutemonList;

    public StatisticsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.statistics, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize Lutemon list (replace with actual data source)
        lutemonList = getLutemons();

        // Set up adapter
        adapter = new LutemonStatsAdapter(getContext(), lutemonList);
        recyclerView.setAdapter(adapter);

        // Calculate and display statistics
        TextView textView14 = view.findViewById(R.id.textView14);

        int totalLutemons = lutemonList.size();
        int totalbattles = 0;
        int totalTrainings = 0;
        for (Lutemon lutemon : lutemonList) {
            totalbattles += lutemon.getTotalBattles();
            totalTrainings += lutemon.getTotalTrainings();
        }


        textView14.setText("Total Lutemons: " + totalLutemons + "\nTotal Battles: " + totalbattles/2 + "\nTotal Trainings: " + totalTrainings);
    }

    private List<Lutemon> getLutemons() {
        // Replace this with actual data fetching logic
        return LutemonStorage.allLutemons;
    }
}