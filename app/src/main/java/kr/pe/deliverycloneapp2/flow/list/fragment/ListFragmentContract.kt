package kr.pe.deliverycloneapp2.flow.list.fragment

import kr.pe.deliverycloneapp2.model.Store
import kr.pe.deliverycloneapp2.mvp.BaseMvpPresenter
import kr.pe.deliverycloneapp2.mvp.BaseMvpView

object ListFragmentContract {
    interface View : BaseMvpView {
        fun output(count: Int)
        fun fetchItemsDone(items: List<Store>)
    }

    interface Presenter : BaseMvpPresenter<View> {
        fun input(count: Int)
        fun fetchItems(category: String)
    }
}