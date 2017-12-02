package ru.surfstudio.easyadapter.sample.ui.screen.pagination

import ru.surfstudio.easyadapter.recycler.pagination.PaginationState
import ru.surfstudio.easyadapter.sample.domain.DataList
import ru.surfstudio.easyadapter.sample.domain.Element
import ru.surfstudio.easyadapter.sample.ui.common.stub.Stub
import ru.surfstudio.easyadapter.sample.ui.common.stub.generateStubs

data class PaginationScreenModel(
        var loadState: LoadState = LoadState.NONE,
        var paginationState: PaginationState = PaginationState.COMPLETE,
        var elements: DataList<Element> = DataList.empty<Element>(),
        val stubs: List<Stub> = generateStubs(10)
) {
    fun setNormalPaginationState() {
        paginationState = when (elements.canGetMore()) {
            true -> PaginationState.READY
            false -> PaginationState.COMPLETE
        }
    }

    fun setErrorPaginationState() {
        paginationState = when (elements.isEmpty()) {
            true -> PaginationState.COMPLETE
            false -> PaginationState.ERROR
        }
    }

    fun setNormalLoadState() {
        loadState = when (elements.isEmpty()) {
            true -> LoadState.EMPTY
            false -> LoadState.NONE
        }
    }

    fun setErrorLoadState() {
        loadState = when (elements.isEmpty()) {
            true -> LoadState.ERROR
            false -> LoadState.NONE
        }
    }

    fun hasData(): Boolean {
        return elements.isNotEmpty()
    }
}

enum class LoadState {
    LOADING,
    ERROR,
    EMPTY,
    NONE
}
