package kr.pe.deliverycloneapp2.repository

import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Observable
import kr.pe.deliverycloneapp2.model.Address

class FirebaseRepository : Source {
    val firestoreApp by lazy {
        FirebaseFirestore.getInstance()
    }

    override fun getConvertedAddr(lat: Double, lng: Double) : Observable<Address> {
        TODO("not implemented") //To change body of created functions use File | Settings | File
    }
}