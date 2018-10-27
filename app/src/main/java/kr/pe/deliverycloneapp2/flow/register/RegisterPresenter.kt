package kr.pe.deliverycloneapp2.flow.register

import android.util.Log
import io.reactivex.rxkotlin.subscribeBy
import kr.pe.deliverycloneapp2.model.Store
import kr.pe.deliverycloneapp2.mvp.BaseMvpPresenterImpl
import kr.pe.deliverycloneapp2.repository.RepositoryImpl

class RegisterPresenter : BaseMvpPresenterImpl<RegisterContract.View>(), RegisterContract.Presenter {

    val repository by lazy {
        RepositoryImpl()
    }

    override fun register(store: Store) {
        repository.register(store)
            .subscribe {
                Log.d("gdg", "add completed ")
                mView?.registerDone()
            }
    }

}