package ru.surfstudio.easyadapter.recycler.controller;


import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import ru.surfstudio.easyadapter.recycler.EasyAdapter;
import ru.surfstudio.easyadapter.recycler.ItemList;
import ru.surfstudio.easyadapter.recycler.item.BaseItem;

/**
 * Base Controller for item of RecyclerView. It used with {@link EasyAdapter} and {@link ItemList}
 * It responsible for interaction with item.
 * @param <H> type of ViewHolder
 * @param <I> type of Item
 */
public abstract class BaseItemController<H extends RecyclerView.ViewHolder, I extends BaseItem> {
    public static final long NO_ID = RecyclerView.NO_ID;

    public abstract void bind(H holder, I item);

    public abstract H createViewHolder(ViewGroup parent);

    public int viewType() {
        return getClass().getCanonicalName().hashCode();
    }

    public long getItemId(I item){
        return NO_ID;
    }

    public abstract long getItemHash(I item);
}
