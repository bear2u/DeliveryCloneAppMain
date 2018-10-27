package kr.pe.deliverycloneapp2.repository

import com.google.firebase.firestore.FirebaseFirestore
import durdinapps.rxfirebase2.RxFirestore
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import kr.pe.deliverycloneapp2.model.Address
import kr.pe.deliverycloneapp2.model.Store

object FirebaseRepository : Source {


    val firestoreApp by lazy {
        FirebaseFirestore.getInstance()
    }

    override fun register(store: Store): Completable {
        val documentRef = firestoreApp.collection(store.category?.title ?: "전체")
            .document(
                store.id ?: throw Exception("id is null")
            )
        return RxFirestore.setDocument(documentRef, store)
    }

    override fun fetchItems(categoryName: String): Maybe<List<Store>> {
        return RxFirestore.getCollection(
            firestoreApp.collection(categoryName),
            Store::class.java
        )
    }

    override fun getConvertedAddr(lat: Double, lng: Double) : Observable<Address> {
        TODO("not implemented") //To change body of created functions use File | Settings | File
    }




}