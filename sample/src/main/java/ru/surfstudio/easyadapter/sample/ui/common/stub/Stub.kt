package ru.surfstudio.easyadapter.sample.ui.common.stub

import java.util.*

data class Stub(val id: Long = Companion.rnd.nextLong()) {
    companion object {
        val rnd = Random()
    }
}

fun generateStubs(count: Int): List<Stub> {
    return (0..count - 1)
            .map { Stub() }
            .toList()
}

