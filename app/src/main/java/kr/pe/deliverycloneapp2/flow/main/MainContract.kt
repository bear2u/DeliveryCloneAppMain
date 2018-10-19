package kr.pe.deliverycloneapp2.flow.main

import kr.pe.deliverycloneapp2.mvp.BaseMvpPresenter
import kr.pe.deliverycloneapp2.mvp.BaseMvpView

object MainContract {
    interface View : BaseMvpView {
        fun updateAddress(dong: String?)
    }

    interface Presenter : BaseMvpPresenter<View> {
        fun getAddr(lat: Double, lng: Double)
    }
}