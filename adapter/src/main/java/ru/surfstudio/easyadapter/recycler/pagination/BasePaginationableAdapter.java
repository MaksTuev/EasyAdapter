package ru.surfstudio.easyadapter.recycler.pagination;

import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import ru.surfstudio.easyadapter.recycler.EasyAdapter;
import ru.surfstudio.easyadapter.recycler.ItemList;
import ru.surfstudio.easyadapter.recycler.controller.NoDataItemController;
import ru.surfstudio.easyadapter.recycler.holder.BindableViewHolder;
import ru.surfstudio.easyadapter.recycler.item.NoDataItem;

/**
 * Добавляет к списку футер с состояниями {@link PaginationState}
 * <p>
 * После эмита события о том что необходимо загрузить новый блок данных, события больше не
 * будут выбрасываться пока адаптеру не будет установлен {@link PaginationState#READY}
 * Также событие о необходимости подгрузить данные эмитится при клике на футер с состоянием
 * {@link PaginationState#ERROR}
 */

public abstract class BasePaginationableAdapter<H extends RecyclerView.ViewHolder> extends EasyAdapter {

    private OnShowMoreListener onShowMoreListener;
    private Handler handler = new Handler();
    private boolean blockShowMoreEvent = true;


    public interface OnShowMoreListener {
        void onShowMore();
    }

    public BasePaginationableAdapter() {
        getPaginationFooterController().setListener(new OnShowMoreListener() {
            @Override
            public void onShowMore() {
                onShowMoreClick();
            }
        });
    }

    protected abstract BasePaginationFooterController<? extends RecyclerView.ViewHolder> getPaginationFooterController();

    protected void setState(final PaginationState state) {
        blockShowMoreEvent = state != PaginationState.READY;
        ItemList items = getItems();
        int lastIndex = items.size() - 1;
        boolean removed = false;
        boolean inserted = false;
        if (lastIndex >= 0 && hasFooter()) {
            items.remove(lastIndex);
            removed = true;
        }
        getPaginationFooterController().setState(state);
        if (state.isVisible()) {
            items.add(getPaginationFooterController());
            inserted = true;
        }
        setItems(items, false);
        if (inserted && removed) {
            notifyItemChanged(lastIndex);
        } else if (inserted) {
            notifyItemInserted(lastIndex + 1);
        } else if (removed) {
            notifyItemRemoved(lastIndex);
        }
    }


    public void setItems(ItemList items, @NonNull PaginationState paginationState) {
        if (paginationState.isVisible()) {
            getPaginationFooterController().setState(paginationState);
            items.add(getPaginationFooterController());
        }
        super.setItems(items);
    }

    @Override
    public void setItems(ItemList items) {
        throw new UnsupportedOperationException("use setItems(ItemList, PaginationState) instead");
    }

    public void setOnShowMoreListener(OnShowMoreListener onShowMoreListener) {
        this.onShowMoreListener = onShowMoreListener;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        initLayoutManager(layoutManager);
        initPaginationListener(recyclerView, layoutManager);
    }

    private void initPaginationListener(RecyclerView recyclerView, final LinearLayoutManager layoutManager) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (onShowMoreListener != null && !blockShowMoreEvent) {
                    int totalItemCount = layoutManager.getItemCount();
                    int firstVisibleItem = layoutManager.findFirstVisibleItemPosition();
                    int lastVisibleItem = layoutManager.findLastVisibleItemPosition();
                    int numVisibleItem = lastVisibleItem - firstVisibleItem;

                    if (totalItemCount - lastVisibleItem < 2 * numVisibleItem) {
                        blockShowMoreEvent = true;
                        onShowMoreListener.onShowMore();
                        setLoadingStatus();
                    }
                }
            }
        });
    }

    //todo comment
    private void setLoadingStatus() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                setState(PaginationState.LOADING);
            }
        });
    }

    private void initLayoutManager(LinearLayoutManager layoutManager) {
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager castedLayoutManager = (GridLayoutManager) layoutManager;
            final GridLayoutManager.SpanSizeLookup existingLookup = castedLayoutManager.getSpanSizeLookup();

            castedLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (position == getPaginationFooterPosition() && hasFooter()) {
                        //full width footer
                        return castedLayoutManager.getSpanCount();
                    } else {
                        return existingLookup.getSpanSize(position);
                    }
                }
            });
        }
    }

    private int getPaginationFooterPosition() {
        return getItemCount() - 1;
    }

    private boolean hasFooter() {
        return getPaginationFooterController().getState().isVisible();
    }

    private void onShowMoreClick() {
        setLoadingStatus();
        if (onShowMoreListener != null) {
            onShowMoreListener.onShowMore();
        }
    }

    protected abstract static class BasePaginationFooterController<H extends BasePaginationFooterHolder> extends NoDataItemController<H> {
        private PaginationState state = PaginationState.COMPLETE;
        private OnShowMoreListener listener;

        public void setListener(OnShowMoreListener listener) {
            this.listener = listener;
        }

        public void setState(PaginationState state) {
            this.state = state;
        }

        public PaginationState getState() {
            return state;
        }

        @Override
        public void bind(H holder, NoDataItem<H> item) {
            holder.bind(state);
        }

        @Override
        public H createViewHolder(ViewGroup parent) {
            return createViewHolder(parent, listener);
        }

        @Override
        public long getItemHash(NoDataItem<H> item) {
            return state.hashCode();
        }

        protected abstract H createViewHolder(ViewGroup parent, OnShowMoreListener listener);

    }

    protected static abstract class BasePaginationFooterHolder extends BindableViewHolder<PaginationState> {

        public BasePaginationFooterHolder(ViewGroup parent, @LayoutRes int layoutRes) {
            super(parent, layoutRes);
        }

    }

}

