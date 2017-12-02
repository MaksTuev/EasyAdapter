package ru.surfstudio.easyadapter.sample.ui.screen.main.data

import ru.surfstudio.easyadapter.sample.domain.Carousel
import ru.surfstudio.easyadapter.sample.domain.Element


data class MainScreenModel(
        val carousels: List<Carousel> = emptyList(),
        val hasCommercial: Boolean = false,
        val elements: List<Element> = emptyList(),
        val bottomCarousel: Carousel? = null) {

    fun hasHeader(): Boolean = !carousels.isEmpty()
    fun hasBottomCarousel(): Boolean = bottomCarousel != null
    fun isEmpty(): Boolean
            = carousels.isEmpty()
            && !hasCommercial
            && elements.isEmpty()
            && bottomCarousel == null
}