package kr.pe.deliverycloneapp2.model

data class Store(
        var id: String? = null,
        var title: String? =null,
        var content: String? =null,
        var thumbnail : String? =null,
        var star : Double? =null,
        var review : Int? =null,
        var priority: Int? =null,
        var category: Category? = null
)