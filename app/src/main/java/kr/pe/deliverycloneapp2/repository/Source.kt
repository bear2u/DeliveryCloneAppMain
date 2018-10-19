package kr.pe.deliverycloneapp2.repository

import android.net.Uri
import com.google.firebase.storage.StorageMetadata
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import kr.pe.deliverycloneapp2.model.Address
import kr.pe.deliverycloneapp2.model.Store


interface Source {

    fun getConvertedAddr(lat : Double, lng : Double ) : Observable<Address>

    fun register(store: Store) : Completable

    fun uploadImage(uri: Uri) : Maybe<Uri>?
}