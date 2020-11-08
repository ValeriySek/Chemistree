package com.selflearning.chemistree.f_games.gamespack;

import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.selflearning.chemistree.adapter.GamesPackAdapter;
import com.selflearning.chemistree.R;
import com.selflearning.chemistree.games.GameComponentsList;

import java.util.ArrayList;
import java.util.List;

public class GamesPack extends Fragment {

    private Activity activity;
    private Context context;
    private GamesPackAdapter adapter;
    private RecyclerView recyclerView;
    GridLayoutManager gridLayoutManager;
    private List<RowType> rowTypeList = new ArrayList<>();
    private GameComponentsList gameComponentsList;

    private GamesPackViewModel mViewModel;

    public static GamesPack newInstance() {
        return new GamesPack();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.games_pack_fragment, container, false);
        initVar();
        initView(root);
        initListener();
        return root;
    }

    private void initVar(){
        activity = getActivity();
        context = activity.getApplicationContext();
        gridLayoutManager = new GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false);
        gameComponentsList = new GameComponentsList();


        rowTypeList.add(new DividerRowType("ORGANIC"));

        rowTypeList.add(new GamesRowType(activity, gameComponentsList.getGameComponentsList().get(0)));
        rowTypeList.add(new GamesRowType(activity, gameComponentsList.getGameComponentsList().get(4)));
        rowTypeList.add(new GamesRowType(activity, gameComponentsList.getGameComponentsList().get(5)));
        rowTypeList.add(new GamesRowType(activity, gameComponentsList.getGameComponentsList().get(2)));
        rowTypeList.add(new GamesRowType(activity, gameComponentsList.getGameComponentsList().get(2)));

        rowTypeList.add(new DividerRowType("INORGANIC"));

        rowTypeList.add(new GamesRowType(activity, gameComponentsList.getGameComponentsList().get(0)));
        rowTypeList.add(new GamesRowType(activity, gameComponentsList.getGameComponentsList().get(1)));
        rowTypeList.add(new GamesRowType(activity, gameComponentsList.getGameComponentsList().get(2)));
        rowTypeList.add(new GamesRowType(activity, gameComponentsList.getGameComponentsList().get(3)));
        rowTypeList.add(new GamesRowType(activity, gameComponentsList.getGameComponentsList().get(4)));
        rowTypeList.add(new GamesRowType(activity, gameComponentsList.getGameComponentsList().get(5)));



        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if(rowTypeList.get(position) instanceof DividerRowType){
                    return 2;
                } else return 1;
            }
        });
    }






    private void initView(View view){
        recyclerView = view.findViewById(R.id.rvGamesPack);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(10);
        adapter = new GamesPackAdapter(rowTypeList, context);
        recyclerView.setAdapter(adapter);
    }

    private void initListener(){

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(GamesPackViewModel.class);
        // TODO: Use the ViewModel
    }
}
