package kr.pe.deliverycloneapp2.repository

import io.reactivex.Observable
import kr.pe.deliverycloneapp2.model.Address
import kr.pe.deliverycloneapp2.repository.api.ApiManager

object NetworkRepsitory : Source {

    override fun getConvertedAddr(lng: Double, lat: Double): Observable<Address> {
        //129.075090,35.179632
        return ApiManager.getAddressFromLatLng(lng, lat)
    }
}