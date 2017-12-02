package ru.surfstudio.easyadapter.sample.ui.screen.pagination

import com.agna.ferro.mvp.component.scope.PerScreen
import com.agna.ferro.mvprx.MvpRxPresenter
import ru.surfstudio.easyadapter.sample.interactor.element.DEFAULT_PAGE
import ru.surfstudio.easyadapter.sample.interactor.element.ElementRepository
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import javax.inject.Inject

@PerScreen
class PaginationPresenter @Inject constructor(
        val elementRepository: ElementRepository
) : MvpRxPresenter<PaginationActivityView>() {

    private val screenModel = PaginationScreenModel()
    private var loadMainSubscription: Subscription? = null
    private var loadMoreSubscription: Subscription? = null

    override fun onLoad(viewRecreated: Boolean) {
        super.onLoad(viewRecreated)
        if (viewRecreated) {
            view.render(screenModel)
        } else {
            loadMainData()
        }
    }

    private fun loadMainData() {
        loadMainSubscription?.unsubscribe()
        loadMoreSubscription?.unsubscribe()
        if (!screenModel.hasData()) {
            screenModel.loadState = LoadState.LOADING
            view.render(screenModel)
        }
        loadMainSubscription = subscribe(
                elementRepository.getElements(DEFAULT_PAGE)
                        .observeOn(AndroidSchedulers.mainThread()),
                { elements ->
                    screenModel.elements = elements
                    screenModel.setNormalLoadState()
                    screenModel.setNormalPaginationState()
                    view.render(screenModel)
                },
                { _ ->
                    screenModel.setErrorLoadState()
                    screenModel.setErrorPaginationState()
                    view.showText("Imitate load data error")
                    view.render(screenModel)
                })
    }

    fun loadMore() {
        view.showText("Start pagination request")
        loadMoreSubscription = subscribe(
                elementRepository.getElements(screenModel.elements.nextPage)
                        .observeOn(AndroidSchedulers.mainThread()),
                { elements ->
                    screenModel.elements.merge(elements)
                    screenModel.setNormalPaginationState()
                    view.showText("Pagination request complete")
                    view.render(screenModel)
                },
                { _ ->
                    screenModel.setErrorPaginationState()
                    view.showText("Imitate pagination request error")
                    view.render(screenModel)
                })
    }

    fun reloadData() {
        loadMainData()
    }

}


