package com.selflearning.chemistree.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import android.text.Html;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.selflearning.chemistree.R;
import com.selflearning.chemistree.chemistry.inorganic.bases.Bases;
import com.selflearning.chemistree.listeners.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class AcidsAdapter extends RecyclerView.Adapter<AcidsAdapter.AcidsViewHolder> {

    private List<Bases> acidsList = new ArrayList<>();

    private OnItemClickListener onItemClickListener;

    public AcidsAdapter() {
    }

    public void setDataList(List<Bases> acidsList) {
        this.acidsList = acidsList;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public AcidsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.acids_list, parent, false);
        return new AcidsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AcidsViewHolder holder, int position) {
        if(acidsList.size() != 0) {
            Bases acid = acidsList.get(position);
            holder.textViewAcidFormula.setText(Html.fromHtml(acid.getFormula().replaceAll("([2-9]*)", "<sub><small>$1</small></sub>")));
            holder.textViewAcidName.setText(acid.getName());
        }else {
            holder.textViewAcidFormula.setText("no data");
        }
    }

    @Override
    public int getItemCount() {
        return acidsList.size();
    }

    class AcidsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textViewAcidFormula;
        TextView textViewAcidName;

        public AcidsViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewAcidFormula = itemView.findViewById(R.id.textViewAcidFormula);
            textViewAcidName = itemView.findViewById(R.id.textViewAcidRuName);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null){
                onItemClickListener.onItemClick(getAdapterPosition());
            }
        }
    }
}
