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
import com.anychart.charts.Cartesian;
import com.anychart.enums.Anchor;
import com.anychart.enums.HoverMode;
import com.anychart.enums.Position;
import com.anychart.enums.TooltipPositionMode;
import com.example.oopprojectwork.Lutemon.Lutemon;
import com.example.oopprojectwork.LutemonStorage;
import com.example.oopprojectwork.R;

import java.util.ArrayList;
import java.util.List;

public class BarGraphFragment extends Fragment {

    public BarGraphFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.bar_graph, container, false);

        // Initialize AnyChartView
        AnyChartView anyChartView = view.findViewById(R.id.any_chart_view2);
        setupBarGraph(anyChartView);

        return view;
    }

    private void setupBarGraph(AnyChartView anyChartView) {
        // Initialize Bar chart
        Cartesian bar = AnyChart.bar();

        // Initialize total wins for each color
        List<Lutemon> lutemons = LutemonStorage.allLutemons;
        int redWins = 0, blackWins = 0, greenWins = 0, orangeWins = 0, pinkWins = 0;


        // Calculate total wins for each color
        for (Lutemon lutemon : lutemons) {
            switch (lutemon.color()) {
                case "Red":
                    redWins += lutemon.getWins();
                    break;
                case "Black":
                    blackWins += lutemon.getWins();
                    break;
                case "Green":
                    greenWins += lutemon.getWins();
                    break;
                case "Orange":
                    orangeWins += lutemon.getWins();
                    break;
                case "Pink":
                    pinkWins += lutemon.getWins();
                    break;
            }
        }

        if (redWins == 0 && blackWins == 0 && greenWins == 0 && orangeWins == 0 && pinkWins == 0) {
            bar.title("No battles have been fought yet.");
        } else {
            bar.title("Battles won by each Type(color) of Lutemon");
        }

        // Prepare data for the bar graph
        List<DataEntry> data = new ArrayList<>();
        data.add(new CustomColorDataEntry("Red", redWins, "#FF0000")); // Red
        data.add(new CustomColorDataEntry("Black", blackWins, "#000000")); // Black
        data.add(new CustomColorDataEntry("Green", greenWins, "#008000")); // Green
        data.add(new CustomColorDataEntry("Orange", orangeWins, "#FFA500")); // Orange
        data.add(new CustomColorDataEntry("Pink", pinkWins, "#FFC0CB")); // Pink

        // Set data to the bar chart
        bar.data(data);

        // Configure bar chart appearance
        bar.yScale().minimum(0d);
        bar.tooltip()
                .positionMode(TooltipPositionMode.POINT)
                .anchor(Anchor.CENTER_BOTTOM)
                .position(Position.CENTER_BOTTOM);
        bar.interactivity().hoverMode(HoverMode.BY_X);
        bar.xAxis(0).title("Lutemon Color");
        bar.yAxis(0).title("Wins");

        // Bind the bar chart to the AnyChartView
        anyChartView.setChart(bar);
    }

    // Custom DataEntry class to include color
    private static class CustomColorDataEntry extends ValueDataEntry {
        CustomColorDataEntry(String x, Number value, String color) {
            super(x, value);
            setValue("fill", color);
        }
    }
}