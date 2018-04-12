package com.mantas.getfit.getfitunbreakable;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

public class RecordsAdapter extends RecyclerView.Adapter<RecordsAdapter.RecordsViewHolder> {

    private LayoutInflater inflater;

    List<RecordsRow> data = Collections.emptyList();

    public RecordsAdapter(Context context, List<RecordsRow> data){
        inflater = LayoutInflater.from(context);

        this.data = data;
    }
    @Override
    public RecordsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.records_row, parent, false);

        RecordsViewHolder recordsViewHolder = new RecordsViewHolder(view);
        return recordsViewHolder;
    }

    @Override
    public void onBindViewHolder(RecordsViewHolder holder, int position) {
        RecordsRow current = data.get(position);
        holder.label.setText(current.name);
        holder.button.setText(current.weight);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }



    class RecordsViewHolder extends RecyclerView.ViewHolder{

        TextView label;
        Button button;

        public RecordsViewHolder(View itemView) {
            super(itemView);

            label = itemView.findViewById(R.id.excercise_name_label);
            button = itemView.findViewById(R.id.random_button);
        }
    }
}
