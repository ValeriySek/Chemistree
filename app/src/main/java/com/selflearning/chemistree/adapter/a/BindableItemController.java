package com.selflearning.chemistree.adapter.a;

public abstract class BindableItemController<T, H extends BindableViewHolder<T>>
        extends BaseItemController<H, BindableItem<T, H>> {


    /**
     * Bind item with data to holder
     *
     * @param holder holder to retrieve item
     * @param item   item to bind
     */
    @Override
    public final void bind(H holder, BindableItem<T, H> item) {
        bind(holder, item.getData());
    }

    /**
     * Bind data to holder
     *
     * @param holder holder to retrieve item
     * @param data   data to bind
     */
    public void bind(H holder, T data) {
        holder.bind(data);
    }

    @Override
    public final Object getItemId(BindableItem<T, H> item) {
        return getItemId(item.getData());
    }

    @Override
    public final Object getItemHash(BindableItem<T, H> item) {
        return getItemHash(item.getData());
    }

    /**
     * Get the unique id for data.
     * Method is used for automatically call notify... methods, see {@link }.
     *
     * @param data data
     * @return unique id retrieved from data
     */
    protected abstract Object getItemId(T data);

    /**
     * Get the data hashcode.
     * Method is used for automatically call notify... methods, see {@link }
     *
     * @param data data
     * @return hashcode of the data
     */
    protected Object getItemHash(T data) {
        return data == null ? 0 : data.hashCode();
    }
}

