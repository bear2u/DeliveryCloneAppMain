package kr.pe.deliverycloneapp2.flow.list.fragment

import android.util.Log
import kr.pe.deliverycloneapp2.model.Store
import kr.pe.deliverycloneapp2.mvp.BaseMvpPresenterImpl

class ListPresenter : BaseMvpPresenterImpl<ListContract.View>(), ListContract.Presenter {
    override fun getStores(type: String?) {
        val items = arrayListOf<Store>()
        0.rangeTo(10).forEach {
            items.add(Store(categoryName = "치킨"))
        }

        Log.d("gdg", "mView : $mView")
        mView?.updateList(items)
    }
}