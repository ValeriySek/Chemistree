package com.selflearning.chemistree.adapter.a;

import androidx.recyclerview.widget.RecyclerView;

public final class NoDataItem<H extends RecyclerView.ViewHolder>
        extends BaseItem<H> {

    public NoDataItem(NoDataItemController<H> itemController) {
        super(itemController);
    }
}

