package com.example.douglasdelatore.preventivosprats.activity;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.webkit.WebView;

import com.example.douglasdelatore.preventivosprats.R;
import com.example.douglasdelatore.preventivosprats.adapter.PreventivosAdapter;
import com.example.douglasdelatore.preventivosprats.helper.ConfiguracaoFirebase;
import com.example.douglasdelatore.preventivosprats.model.CadastroPreventivos;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class RelPreventivasRealizadasActivity extends AppCompatActivity {

    private ArrayList<CadastroPreventivos> listaPreventivos = new ArrayList<>();
    private RecyclerView recyclerViewPreventivos;
    private DatabaseReference preventivosRef;
    private PreventivosAdapter adapter;
    private ValueEventListener valueEventListenerPreventivos;
    private List<CadastroPreventivos> preventivos;
    private Integer contador;


    private float[] yData = {100,50};
    private String[] xData = {"Não Realizadas","Realizadas"};
    private PieChart pieChart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rel_preventivas_realizadas);

        pieChart = findViewById(R.id.pierChart);


        //pieChart.setDescription("Douglas");
        pieChart.setRotationEnabled(true);
        pieChart.setHoleRadius(25f);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterText("teste");
        pieChart.setCenterTextColor(R.color.colorPrimary);
        pieChart.setDrawEntryLabels(true);

        addDataSet(pieChart);

    }

    private void addDataSet(PieChart pieChart){
        ArrayList<PieEntry> yEntry = new ArrayList<>();
        ArrayList<String> xEntry = new ArrayList<>();


        for (int i = 0; i < yData.length; i++){
            yEntry.add(new PieEntry(yData[i] , i));
        }

        for (int i = 0; i < xData.length; i++){
            xEntry.add(xData[i]);
        }


        PieDataSet pieDataSet = new PieDataSet(yEntry,"employee");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);


        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.RED);
        colors.add(Color.GREEN);

        pieDataSet.setColors(colors);


        //create pie data object
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();



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


