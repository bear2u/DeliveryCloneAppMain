package kr.pe.deliverycloneapp2.flow.list.fragment

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import kr.pe.deliverycloneapp2.mvp.BaseMvpPresenterImpl
import kr.pe.deliverycloneapp2.repository.RepositoryImpl
import timber.log.Timber

class ListFragPresenter : BaseMvpPresenterImpl<ListFragContract.View>(), ListFragContract.Presenter {

    private val compositeDisposable = CompositeDisposable()

    private val repository  by lazy {
        RepositoryImpl()
    }

    override fun getStores(type: String) {
        val disposable = repository.getStores(type)
            .subscribeBy(
                onSuccess = {
                    Timber.d("list : $it")
                    mView?.updateList(it)
                }
            )

        compositeDisposable.add(disposable)
    }

    override fun detachView() {
        super.detachView()
        compositeDisposable.clear()
    }
}