package kr.pe.deliverycloneapp2.flow.list.fragment

import android.util.Log
import io.reactivex.rxkotlin.subscribeBy
import kr.pe.deliverycloneapp2.mvp.BaseMvpPresenterImpl
import kr.pe.deliverycloneapp2.repository.RepositoryImpl
import timber.log.Timber

class ListFragmentPresenter : BaseMvpPresenterImpl<ListFragmentContract.View>(), ListFragmentContract.Presenter {

    private val repository  by lazy {
        RepositoryImpl()
    }
    override fun fetchItems(categoryName: String) {
        repository.fetchItems(categoryName)
            .subscribeBy(
                onSuccess = {
                    Timber.d(it.toString())
                    mView?.fetchItemsDone(it)
                },
                onComplete = {
                    Timber.d("$categoryName : onCompleted")
                },
                onError = {
                    Timber.e(it)
                }
            )

        //mView?.fetchItemsDone(null)
    }

    override fun input(count: Int) {
        Log.d("gdg", "#9 $count")
        mView?.output(count)
    }
}