package com.selflearning.chemistree.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.selflearning.chemistree.R;
import com.selflearning.chemistree.models.ReportModels;

import java.util.ArrayList;

public class PostGameReviewAdapter extends RecyclerView.Adapter<PostGameReviewAdapter.PostGameReviewViewHolder> {

    private Context context;
    private Activity activity;

    private ArrayList<ReportModels> arrayList;

    public PostGameReviewAdapter(Context context, Activity activity, ArrayList<ReportModels> arrayList) {
        this.context = context;
        this.activity = activity;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public PostGameReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_post_game_report, parent, false);
        return new PostGameReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostGameReviewViewHolder holder, int position) {
        ReportModels reportModels = arrayList.get(position);
        holder.tvName.setText(Html.fromHtml(reportModels.getCorrectName()));
        holder.tvDesc.setText(Html.fromHtml(reportModels.getCorrectDesc()));
    }

    @Override
    public int getItemCount() {
        return (arrayList != null ? arrayList.size() : 0);
    }

    class PostGameReviewViewHolder extends RecyclerView.ViewHolder{

        private TextView tvName;
        private TextView tvDesc;

        public PostGameReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvReportName);
            tvDesc = itemView.findViewById(R.id.tvReportDesc);
        }
    }
}
