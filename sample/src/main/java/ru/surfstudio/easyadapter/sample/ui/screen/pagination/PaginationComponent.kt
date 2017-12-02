package ru.surfstudio.easyadapter.sample.ui.screen.main

import com.agna.ferro.mvp.component.ScreenComponent
import com.agna.ferro.mvp.component.scope.PerScreen
import dagger.Component
import ru.surfstudio.easyadapter.sample.ui.screen.pagination.PaginationActivityView


@PerScreen
@Component
interface PaginationComponent: ScreenComponent<PaginationActivityView> {
    override fun inject(paginationActivityView: PaginationActivityView)
}
