package ru.surfstudio.easyadapter.recycler.animator;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;

import ru.surfstudio.easyadapter.recycler.holder.BaseViewHolder;


public class BaseItemAnimator extends DefaultItemAnimator {

    @Override
    public final void onRemoveStarting(RecyclerView.ViewHolder item) {
        super.onRemoveStarting(item);
        if(!(item instanceof BaseViewHolder) || !((BaseViewHolder)item).animateRemove()){
            onRemoveStartingInternal(item);
        }
    }

    @Override
    public final void onAddStarting(RecyclerView.ViewHolder item) {
        super.onAddStarting(item);
        if(!(item instanceof BaseViewHolder) || !((BaseViewHolder)item).animateInsert()){
            onAddStartingInternal(item);
        }
    }

    @Override
    public final void onChangeStarting(RecyclerView.ViewHolder item, boolean oldItem) {
        super.onChangeStarting(item, oldItem);
        if(!(item instanceof BaseViewHolder) || !((BaseViewHolder)item).animateChange()){
            onChangeStartingInternal(item);
        }
    }

    protected void onRemoveStartingInternal(RecyclerView.ViewHolder item) {
        //empty
    }

    protected void onAddStartingInternal(RecyclerView.ViewHolder item) {
        //empty
    }

    protected void onChangeStartingInternal(RecyclerView.ViewHolder item) {
        //empty
    }
}
