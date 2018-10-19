package kr.pe.deliverycloneapp2.repository

import android.net.Uri
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageMetadata
import durdinapps.rxfirebase2.RxFirebaseStorage
import durdinapps.rxfirebase2.RxFirestore
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import kr.pe.deliverycloneapp2.model.Address
import kr.pe.deliverycloneapp2.model.Store
import kr.pe.deliverycloneapp2.utils.getUUID

object FirebaseRepository : Source {

    val firestoreApp by lazy {
        FirebaseFirestore.getInstance()
    }

    val firebaseStorage by lazy {
        FirebaseStorage.getInstance("gs://deliverycloneapp.appspot.com")
    }

    override fun getConvertedAddr(lat: Double, lng: Double) : Observable<Address> {
        TODO("not implemented") //To change body of created functions use File | Settings | File
    }

    override fun register(store: Store): Completable {
        val document = firestoreApp.collection(store.categoryName).document( store.id ?: throw Exception("Empty ID") )
        return RxFirestore.setDocument(document, store)
    }

    override fun uploadImage(uri: Uri): Maybe<Uri>? {
        val ref = firebaseStorage.reference.child(getUUID())
        ref.updateMetadata(getImageMetaData())
        return RxFirebaseStorage.putFile(ref, uri)
            .flatMapMaybe {
                RxFirebaseStorage.getDownloadUrl(ref)
            }
    }

    fun getImageMetaData() =  StorageMetadata.Builder().setContentType("image/jpg").build()

}