package ru.surfstudio.easyadapter.recycler.holder;

import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;

import ru.surfstudio.easyadapter.recycler.controller.BindableItemController;

/**
 * ViewHolder with rendering one block of data
 * Used with {@link BindableItemController}
 * This holder also has some convenient features, see {@link BaseViewHolder}
 *
 * @param <T> data type
 */
public abstract class BindableViewHolder<T> extends BaseViewHolder {

    public BindableViewHolder(ViewGroup parent, @LayoutRes int layoutRes) {
        super(parent, layoutRes);
    }

    public BindableViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bind(T data);
}
