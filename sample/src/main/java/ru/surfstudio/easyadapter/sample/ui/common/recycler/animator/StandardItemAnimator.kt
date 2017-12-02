package ru.surfstudio.easyadapter.sample.ui.common.recycler.animator

import ru.surfstudio.easyadapter.recycler.animator.BaseItemAnimator

open class StandardItemAnimator : BaseItemAnimator() {
    private val ADD_DURATION : Long = 200
    private val REMOVE_DURATION: Long = 350
    private val MOVE_DURATION: Long = 350
    private val CHANGE_DURATION: Long = 200
    init {
        addDuration = ADD_DURATION
        removeDuration = REMOVE_DURATION
        moveDuration = MOVE_DURATION
        changeDuration = CHANGE_DURATION
        supportsChangeAnimations = false
    }
}
