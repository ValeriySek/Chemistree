package com.selflearning.chemistree.adapter.a;

public final class BindableItem<T, H extends BindableViewHolder<T>> extends BaseItem<H> {
    private T data;

    public BindableItem(T data, BindableItemController<T, H> itemController) {
        super(itemController);
        this.data = data;
    }

    /**
     * Get the data from item.
     *
     * @return data
     */
    public T getData() {
        return data;
    }
}
