package kr.pe.deliverycloneapp2.repository

import android.net.Uri
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageMetadata
import durdinapps.rxfirebase2.RxFirebaseStorage
import durdinapps.rxfirebase2.RxFirestore
import io.reactivex.*
import io.reactivex.schedulers.Schedulers
import kr.pe.deliverycloneapp2.model.Address
import kr.pe.deliverycloneapp2.model.Store
import kr.pe.deliverycloneapp2.utils.getUUID
import timber.log.Timber

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
        val document = firestoreApp.collection(store.categoryName ?: "").document( store.id ?: throw Exception("Empty ID") )
        return RxFirestore.setDocument(document, store)
    }

    override fun uploadImage(uri: Uri): Maybe<Uri>? {
        val ref = firebaseStorage.reference.child(getUUID())

//        ref.putFile(uri)
//            .addOnSuccessListener {
//                Timber.d("successed $it")
//            }

//        ref.updateMetadata(getImageMetaData())
        return RxFirebaseStorage.putFile(ref, uri)
//            .subscribeOn(Schedulers.io())
//            .observeOn(Schedulers.io())
            .flatMapMaybe {
                RxFirebaseStorage.getDownloadUrl(ref)
            }
    }

    fun getImageMetaData() =  StorageMetadata.Builder().setContentType("image/jpg").build()

    override fun getStores(type: String): Maybe<MutableList<Store>> {
        val collectionRef = firestoreApp.collection(type)
        return RxFirestore.getCollection(collectionRef, Store::class.java)
            .doOnError {
                Timber.e(it)
            }
    }
}