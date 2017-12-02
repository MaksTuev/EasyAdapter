package ru.surfstudio.easyadapter.recycler.pagination;


public enum PaginationState {
    COMPLETE(false),
    LOADING(true),
    READY(false),
    ERROR(true);

    boolean visible;

    PaginationState(boolean visible) {
        this.visible = visible;
    }

    public boolean isVisible() {
        return visible;
    }
}
