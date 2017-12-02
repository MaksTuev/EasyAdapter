package ru.surfstudio.easyadapter.recycler.holder;


import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.surfstudio.easyadapter.recycler.animator.BaseItemAnimator;

/**
 * Base ViewHolder with convenient features:
 * 1) has constructor with item layout resource id
 * 2) support custom animation, when used with {@link BaseItemAnimator}
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {

    public BaseViewHolder(ViewGroup parent, @LayoutRes int layoutRes) {
        super(LayoutInflater.from(parent.getContext()).inflate(layoutRes, parent, false));
    }

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    /**
     * Override this method, if you want has custom animation "insert" for this holder
     *
     * @return true, if holder has custom animation
     */
    public boolean animateInsert() {
        return false;
    }

    /**
     * Override this method, if you want has custom animation "change" for this holder
     *
     * @return true, if holder has custom animation
     */
    public boolean animateChange() {
        return false;
    }

    /**
     * Override this method, if you want has custom animation "remove" for this holder
     *
     * @return true, if holder has custom animation
     */
    public boolean animateRemove() {
        return false;
    }
}
