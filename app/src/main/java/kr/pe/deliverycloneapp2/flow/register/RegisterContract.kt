package kr.pe.deliverycloneapp2.flow.register

import kr.pe.deliverycloneapp2.model.Store
import kr.pe.deliverycloneapp2.mvp.BaseMvpPresenter
import kr.pe.deliverycloneapp2.mvp.BaseMvpView

object RegisterContract {
    interface View : BaseMvpView {
        fun registerDone()
    }

    interface Presenter : BaseMvpPresenter<View> {
        fun register(store: Store)
    }
}