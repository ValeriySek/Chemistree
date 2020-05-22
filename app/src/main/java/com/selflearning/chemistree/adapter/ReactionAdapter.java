package com.selflearning.chemistree.adapter;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.selflearning.chemistree.R;
import com.selflearning.chemistree.chemistry.reactions.ReactionEntry;

import java.util.List;

public class ReactionAdapter extends RecyclerView.Adapter<ReactionAdapter.ReactionViewHolder> {

    private List<ReactionEntry> reactionEntryList;
    private String react;

    public ReactionAdapter(List<ReactionEntry> reactionEntryList) {
        this.reactionEntryList = reactionEntryList;
    }

    @NonNull
    @Override
    public ReactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_reactions, parent, false);
        return new ReactionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReactionViewHolder holder, int position) {
        if(reactionEntryList != null){
            ReactionEntry reaction = reactionEntryList.get(position);
            react = reaction.reaction.replaceAll("([a-zA-Z])([0-9]*)", "$1<sub><small>$2</small></sub>");
            holder.tvReaction.setText(Html.fromHtml(react));
        }
    }

    @Override
    public int getItemCount() {
        return reactionEntryList.size();
    }

    class ReactionViewHolder extends RecyclerView.ViewHolder {

        private TextView tvReaction;

        public ReactionViewHolder(@NonNull View itemView) {
            super(itemView);
            tvReaction = itemView.findViewById(R.id.tvReaction);
        }
    }
}
