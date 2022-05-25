package com.selflearning.chemistree.adapter.a;

import androidx.recyclerview.widget.RecyclerView;

public class BaseItem<H extends RecyclerView.ViewHolder> {

    private BaseItemController<H, ? extends BaseItem> itemController;

    public BaseItem(BaseItemController<H, ? extends BaseItem> itemController) {
        this.itemController = itemController;
    }

    /**
     * Next item in ItemList
     */
    public BaseItem<H> nextItem;

    /**
     * Previous item in ItemList
     */
    public BaseItem<H> previousItem;

    /**
     * Position in ItemList
     */
    public int position;

    /**
     * Position in adapter
     */
    public int adapterPosition;

    /**
     * Get itemController for this item.
     *
     * @return itemController
     */
    public BaseItemController<H, ? extends BaseItem> getItemController() {
        return itemController;
    }
}