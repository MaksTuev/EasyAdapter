package ru.surfstudio.easyadapter.sample.ui.screen.main

import com.agna.ferro.mvp.component.ScreenComponent
import com.agna.ferro.mvp.component.scope.PerScreen
import dagger.Component


@PerScreen
@Component
interface MainComponent: ScreenComponent<MainActivityView> {
    override fun inject(mainActivityView: MainActivityView)
}
