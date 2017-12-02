package ru.surfstudio.easyadapter.recycler.holder;

import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;

import ru.surfstudio.easyadapter.recycler.controller.DoubleBindableItemController;

/**
 * ViewHolder with rendering two block of data
 * Used with {@link DoubleBindableItemController}
 * This holder also has some convenient features, see {@link BaseViewHolder}
 *
 * @param <T1> first data type
 * @param <T2> second data type
 */
public abstract class DoubleBindableViewHolder<T1, T2> extends BaseViewHolder {

    public DoubleBindableViewHolder(ViewGroup parent, @LayoutRes int layoutRes) {
        super(parent, layoutRes);
    }

    public DoubleBindableViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bind(T1 firstData, T2 secondData);
}
