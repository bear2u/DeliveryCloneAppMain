package kr.pe.deliverycloneapp2.flow.list.fragment

import kr.pe.deliverycloneapp2.model.Store
import kr.pe.deliverycloneapp2.mvp.BaseMvpPresenter
import kr.pe.deliverycloneapp2.mvp.BaseMvpView

object ListFragContract {
    interface View: BaseMvpView {
        fun updateList(items: MutableList<Store>)
    }

    interface Presenter: BaseMvpPresenter<View> {
        fun getStores(type: String)
    }
}