package kr.pe.deliverycloneapp2.repository.api

import android.util.Log
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import kr.pe.deliverycloneapp2.model.Address
import retrofit2.Retrofit

object ApiManager {
    private const val BASE_SERVER: String = "https://openapi.naver.com/v1/map/"
    var retrofit: Retrofit

    init {
        Log.d("gdg", "created retrofit")
        retrofit = BaseApiManager.initRetrofit(BASE_SERVER)
    }

    fun getAddressFromLatLng(lng: Double, lat: Double) : Observable<Address> {
        val addressService = retrofit.create(AddressService::class.java)
        Log.d("gdg","getAddressFromLatLng : $lat, $lng,")
        return addressService.getAddress(query = "$lat,$lng")
                .map{ it.result } //ResponseAddress -> Address
                .subscribeOn(Schedulers.io())
    }
}