package ru.surfstudio.easyadapter.sample.ui.screen.main.list.carousel

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ru.surfstudio.easyadapter.recycler.EasyAdapter
import ru.surfstudio.easyadapter.recycler.controller.BindableItemController
import ru.surfstudio.easyadapter.recycler.holder.BindableViewHolder
import ru.surfstudio.easyadapter.sample.R
import ru.surfstudio.easyadapter.sample.domain.Carousel
import ru.surfstudio.easyadapter.sample.domain.Element
import ru.surfstudio.easyadapter.sample.ui.common.recycler.animator.StandardItemAnimator
import ru.surfstudio.easyadapter.sample.ui.screen.main.list.CarouselElementController


class CarouselController(
        val onElementClickListener: (element: Element) -> Unit,
        val onShowAllClickListener: (carousel: Carousel) -> Unit
) : BindableItemController<Carousel, CarouselController.Holder>() {

    override fun createViewHolder(parent: ViewGroup): Holder = Holder(parent)

    override fun getItemId(data: Carousel): Long = data.id.hashCode().toLong()

    inner class Holder(parent: ViewGroup) : BindableViewHolder<Carousel>(parent, R.layout.carousel_item_layout) {
        private lateinit var data: Carousel
        private val titleTv: TextView
        private val adapter: EasyAdapter = EasyAdapter()
        private val carouselElementController = CarouselElementController(onElementClickListener)

        init {
            val allBtn = itemView.findViewById<View>(R.id.carousel_all_btn)
            allBtn.setOnClickListener { onShowAllClickListener.invoke(data) }
            titleTv = itemView.findViewById<TextView>(R.id.carousel_title_tv)

            val recyclerView = itemView.findViewById<RecyclerView>(R.id.carousel_recycler)
            val linearLayoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            val itemAnimator = StandardItemAnimator()
            GravitySnapHelper(Gravity.START).attachToRecyclerView(recyclerView)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = linearLayoutManager
            recyclerView.itemAnimator = itemAnimator
        }

        override fun bind(data: Carousel) {
            this.data = data
            titleTv.text = data.name
            adapter.setData(data.elements, carouselElementController)
        }
    }
}