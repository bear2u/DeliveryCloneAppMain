package kr.pe.deliverycloneapp2.flow.main

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import kr.pe.deliverycloneapp2.mvp.BaseMvpPresenterImpl
import kr.pe.deliverycloneapp2.repository.RepositoryImpl

class MainPresenter : BaseMvpPresenterImpl<MainContract.View>(), MainContract.Presenter {

    private var compositeDisposable : CompositeDisposable  = CompositeDisposable()

    private val repository  by lazy {
        RepositoryImpl()
    }

    override fun getAddr(lng: Double, lat: Double) {
        //129
        Log.d("gdg", "#15 $lng, $lat");
        val disposable = repository.convertAddr(lng, lat)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            Log.d("gdg", "#22 : $it")
                            mView?.updateAddress(it.items[0].addressDetail.dongmyun)
                        },
                        onError = {
                            it.printStackTrace()
                        }
                )

        compositeDisposable.add(disposable)
    }

    override fun detachView() {
        super.detachView()
        compositeDisposable.clear()
    }

}