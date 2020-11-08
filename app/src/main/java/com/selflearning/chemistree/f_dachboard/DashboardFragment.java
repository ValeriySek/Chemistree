package com.selflearning.chemistree.f_dachboard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.selflearning.chemistree.R;
import com.selflearning.chemistree.f_mendeleevtable.MendeleevTable;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    Context context;
    ImageButton imageButton;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        setUpToolbar(root);
        context = getActivity();

        imageButton = root.findViewById(R.id.imageButtonMendeleev);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MendeleevTable.class);
                startActivity(intent);
            }
        });
//        recyclerView.setHasFixedSize(true);
//
//        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false));
//
//        CategoryAdapter adapter = new CategoryAdapter(DatabaseAccess.getInstance(context).getAllTables(), context);
//        recyclerView.setAdapter(adapter);
//
//        int i = 10;
//        recyclerView.addItemDecoration(new TablesGridItemDecoration(i));
//        final TextView textView = root.findViewById(R.id.text_dashboard);
//        dashboardViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        return root;
    }

    private void setUpToolbar (View view) {
        Toolbar toolbar = view.findViewById(R.id.app_bar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity!=null){
            activity.setSupportActionBar(toolbar);
        }
    }
}