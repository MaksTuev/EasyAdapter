package ru.surfstudio.easyadapter.recycler.item;


import ru.surfstudio.easyadapter.recycler.ItemList;
import ru.surfstudio.easyadapter.recycler.controller.DoubleBindableItemController;
import ru.surfstudio.easyadapter.recycler.holder.DoubleBindableViewHolder;

/**
 * Item for {@link ItemList} with two block data
 *
 * @param <T1> type of first data
 * @param <T2> type of second data
 * @param <H>  type of ViewHolder
 */
public class DoubleBindableItem<T1, T2, H extends DoubleBindableViewHolder<T1, T2>> extends BaseItem<H> {
    private T1 firstData;
    private T2 secondData;

    public DoubleBindableItem(T1 firstData, T2 secondData, DoubleBindableItemController<T1, T2, H> itemController) {
        super(itemController);
        this.firstData = firstData;
        this.secondData = secondData;
    }

    public T1 getFirstData() {
        return firstData;
    }

    public T2 getSecondData() {
        return secondData;
    }
}
