package com.selflearning.chemistree.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.selflearning.chemistree.dBHelper.Tables;
import com.selflearning.chemistree.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.TablesViewHolder> {
    List<Tables> tablesList;
    Context context;

    public CategoryAdapter(List<Tables> tablesList, Context context) {
        this.tablesList = tablesList;
        this.context = context;
    }

    @NonNull
    @Override
    public TablesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.tables_card, parent, false);
        return new TablesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TablesViewHolder holder, int position) {
        holder.tableTitle.setText(tablesList.get(position).getName());
        Picasso.get().load(tablesList.get(position).getImage()).fit().into(holder.tableImage);
    }

    @Override
    public int getItemCount() {
        return tablesList.size();
    }



    public class TablesViewHolder extends RecyclerView.ViewHolder {
        ImageView tableImage;
        TextView tableTitle;
        CardView cardView;
        public TablesViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_category);
            tableImage = itemView.findViewById(R.id.table_image);
            tableTitle = itemView.findViewById(R.id.table_title);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}
