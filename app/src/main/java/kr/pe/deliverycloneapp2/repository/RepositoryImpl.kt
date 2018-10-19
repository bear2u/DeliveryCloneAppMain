package kr.pe.deliverycloneapp2.repository

import android.net.Uri
import com.google.firebase.storage.StorageMetadata
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import kr.pe.deliverycloneapp2.model.Address
import kr.pe.deliverycloneapp2.model.Store

class RepositoryImpl {

    fun convertAddr(lng: Double, lat: Double): Observable<Address> = NetworkRepsitory.getConvertedAddr(lng, lat)

    fun register(store: Store) : Completable {
        return FirebaseRepository.register(store)
    }

    fun uploadImage(uri: Uri): Maybe<Uri>? {
        return FirebaseRepository.uploadImage(uri)
    }
}