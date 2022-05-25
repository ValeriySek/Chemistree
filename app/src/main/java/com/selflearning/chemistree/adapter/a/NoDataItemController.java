package com.selflearning.chemistree.adapter.a;

import androidx.recyclerview.widget.RecyclerView;

public abstract class NoDataItemController<H extends RecyclerView.ViewHolder>
        extends BaseItemController<H, NoDataItem<H>> {

    @Override
    public Object getItemId(NoDataItem<H> item) {
        return getTypeHashCode();
    }

    /**
     * Bind item to holder. Empty, because item simply contains no data.
     *
     * @param holder holder to retrieve item
     * @param item   item to bind
     */
    @Override
    public void bind(H holder, NoDataItem<H> item) {
        //empty
    }

    /**
     * Method always returns the same value because item has no data
     *
     * @param item noDataItem
     * @return hashCode
     * @see BaseItemController#getItemHash(BaseItem)
     */
    @Override
    public Object getItemHash(NoDataItem<H> item) {
        return "0";
    }
}
