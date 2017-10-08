package app.complyglobal.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import app.complyglobal.R;


public class DashboardFragment extends Fragment implements OnChartValueSelectedListener {

    public static final int[] PIE_CHART_COLORS = {
            Color.rgb(43, 126, 208), Color.rgb(208, 65, 43), Color.rgb(208, 200, 43),
            Color.rgb(65, 162, 27), Color.rgb(42, 109, 130)
    };


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_dashboard_navingation, container, false);
        ytdPieChart(rootView);
        nonComplianceStats(rootView);
        return rootView;
    }

    private void ytdPieChart(View rootView){
        PieChart pieChart = (PieChart)rootView.findViewById(R.id.piechart);
        pieChart.setUsePercentValues(false);
        // IMPORTANT: In a PieChart, no values (Entry) should have the same
        // xIndex (even if from different DataSets), since no values can be
        // drawn above each other.
        ArrayList<Entry> yvalues = new ArrayList<Entry>();
        yvalues.add(new Entry(8f, 0));
        yvalues.add(new Entry(15f, 1));
        yvalues.add(new Entry(12f, 2));
        yvalues.add(new Entry(25f, 3));

        PieDataSet dataSet = new PieDataSet(yvalues, null);

        ArrayList<String> xVals = new ArrayList<String>();

        xVals.add("Due");
        xVals.add("Delay");
        xVals.add("Inprogress");
        xVals.add("Completed");

        PieData data = new PieData(xVals, dataSet);
        // In Percentage
        data.setValueFormatter(new PercentFormatter());
        // Default value
        //data.setValueFormatter(new DefaultValueFormatter(0));
        pieChart.setData(data);
        pieChart.setDescription("");

        // pieChart.setDrawHoleEnabled(true);
        // pieChart.setTransparentCircleRadius(58f);


        pieChart.setDrawHoleEnabled(true);
        pieChart.setTransparentCircleRadius(0f);
        pieChart.setHoleRadius(0f);
        dataSet.setColors(DashboardFragment.PIE_CHART_COLORS);

        data.setValueTextSize(13f);
        data.setValueTextColor(Color.WHITE);

        pieChart.setOnChartValueSelectedListener(this);
    }

    private void nonComplianceStats(View rootView){
        BarChart barChart = (BarChart)rootView.findViewById(R.id.barchart);
        barChart.setDescription("");
        // create BarEntry for Bar Group 1
        ArrayList<BarEntry> totalComliance = new ArrayList<>();
        totalComliance.add(new BarEntry(25f, 0));
        totalComliance.add(new BarEntry(35f, 1));
        // creating dataset for Bar Group1
        BarDataSet barDataSet1 = new BarDataSet(totalComliance, "Due");
        barDataSet1.setColor(Color.rgb(43, 126, 208));
        // create BarEntry for Bar Group 1
        ArrayList<BarEntry> completed = new ArrayList<>();
        completed.add(new BarEntry(15f, 0));
        completed.add(new BarEntry(25f, 1));
        // creating dataset for Bar Group 2
        BarDataSet barDataSet2 = new BarDataSet(completed, "Completed");
        barDataSet2.setColor(Color.rgb(65, 162, 27));

        // create BarEntry for Bar Group 1
        ArrayList<BarEntry> inprogres = new ArrayList<>();
        inprogres.add(new BarEntry(6f, 0));
        inprogres.add(new BarEntry(5f, 1));
        // creating dataset for Bar Group 2
        BarDataSet barDataSet3 = new BarDataSet(inprogres, "Inprogres");
        barDataSet3.setColor(Color.rgb(208, 200, 43));

        // create BarEntry for Bar Group 1
        ArrayList<BarEntry> delayed = new ArrayList<>();
        delayed.add(new BarEntry(15f, 0));
        delayed.add(new BarEntry(10f, 1));
        // creating dataset for Bar Group 2
        BarDataSet barDataSet4 = new BarDataSet(delayed, "Delayed");
        barDataSet4.setColor(Color.rgb(208, 65, 43));

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("Oct");
        labels.add("Nov");

        ArrayList<IBarDataSet> dataSets = new ArrayList<>();  // combined all dataset into an arraylist
        dataSets.add(barDataSet1);
        dataSets.add(barDataSet2);
        dataSets.add(barDataSet3);
        dataSets.add(barDataSet4);

        // initialize the Bardata with argument labels and dataSet
        BarData data = new BarData(labels,dataSets);

        barChart.setData(data);
    }

    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}
