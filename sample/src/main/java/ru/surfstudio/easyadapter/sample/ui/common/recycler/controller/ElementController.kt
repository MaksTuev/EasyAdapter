package ru.surfstudio.easyadapter.sample.ui.common.recycler.controller

import android.view.ViewGroup
import android.widget.TextView
import ru.surfstudio.easyadapter.recycler.controller.BindableItemController
import ru.surfstudio.easyadapter.recycler.holder.BindableViewHolder
import ru.surfstudio.easyadapter.sample.R
import ru.surfstudio.easyadapter.sample.domain.Element
import ru.surfstudio.easyadapter.sample.ui.common.widget.ElementCoverView


class ElementController(
        val onClickListener: (element: Element) -> Unit
) : BindableItemController<Element, ElementController.Holder>() {

    override fun createViewHolder(parent: ViewGroup): Holder = Holder(parent)

    override fun getItemId(data: Element): Long = data.id.hashCode().toLong()

    inner class Holder(parent: ViewGroup) : BindableViewHolder<Element>(parent, R.layout.element_item_layout) {
        private lateinit var data: Element
        private val nameTv: TextView
        private val coverView: ElementCoverView

        init {
            itemView.setOnClickListener { onClickListener.invoke(data) }
            nameTv = itemView.findViewById(R.id.name_tv)
            coverView = itemView.findViewById(R.id.cover_view)
        }

        override fun bind(data: Element) {
            this.data = data
            nameTv.text = data.name
            coverView.render(data.id)
        }
    }
}