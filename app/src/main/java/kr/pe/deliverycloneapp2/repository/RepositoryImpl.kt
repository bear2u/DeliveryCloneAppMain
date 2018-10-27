package kr.pe.deliverycloneapp2.repository

import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import kr.pe.deliverycloneapp2.model.Address
import kr.pe.deliverycloneapp2.model.Store
import kr.pe.deliverycloneapp2.repository.NetworkRepsitory

class RepositoryImpl {

    fun convertAddr(lng: Double, lat: Double): Observable<Address>
            = NetworkRepsitory.getConvertedAddr(lng, lat)
    fun register(store: Store) : Completable {
        return FirebaseRepository.register(store)
    }

    fun fetchItems(categoryName: String) : Maybe<List<Store>> {
        return FirebaseRepository.fetchItems(categoryName)
    }
}