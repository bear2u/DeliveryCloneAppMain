package kr.pe.deliverycloneapp2.model

data class Store(
        var id: String? = null,
        var name: String? = null,
        var thumbnail : String? = null,
        var star : Double? = null,
        var review : Int? = null,
        var priority: Int? = null,
        var categoryName: String
)