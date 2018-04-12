package com.mantas.getfit.getfitunbreakable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecordsActivity extends AppCompatActivity {

    private RecordsAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);

        recyclerView = findViewById(R.id.excercise_recycler);
        adapter = new RecordsAdapter(this, getRecordsData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public static List<RecordsRow> getRecordsData(){
        List<RecordsRow> data = new ArrayList<>();

        String[] excerciseNames = {"Deadlift", "Backsquat", "Snatch"};
        String[] randomButtonText = {"Vienas", "Du", "Trys"};

        for(int i = 0; i < excerciseNames.length && i < randomButtonText.length; i++){
            RecordsRow recordsRow = new RecordsRow();
            recordsRow.name = excerciseNames[i];
            recordsRow.weight = randomButtonText[i];

            data.add(recordsRow);
        }
        return data;
    }
}
