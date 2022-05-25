package com.selflearning.chemistree.adapter.a;

import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public abstract class BaseItemController<H extends RecyclerView.ViewHolder, I extends BaseItem> {

    public static final long NO_ID = RecyclerView.NO_ID;

    private final static Map<Class<? extends BaseItemController>, Integer> viewTypeIdsMap = new HashMap<>();

    /**
     * Bind base item to holder
     *
     * @param holder holder to retrieve item
     * @param item   item to bind
     */
    public abstract void bind(H holder, I item);

    /**
     * Create holder inside parent
     *
     * @param parent parent which holder will attach to
     */
    public abstract H createViewHolder(ViewGroup parent);

    /**
     * Calls when view, which is bounded to holder, is no longer active
     */
    public void unbind(H holder, I item) {
        // do nothing
    }

    public int viewType() {
        return getTypeHashCode();
    }

    /**
     * Get the unique id for item.
     * Method is used for automatically call notify... methods
     * to define if the items are the same, see {@link }.
     *
     * @param item item
     * @return unique id retrieved from item
     */
    public abstract Object getItemId(I item);

    /**
     * Get the item hashcode.
     * Method is used for automatically call notify... methods
     * to define if the item's content is the same, see {@link }
     *
     * @param item item
     * @return hashcode of the item
     */
    public abstract Object getItemHash(I item);

    /**
     * @return hash code for this {@link BaseItemController} type
     */
    protected int getTypeHashCode() {
        final Class<? extends BaseItemController> clazz = getClass();
        Integer id = viewTypeIdsMap.get(clazz);
        if (id == null) {
            id = new Random().nextInt();
            boolean hasId = false;
            for (Map.Entry<Class<? extends BaseItemController>, Integer> e : viewTypeIdsMap.entrySet()) {
                if (id.equals(e.getValue())) {
                    hasId = true;
                    break;
                }
            }
            if (hasId) {
                return getTypeHashCode();
            }
            viewTypeIdsMap.put(clazz, id);
        }
        return id;
    }
}

