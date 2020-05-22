package com.selflearning.chemistree.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.selflearning.chemistree.chemistry.elements.Element;
import com.selflearning.chemistree.R;

import java.util.ArrayList;
import java.util.List;

public class ElementsAdapter extends RecyclerView.Adapter<ElementsAdapter.ElementsViewHolder> {

    List<Element> elementList = new ArrayList<>();

    public ElementsAdapter() {
    }

    public void setElementList(List<Element> elementList) {
        this.elementList = elementList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ElementsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.elements_list, parent, false);
        return new ElementsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ElementsViewHolder holder, int position) {
        if(elementList != null && elementList.size() != 0) {
            Element element = elementList.get(position);
            holder.textViewId.setText(String.format("%s", element.getAtomicNumber()));
            holder.textViewSymbol.setText(element.getSymbol());
            holder.textViewRuTitle.setText(element.getTitle());
            holder.textViewWeight.setText(String.format("%s", element.getWeight()));
        }else {
            holder.textViewRuTitle.setText("element.getRuTitle()");
        }
    }

    @Override
    public int getItemCount() {
        return elementList.size();
    }

    class ElementsViewHolder extends RecyclerView.ViewHolder {
        TextView textViewId;
        TextView textViewSymbol;
        TextView textViewRuTitle;
        TextView textViewWeight;

        public ElementsViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewId = itemView.findViewById(R.id.idOfElement);
            textViewSymbol = itemView.findViewById(R.id.symbolOfElement);
            textViewRuTitle = itemView.findViewById(R.id.ruTitleOfElement);
            textViewWeight = itemView.findViewById(R.id.weightOfElement);
        }
    }
}
