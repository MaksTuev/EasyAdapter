package ru.surfstudio.easyadapter.sample.ui.common.recycler.controller

import android.view.ViewGroup
import ru.surfstudio.easyadapter.recycler.controller.NoDataItemController
import ru.surfstudio.easyadapter.recycler.holder.BaseViewHolder
import ru.surfstudio.easyadapter.sample.R


class EmptyStateController : NoDataItemController<EmptyStateController.Holder>() {

    override fun createViewHolder(parent: ViewGroup): Holder = Holder(parent)

    inner class Holder(parent: ViewGroup) : BaseViewHolder(parent, R.layout.empty_state_item_layout)
}