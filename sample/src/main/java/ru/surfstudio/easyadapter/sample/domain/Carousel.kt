package ru.surfstudio.easyadapter.sample.domain


data class Carousel(
        val id: String,
        val name: String,
        val elements: List<Element> = emptyList()
)