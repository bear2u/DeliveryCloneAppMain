package kr.pe.deliverycloneapp2.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kr.pe.deliverycloneapp2.R

@Parcelize
data class Category (
    val no: Int? = null,
    val resId: Int? = null,
    val background: Int = R.drawable.bdt_btn_white,
    val title: String = ""
) : Parcelable
