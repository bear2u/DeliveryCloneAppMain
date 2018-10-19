package kr.pe.deliverycloneapp2.model

import android.os.Parcel
import android.os.Parcelable
import kr.pe.deliverycloneapp2.R

data class Category (
    val no: Int,
    val resId: Int,
    val background: Int = R.drawable.bdt_btn_white,
    val title: String = "",
    val type: String = ""
) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(no)
        parcel.writeInt(resId)
        parcel.writeInt(background)
        parcel.writeString(title)
        parcel.writeString(type)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Category> {
        override fun createFromParcel(parcel: Parcel): Category {
            return Category(parcel)
        }

        override fun newArray(size: Int): Array<Category?> {
            return arrayOfNulls(size)
        }
    }

}
