package kr.pe.deliverycloneapp2.flow.list.fragment

import kr.pe.deliverycloneapp2.model.Store
import kr.pe.deliverycloneapp2.mvp.BaseMvpPresenter
import kr.pe.deliverycloneapp2.mvp.BaseMvpView

object ListContract {
    interface View: BaseMvpView {
        fun updateList(items: ArrayList<Store>)
    }

    interface Presenter: BaseMvpPresenter<View> {
        fun getStores(type: String?)
    }
}