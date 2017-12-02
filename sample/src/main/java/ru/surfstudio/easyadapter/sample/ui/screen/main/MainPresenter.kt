package ru.surfstudio.easyadapter.sample.ui.screen.main

import com.agna.ferro.mvp.component.scope.PerScreen
import com.agna.ferro.mvprx.MvpRxPresenter
import ru.surfstudio.easyadapter.sample.ui.screen.main.data.MainScreenModel
import ru.surfstudio.easyadapter.sample.ui.screen.main.data.ScreenModelFactory
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@PerScreen
class MainPresenter @Inject constructor() : MvpRxPresenter<MainActivityView>() {

    private val INTERVAL: Long = 4000 //ms
    private val screenModelFactory = ScreenModelFactory()
    private var screenModel: MainScreenModel = screenModelFactory.next()

    override fun onLoad(viewRecreated: Boolean) {
        super.onLoad(viewRecreated)
        view.render(screenModel)
        if (!viewRecreated) {
            subscribe(Observable.interval(INTERVAL, TimeUnit.MILLISECONDS)
                    .observeOn(AndroidSchedulers.mainThread()),
                    { _, _ -> true },
                    { _ -> view.render(screenModelFactory.next()) },
                    { _ -> /*ignore*/ })
        }
    }
}
