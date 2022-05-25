package com.selflearning.chemistree.adapter.a;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BindableViewHolder<T> extends BaseViewHolder {

    public BindableViewHolder(ViewGroup parent, @LayoutRes int layoutRes) {
        super(parent, layoutRes);
    }

    public BindableViewHolder(View itemView) {
        super(itemView);
    }

    /**
     * Display data in ViewHolder
     * This method will be executed on each call to {@link RecyclerView.Adapter#onBindViewHolder(RecyclerView.ViewHolder, int)}
     *
     * @param data data to display
     */
    public abstract void bind(T data);
}