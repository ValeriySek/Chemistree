package com.selflearning.chemistree;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TablesGridItemDecoration extends RecyclerView.ItemDecoration {
    private int decor;

    public TablesGridItemDecoration(int decor) {
        this.decor = decor;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.left = outRect.right = outRect.bottom = outRect.top = decor;
    }
}
