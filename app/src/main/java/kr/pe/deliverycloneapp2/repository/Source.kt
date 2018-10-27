package kr.pe.deliverycloneapp2.repository

import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import kr.pe.deliverycloneapp2.model.Address
import kr.pe.deliverycloneapp2.model.Store


interface Source {

    fun getConvertedAddr(lat : Double, lng : Double ) : Observable<Address>
    fun register(store: Store) : Completable
    fun fetchItems(categoryName: String) : Maybe<List<Store>>

}