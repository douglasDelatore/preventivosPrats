package com.example.douglasdelatore.preventivosprats.activity;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.douglasdelatore.preventivosprats.R;
import com.example.douglasdelatore.preventivosprats.adapter.PreventivosAdapter;
import com.example.douglasdelatore.preventivosprats.adapter.PreventivosRealizadosAdapter;
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
    private TextView textoTotal;

    private int total = 18;
    private float[] yData = {22.3f,77.77f};
    private String[] xData = {"Não Realizadas","Realizadas"};
    private PieChart pieChart;
    private PreventivosRealizadosAdapter preventivosRealizadosAdapter;
    private PreventivosAdapter preventivosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rel_preventivas_realizadas);

        pieChart = findViewById(R.id.pierChart);
        textoTotal= findViewById(R.id.textViewTotalRelatorio);
        textoTotal.setText("Total da semana: " + total);

        //pieChart.setDescription("Douglas");
        pieChart.setRotationEnabled(true);
        pieChart.setHoleRadius(25f);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterText("Relatório");
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


        PieDataSet pieDataSet = new PieDataSet(yEntry,"");
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