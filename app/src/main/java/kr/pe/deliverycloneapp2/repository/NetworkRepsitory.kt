package kr.pe.deliverycloneapp2.repository

import android.net.Uri
import com.google.firebase.storage.StorageMetadata
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import kr.pe.deliverycloneapp2.model.Address
import kr.pe.deliverycloneapp2.model.Store
import kr.pe.deliverycloneapp2.repository.api.ApiManager

object NetworkRepsitory : Source {

    override fun getConvertedAddr(lng: Double, lat: Double): Observable<Address> {
        //129.075090,35.179632
        return ApiManager.getAddressFromLatLng(lng, lat)
    }

    override fun register(store: Store): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun uploadImage(uri: Uri): Maybe<Uri>? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getStores(type: String): Maybe<MutableList<Store>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}