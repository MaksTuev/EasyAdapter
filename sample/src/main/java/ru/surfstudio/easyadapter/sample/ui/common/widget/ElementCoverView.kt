package ru.surfstudio.easyadapter.sample.ui.common.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import ru.surfstudio.easyadapter.sample.R


class ElementCoverView(context: Context?, attrs: AttributeSet?) : FrameLayout(context, attrs) {

    private val identifierTv: TextView;

    init {
        View.inflate(context, R.layout.element_view_layout, this)
        identifierTv = findViewById<TextView>(R.id.identifier_tv)
    }

    fun render(id: String) {
        identifierTv.text = id
    }
}