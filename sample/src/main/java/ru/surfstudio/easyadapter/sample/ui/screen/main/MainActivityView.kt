package ru.surfstudio.easyadapter.sample.ui.screen.main

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.agna.ferro.mvp.component.ScreenComponent
import com.agna.ferro.mvp.presenter.MvpPresenter
import com.agna.ferro.mvp.view.activity.MvpActivityView
import kotlinx.android.synthetic.main.activity_main.*
import ru.surfstudio.easyadapter.recycler.EasyAdapter
import ru.surfstudio.easyadapter.recycler.ItemList
import ru.surfstudio.easyadapter.sample.R
import ru.surfstudio.easyadapter.sample.ui.common.recycler.animator.SlideItemAnimator
import ru.surfstudio.easyadapter.sample.ui.common.recycler.controller.ElementController
import ru.surfstudio.easyadapter.sample.ui.common.recycler.controller.EmptyStateController
import ru.surfstudio.easyadapter.sample.ui.screen.main.data.MainScreenModel
import ru.surfstudio.easyadapter.sample.ui.screen.main.list.CommercialController
import ru.surfstudio.easyadapter.sample.ui.screen.main.list.DeliveryController
import ru.surfstudio.easyadapter.sample.ui.screen.main.list.HeaderController
import ru.surfstudio.easyadapter.sample.ui.screen.main.list.carousel.CarouselController
import ru.surfstudio.easyadapter.sample.ui.screen.pagination.PaginationActivityView
import javax.inject.Inject

class MainActivityView : MvpActivityView() {

    @Inject
    lateinit var presenter: MainPresenter

    private lateinit var headerController: HeaderController
    private lateinit var carouselController: CarouselController
    private lateinit var deliveryController: DeliveryController
    private lateinit var commercialController: CommercialController
    private lateinit var elementController: ElementController
    private lateinit var emptyStateController: EmptyStateController

    private val adapter = EasyAdapter()

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

        headerController = HeaderController()
        carouselController = CarouselController(
                onElementClickListener = { openPaginationScreen() },
                onShowAllClickListener = { openPaginationScreen() })
        deliveryController = DeliveryController(
                onClickListener = { show("on delivery click") })
        commercialController = CommercialController(
                onClickListener = { show("on commercial click") })
        elementController = ElementController(
                onClickListener = { openPaginationScreen() })
        emptyStateController = EmptyStateController()
    }

    private fun openPaginationScreen() {
        startActivity(Intent(this, PaginationActivityView::class.java))
    }

    fun render(screenModel: MainScreenModel) {
        val itemList = ItemList.create()
                .addIf(screenModel.hasHeader(), headerController)
                .addAll(screenModel.carousels, carouselController)
                .addIf(!screenModel.isEmpty(), deliveryController)
                .addIf(screenModel.hasCommercial, commercialController)
                .addAll(screenModel.elements, elementController)
                .addIf(screenModel.hasBottomCarousel(), screenModel.bottomCarousel, carouselController)
                .addIf(screenModel.isEmpty(), emptyStateController)
        adapter.setItems(itemList)
    }

    fun show(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    override fun createScreenComponent(): ScreenComponent<*> = DaggerMainComponent.builder().build()

    override fun getPresenter(): MvpPresenter<*> = presenter

    override fun getContentView(): Int = R.layout.activity_main

    override fun getName(): String = "Main"
}
