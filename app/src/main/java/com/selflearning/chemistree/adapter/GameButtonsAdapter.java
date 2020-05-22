package com.selflearning.chemistree.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.selflearning.chemistree.R;
import com.selflearning.chemistree.listeners.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class GameButtonsAdapter extends RecyclerView.Adapter {

    private Context context;
    private Activity activity;
    private OnItemClickListener onItemClickListener;
    private List<String> list;
    private List<Boolean> booleans;

    private final int TYPE_ITEM1 = 0;
    private final int TYPE_ITEM2 = 1;
    private int tipe;

    public GameButtonsAdapter(Context context, int viewTipe) {
        this.list = new ArrayList<>();
        this.context = context;
        tipe = viewTipe;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(tipe == TYPE_ITEM1) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_game_2, parent, false);
            return new GameButtonViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_game_3, parent, false);
            return new GameTextViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        String s = list.get(position);
        if(holder instanceof GameButtonViewHolder) {
            ((GameButtonViewHolder)holder).button.setText(Html.fromHtml(s));
        }else if(holder instanceof GameTextViewHolder){
            ((GameTextViewHolder)holder).textView.setText(s);
            ((GameTextViewHolder)holder).textView.setEnabled(booleans.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return (list != null ? list.size() : 0);
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public List<Boolean> getBooleans() {
        return booleans;
    }

    public void setBooleans(List<Boolean> booleans) {
        this.booleans = booleans;
        notifyDataSetChanged();
    }

    class GameButtonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Button button;

        public GameButtonViewHolder(@NonNull View itemView) {
            super(itemView);

            button = itemView.findViewById(R.id.bRVGame2);
            button.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(onItemClickListener != null){
                onItemClickListener.onItemClick(getAdapterPosition());

            }
        }
    }

    class GameTextViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private MaterialButton textView;

        public GameTextViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tvRVGame3);
            textView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(onItemClickListener != null){
                onItemClickListener.onItemClick(getAdapterPosition());
            }
        }
    }
}
