package kr.pe.deliverycloneapp2.flow.register

import android.net.Uri
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import kr.pe.deliverycloneapp2.model.Store
import kr.pe.deliverycloneapp2.mvp.BaseMvpPresenterImpl
import kr.pe.deliverycloneapp2.repository.RepositoryImpl
import kr.pe.deliverycloneapp2.utils.getUUID
import timber.log.Timber

class RegisterPresenter : BaseMvpPresenterImpl<RegisterContract.View>(), RegisterContract.Presenter {

    private var compositeDisposable : CompositeDisposable  = CompositeDisposable()

    private val repository  by lazy {
        RepositoryImpl()
    }


    override fun register(uri : Uri?, store: Store) {
        store.apply {
            id = getUUID()
        }

        val disposable =
            repository.uploadImage(uri)
            repository.register(store)
            .subscribeBy(
                onComplete = {
                    mView?.registerDone()
                }
            )

        compositeDisposable.add(disposable)

    }

//    override fun uploadImage(uri: Uri) {
//        val disposable = repository.uploadImage(uri)
//            ?.subscribe {
//                mView?.uploadImageDone(it.toString())
//            }
//
//        disposable?.let {
//            compositeDisposable.add(it)
//        }
//
//    }

    override fun detachView() {
        super.detachView()
        compositeDisposable.clear()
    }

}