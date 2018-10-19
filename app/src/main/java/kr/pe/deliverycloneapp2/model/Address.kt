package kr.pe.deliverycloneapp2.model

import com.squareup.moshi.Json

data class ResponseAddress(
        @field:Json(name = "result") val result : Address
)

data class Address (
        @field:Json(name = "total") val total : Int,
        @field:Json(name = "items") val items : List<AddressItem>
)

data class AddressItem (
        @field:Json(name = "address") val address : String,
        @field:Json(name = "addrdetail") val addressDetail : AddressDetail
)

data class AddressDetail (
        @field:Json(name = "country") val country : String,
        @field:Json(name = "sido") val sido : String,
        @field:Json(name = "sigugun") val sigugun : String,
        @field:Json(name = "dongmyun") val dongmyun : String,
        @field:Json(name = "rest") val rest : String
)
