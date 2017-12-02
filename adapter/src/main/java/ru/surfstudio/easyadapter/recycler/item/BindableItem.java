package ru.surfstudio.easyadapter.recycler.item;


import ru.surfstudio.easyadapter.recycler.controller.BindableItemController;
import ru.surfstudio.easyadapter.recycler.holder.BindableViewHolder;

public final class BindableItem<T, H extends BindableViewHolder<T>> extends BaseItem<H> {
    private T data;

    public BindableItem(T data, BindableItemController<T, H> itemController) {
        super(itemController);
        this.data = data;
    }

    public T getData() {
        return data;
    }

}
