package kr.pe.deliverycloneapp2.repository

import io.reactivex.Observable
import kr.pe.deliverycloneapp2.model.Address


interface Source {

    fun getConvertedAddr(lat : Double, lng : Double ) : Observable<Address>

}