package kr.pe.deliverycloneapp2.repository.api

import io.reactivex.Observable
import kr.pe.deliverycloneapp2.model.ResponseAddress
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface AddressService {

    /*
    위도 : latitude
    경도 : longtitude
    "https://openapi.naver.com/v1/map/reversegeocode?encoding=utf-8&coordType=latlng&query=";
     */
    @Headers(
            "X-Naver-Client-Id:MR_w9O_DKsZpVZ4R7oO4",
            "X-Naver-Client-Secret:V8f20vxLNq"
    )
    @GET("reversegeocode?encoding=utf-8&coordType=latlng")
    fun getAddress(@Query("query") query : String) : Observable<ResponseAddress>
}