package com.selflearning.chemistree.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.selflearning.chemistree.R;
import com.selflearning.chemistree.listeners.OnItemClickListener;
import com.selflearning.chemistree.ui.gamespack.DividerRowType;
import com.selflearning.chemistree.ui.gamespack.GamesRowType;
import com.selflearning.chemistree.ui.gamespack.RowType;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GamesPackAdapter extends RecyclerView.Adapter {

    private List<RowType> rowTypeList;
    private Context context;

    private OnItemClickListener clickListener;

    private final int TYPE_ITEM1 = 0;
    private final int TYPE_ITEM2 = 1;

    public GamesPackAdapter(List<RowType> rowTypeList, Context context) {
        this.rowTypeList = rowTypeList;
        this.context = context;
    }

    public void setClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public int getItemViewType(int position) {
        if(rowTypeList.get(position) instanceof DividerRowType){
            return TYPE_ITEM1;
        }
        return TYPE_ITEM2;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == TYPE_ITEM1){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_game_pack_divider, parent,false);
            return new DividerViewHolder(view);
        } else {
//            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_game_pack_games, parent, false);
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_games_card, parent, false);
            return new GamePackViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RowType type = rowTypeList.get(position);

        if(holder instanceof DividerViewHolder){
            ((DividerViewHolder) holder).tvDivider.setText(((DividerRowType)type).getText());

        }else if (holder instanceof GamePackViewHolder){

            Picasso.get().load(((GamesRowType)type).getBackground()).fit().centerCrop().into(((GamePackViewHolder)holder).ivBackground);

//            ((GamePackViewHolder) holder).ivBackground.setImageResource(((GamesRowType)type).getBackground());
            ((GamePackViewHolder) holder).tvName.setText(((GamesRowType) type).getName());

            ((GamePackViewHolder) holder).cardView.setOnClickListener(((GamesRowType)type).getOnClickListener());

        }
    }

    @Override
    public int getItemCount() {
        return rowTypeList.size();
    }

    class DividerViewHolder extends RecyclerView.ViewHolder{

        TextView tvDivider;

        public DividerViewHolder(@NonNull View itemView) {
            super(itemView);

            tvDivider = itemView.findViewById(R.id.tvRVGamesDivider);
        }
    }

    class GamePackViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        Button bGamesPack;
        ImageView ivBackground;
        ImageView ivIcon;
        TextView tvName;
        MaterialCardView cardView;

        public GamePackViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            bGamesPack = itemView.findViewById(R.id.bRVGames);
            ivBackground = itemView.findViewById(R.id.ivBackgroundGameCard);
            ivIcon = itemView.findViewById(R.id.ivIconGameCard);
            tvName = itemView.findViewById(R.id.tvNameGameCard);
            cardView = itemView.findViewById(R.id.cardViewGame);
        }

        @Override
        public void onClick(View v) {
            if(clickListener != null){
                clickListener.onItemClick(getAdapterPosition());
            }
        }
    }
}
