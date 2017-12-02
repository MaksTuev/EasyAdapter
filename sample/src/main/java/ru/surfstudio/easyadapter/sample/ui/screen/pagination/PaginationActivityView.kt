package ru.surfstudio.easyadapter.sample.ui.screen.pagination

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.agna.ferro.mvp.component.ScreenComponent
import com.agna.ferro.mvp.presenter.MvpPresenter
import com.agna.ferro.mvp.view.activity.MvpActivityView
import kotlinx.android.synthetic.main.activity_main.*
import ru.surfstudio.easyadapter.recycler.ItemList
import ru.surfstudio.easyadapter.sample.R
import ru.surfstudio.easyadapter.sample.ui.common.recycler.animator.SlideItemAnimator
import ru.surfstudio.easyadapter.sample.ui.common.recycler.controller.ElementController
import ru.surfstudio.easyadapter.sample.ui.common.recycler.controller.ElementStubController
import ru.surfstudio.easyadapter.sample.ui.common.recycler.controller.EmptyStateController
import ru.surfstudio.easyadapter.sample.ui.common.recycler.controller.ErrorStateController
import ru.surfstudio.easyadapter.sample.ui.common.recycler.pagination.PaginationableAdapter
import ru.surfstudio.easyadapter.sample.ui.screen.main.DaggerPaginationComponent
import ru.surfstudio.easyadapter.sample.ui.screen.main.list.HeaderController
import javax.inject.Inject


class PaginationActivityView : MvpActivityView() {

    @Inject
    lateinit var presenter: PaginationPresenter

    private lateinit var headerController: HeaderController
    private lateinit var elementController: ElementController
    private lateinit var stubController: ElementStubController
    private lateinit var emptyStateController: EmptyStateController
    private lateinit var errorStateController: ErrorStateController

    private val adapter: PaginationableAdapter<RecyclerView.ViewHolder> = PaginationableAdapter()

    override fun onCreate(savedInstanceState: Bundle?, viewRecreated: Boolean) {
        super.onCreate(savedInstanceState, viewRecreated)
        initList()
    }

    private fun initList() {
        val linearLayoutManager = LinearLayoutManager(this)
        val itemAnimator = SlideItemAnimator()
        recycler.itemAnimator = itemAnimator
        recycler.layoutManager = linearLayoutManager
        recycler.adapter = adapter
        adapter.setOnShowMoreListener { presenter.loadMore() }

        headerController = HeaderController()
        elementController = ElementController(
                onClickListener = { showText("on element ${it.name} click ") })
        stubController = ElementStubController()
        emptyStateController = EmptyStateController()
        errorStateController = ErrorStateController(
                onReloadClickListener = { presenter.reloadData() })
    }

    fun render(screenModel: PaginationScreenModel) {
        val itemList = when (screenModel.loadState) {
            LoadState.LOADING -> ItemList.create(screenModel.stubs, stubController)
            LoadState.ERROR -> ItemList.create(errorStateController)
            LoadState.EMPTY -> ItemList.create(emptyStateController)
            LoadState.NONE -> ItemList.create(screenModel.elements, elementController)
        }
        adapter.setItems(itemList, screenModel.paginationState)
    }

    fun showText(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    override fun getPresenter(): MvpPresenter<*> = presenter

    override fun getContentView(): Int = R.layout.pagination_activity

    override fun getName(): String = "Pagination"

    override fun createScreenComponent(): ScreenComponent<*> =
            DaggerPaginationComponent.builder().build()
}
