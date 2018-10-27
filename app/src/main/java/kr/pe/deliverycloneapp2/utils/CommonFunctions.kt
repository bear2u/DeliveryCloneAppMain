package kr.pe.deliverycloneapp2.utils

import android.support.v7.widget.RecyclerView
import java.util.*

fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
    itemView.setOnClickListener {
        event.invoke(adapterPosition, itemViewType)
    }
    return this
}

fun getRandomUUID() =  UUID.randomUUID().toString()