package ru.surfstudio.easyadapter.recycler.item;


import android.support.v7.widget.RecyclerView;

import ru.surfstudio.easyadapter.recycler.ItemList;
import ru.surfstudio.easyadapter.recycler.controller.BaseItemController;

/**
 * Base Item for {@link ItemList}
 */
public class BaseItem<H extends RecyclerView.ViewHolder> {

    private BaseItemController<H, ? extends BaseItem> itemController;

    public BaseItem(BaseItemController<H, ? extends BaseItem> itemController) {
        this.itemController = itemController;
    }

    public BaseItemController<H, ? extends BaseItem> getItemController() {
        return itemController;
    }
}
