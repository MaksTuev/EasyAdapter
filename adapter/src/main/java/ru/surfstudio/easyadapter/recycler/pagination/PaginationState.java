package ru.surfstudio.easyadapter.recycler.pagination;

/**
 * states of {@link BasePaginationableAdapter}
 */
public enum PaginationState {
    COMPLETE(false), // none
    READY(true),     //footer loader
    ERROR(true);     //footer button "show more"

    /**
     * means that list has pagination footer
     */
    boolean visible;

    PaginationState(boolean visible) {
        this.visible = visible;
    }

    public boolean isVisible() {
        return visible;
    }
}
