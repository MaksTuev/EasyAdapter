package ru.surfstudio.easyadapter.sample.interactor.element

import com.agna.ferro.mvp.component.scope.PerScreen
import ru.surfstudio.easyadapter.sample.domain.DataList
import ru.surfstudio.easyadapter.sample.domain.Element
import rx.Observable
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

val DEFAULT_PAGE = 1

@PerScreen //todo create AppComponent and move this to @PerApplication Scope
class ElementRepository @Inject constructor() {

    private val DEFAULT_PAGE_SIZE = 20
    private val rnd = Random()

    fun getElements(page: Int): Observable<DataList<Element>> {
        val startIndex = (page - 1) * DEFAULT_PAGE_SIZE
        val endIndex = if ((startIndex + DEFAULT_PAGE_SIZE) >= Elements.all.size)
            Elements.all.size else
            startIndex + DEFAULT_PAGE_SIZE
        return Observable.timer(5000, TimeUnit.MILLISECONDS)
                .flatMap {
                    /*if (rnd.nextFloat() > 0.5f)
                        Observable.error(RuntimeException()) else*/
                        Observable.just(
                                DataList(
                                        Elements.all.subList(startIndex, endIndex),
                                        page,
                                        DEFAULT_PAGE_SIZE
                                ))
                }
    }
}