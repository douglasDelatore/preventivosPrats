package com.example.douglasdelatore.preventivosprats.activity;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.douglasdelatore.preventivosprats.R;
import com.example.douglasdelatore.preventivosprats.adapter.PreventivosAdapter;
import com.example.douglasdelatore.preventivosprats.helper.ConfiguracaoFirebase;
import com.example.douglasdelatore.preventivosprats.model.Preventivo;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class RelPreventivasRealizadasActivity extends AppCompatActivity {


    private GraphView graphView;
    private LineGraphSeries series;
    private DatabaseReference preventivosRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rel_preventivas_realizadas);



        GraphView graph = (GraphView) findViewById(R.id.graph);
        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 12),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
        graph.addSeries(series);

// styling
        series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
            @Override
            public int get(DataPoint data) {
                return Color.rgb((int) data.getX()*255/4, (int) Math.abs(data.getY()*255/6), 100);
            }
        });

        series.setSpacing(50);

// draw values on top
        series.setDrawValuesOnTop(true);
        series.setValuesOnTopColor(Color.RED);
//series.setValuesOnTopSize(50);




    }

    public void iniciarComponentes(){
        graphView = findViewById(R.id.chart);
        series = new LineGraphSeries();
        graphView.addSeries(series);
    }


}


/*
   preventivosRef = ConfiguracaoFirebase.getFirebase().child("preventivosFeitos");

           preventivosRef.addValueEventListener(new ValueEventListener() {
@Override
public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        DataPoint[] dataPoint =  new DataPoint[(int) dataSnapshot.getChildrenCount()];
        int index = 0;
        double conta = 10;
        double teste = 20;


        for (DataSnapshot myDataSnapshot : dataSnapshot.getChildren()){
        Preventivo preventivo =  myDataSnapshot.getValue(Preventivo.class);
        int numeroOs = Integer.parseInt(preventivo.getNumeroOS());
        dataPoint[index]=new DataPoint(teste, conta);
        index++;


        }
        series.resetData(dataPoint);
        }

@Override
public void onCancelled(@NonNull DatabaseError databaseError) {

        }
        });
*/


/*





 pieChart = findViewById(R.id.chart);

        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5, 10, 5,5);

        pieChart.setDragDecelerationFrictionCoef(0.95f);

        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(61f);

        ArrayList<PieEntry> values = new ArrayList<>();

        values.add(new PieEntry(50f, "Realizadas"));
        values.add(new PieEntry(23f, "Pendentes"));

        PieDataSet pieDataSet = new PieDataSet(values, "");
        pieDataSet.setSliceSpace(3f);
        pieDataSet.setSelectionShift(5f);
        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);

        PieData data = new PieData(pieDataSet);
        data.setValueTextSize(15f);
        data.setValueTextColor(Color.YELLOW);

        pieChart.setData(data);




*/


