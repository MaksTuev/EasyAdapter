package ru.surfstudio.easyadapter.recycler.controller;


import ru.surfstudio.easyadapter.recycler.EasyAdapter;
import ru.surfstudio.easyadapter.recycler.ItemList;
import ru.surfstudio.easyadapter.recycler.holder.BindableViewHolder;
import ru.surfstudio.easyadapter.recycler.item.BindableItem;

/**
 * Controller for item of RecyclerView with one block of data {@link BindableItem}.
 * It used with {@link EasyAdapter} and {@link ItemList}
 *
 * @param <H> type of ViewHolder
 * @param <T> type of data
 */
public abstract class BindableItemController<T, H extends BindableViewHolder<T>>
        extends BaseItemController<H, BindableItem<T, H>> {

    @Override
    public final void bind(H holder, BindableItem<T, H> item) {
        bind(holder, item.getData());
    }

    public void bind(H holder, T data) {
        holder.bind(data);
    }

    @Override
    public final long getItemId(BindableItem<T, H> item) {
        return getItemId(item.getData());
    }

    @Override
    public final long getItemHash(BindableItem<T, H> item) {
        return getItemHash(item.getData());
    }

    /**
     * must return unique value
     * method is used for automatically call notify... methods, see {@link EasyAdapter}
     * @return item id
     */
    protected abstract long getItemId(T data);

    /**
     * method is used for automatically call notify... methods, see {@link EasyAdapter}
     * @return hash of data
     */
    protected long getItemHash(T data) {
        return data == null ? 0 : data.hashCode();
    }
}
