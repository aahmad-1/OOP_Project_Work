package com.example.oopprojectwork.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.example.oopprojectwork.Lutemon.Lutemon;
import com.example.oopprojectwork.LutemonStorage;
import com.example.oopprojectwork.R;

import java.util.ArrayList;
import java.util.List;

public class PieChartsFragment extends Fragment {



    public PieChartsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.charts, container, false);

        // Initialize AnyChartView
        AnyChartView anyChartView = view.findViewById(R.id.any_chart_view);
        setupPieChart(anyChartView);

        return view;
    }

    private void setupPieChart(AnyChartView anyChartView) {
        // Initialize Pie chart
        Pie pie = AnyChart.pie();

        // Initialize total battles for each color
        int redBattles = 0;
        int blackBattles = 0;
        int greenBattles = 0;
        int orangeBattles = 0;
        int pinkBattles = 0;

        // Calculate total battles for each color
        for (Lutemon lutemon : LutemonStorage.allLutemons) {
            switch (lutemon.color()) {
                case "Red":
                    redBattles += lutemon.getTotalBattles();
                    break;
                case "Black":
                    blackBattles += lutemon.getTotalBattles();
                    break;
                case "Green":
                    greenBattles += lutemon.getTotalBattles();
                    break;
                case "Orange":
                    orangeBattles += lutemon.getTotalBattles();
                    break;
                case "Pink":
                    pinkBattles += lutemon.getTotalBattles();
                    break;
            }
        }

        // Check if all counters are zero
        if (redBattles == 0 && blackBattles == 0 && greenBattles == 0 && orangeBattles == 0 && pinkBattles == 0) {
            pie.title(" No battles fought yet");
        } else {
            pie.title("Battles fought by Type(color) of Lutemon");
        }

        // Prepare data for the pie chart
        List<DataEntry> data = new ArrayList<>();
        data.add(new ValueDataEntry("Red", redBattles));
        data.add(new ValueDataEntry("Black", blackBattles));
        data.add(new ValueDataEntry("Green", greenBattles));
        data.add(new ValueDataEntry("Orange", orangeBattles));
        data.add(new ValueDataEntry("Pink", pinkBattles));



        // Set data to the pie chart
        pie.data(data);

        // Set custom colors for the pie chart
        pie.palette(new String[] {
                "#FF0000", // Red
                "#000000", // Black
                "#008000", // Green
                "#FFA500", // Orange
                "#FFC0CB"  // Pink
        });

        // Bind the pie chart to the AnyChartView
        anyChartView.setChart(pie);
    }

}
