package ru.surfstudio.easyadapter.recycler.item;


import android.support.v7.widget.RecyclerView;

import ru.surfstudio.easyadapter.recycler.ItemList;
import ru.surfstudio.easyadapter.recycler.controller.NoDataItemController;

/**
 * Item for {@link ItemList} without data
 *
 * @param <H> type of ViewHolder
 */
public final class NoDataItem<H extends RecyclerView.ViewHolder>
        extends BaseItem<H> {

    public NoDataItem(NoDataItemController<H> itemController) {
        super(itemController);
    }
}
