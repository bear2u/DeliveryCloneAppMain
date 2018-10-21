package kr.pe.deliverycloneapp2.flow.register

import android.net.Uri
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import kr.pe.deliverycloneapp2.model.Store
import kr.pe.deliverycloneapp2.mvp.BaseMvpPresenterImpl
import kr.pe.deliverycloneapp2.repository.RepositoryImpl
import kr.pe.deliverycloneapp2.utils.getUUID

class RegisterPresenter : BaseMvpPresenterImpl<RegisterContract.View>(), RegisterContract.Presenter {

    private var compositeDisposable : CompositeDisposable  = CompositeDisposable()

    private val repository  by lazy {
        RepositoryImpl()
    }


    override fun register(uri : Uri?, store: Store) {
        store.apply {
            id = getUUID()
        }

        val imageObservable : Maybe<Uri>? = uri?.let{
            repository.uploadImage(uri)
        }

        val registerObservable = imageObservable
            ?.map {
                store.apply {
                    this.thumbnail = it.toString()
                }
            }
            ?.flatMapCompletable(::registerProc)
            ?:registerProc(store)

        val disposable = registerObservable
            .subscribeBy(
                onComplete = {
                    mView?.registerDone()
                }
            )

        compositeDisposable.add(disposable)
    }

    fun registerProc(store : Store): Completable {
        return repository.register(store)
    }

    override fun detachView() {
        super.detachView()
        compositeDisposable.clear()
    }

}