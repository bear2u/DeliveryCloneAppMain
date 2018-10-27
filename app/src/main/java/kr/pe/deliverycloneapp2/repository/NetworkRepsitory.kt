package kr.pe.deliverycloneapp2.repository

import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import kr.pe.deliverycloneapp2.model.Address
import kr.pe.deliverycloneapp2.model.Store
import kr.pe.deliverycloneapp2.repository.api.ApiManager

object NetworkRepsitory : Source {
    override fun fetchItems(categoryName: String): Maybe<List<Store>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun register(store: Store) : Completable{
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getConvertedAddr(lng: Double, lat: Double): Observable<Address> {
        //129.075090,35.179632
        return ApiManager.getAddressFromLatLng(lng, lat)
    }


}