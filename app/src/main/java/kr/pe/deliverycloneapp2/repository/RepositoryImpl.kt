package kr.pe.deliverycloneapp2.repository

import io.reactivex.Observable
import kr.pe.deliverycloneapp2.model.Address
import kr.pe.deliverycloneapp2.repository.NetworkRepsitory

class RepositoryImpl {

    fun convertAddr(lng: Double, lat: Double): Observable<Address> = NetworkRepsitory.getConvertedAddr(lng, lat)
}