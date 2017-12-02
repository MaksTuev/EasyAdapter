package ru.surfstudio.easyadapter.sample.ui.common.recycler.controller

import android.view.ViewGroup
import ru.surfstudio.easyadapter.recycler.controller.BindableItemController
import ru.surfstudio.easyadapter.recycler.holder.BindableViewHolder
import ru.surfstudio.easyadapter.sample.R
import ru.surfstudio.easyadapter.sample.ui.common.stub.Stub
import ru.surfstudio.easyadapter.sample.ui.common.stub.toStub


class ElementStubController : BindableItemController<Stub, ElementStubController.Holder>() {

    override fun createViewHolder(parent: ViewGroup): Holder = Holder(parent)

    override fun getItemId(stub: Stub): Long = stub.id

    inner class Holder(parent: ViewGroup) : BindableViewHolder<Stub>(parent, R.layout.element_item_layout) {

        init {
            itemView.toStub()
        }

        override fun bind(stub: Stub) {
            //ignore
        }
    }
}