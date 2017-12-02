package ru.surfstudio.easyadapter.sample.ui.screen.main.list

import android.view.ViewGroup
import ru.surfstudio.easyadapter.recycler.controller.NoDataItemController
import ru.surfstudio.easyadapter.recycler.holder.BaseViewHolder
import ru.surfstudio.easyadapter.sample.R


class DeliveryController(
        val onClickListener: () -> Unit
) : NoDataItemController<DeliveryController.Holder>() {

    override fun createViewHolder(parent: ViewGroup): Holder = Holder(parent)

    inner class Holder(parent: ViewGroup) : BaseViewHolder(parent, R.layout.delivery_item_layout) {
        init {
            itemView.setOnClickListener { onClickListener.invoke() }
        }
    }
}