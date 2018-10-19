package kr.pe.deliverycloneapp2.flow.list.fragment

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.main_recyclerview_item.view.*
import kr.pe.deliverycloneapp2.R
import kr.pe.deliverycloneapp2.model.Store
import kr.pe.deliverycloneapp2.utils.listen

class ListRecylerViewAdapter(
    private var items: List<Store>,
    private val context: Context,
    private val clickListener : (item: Store) -> Unit
) : RecyclerView.Adapter<ListRecylerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_recyclerview_item, parent, false)
        return ViewHolder(view).listen{ pos, type ->
            clickListener(items[pos])
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.source.setImageResource(items[position].)
//        holder.source.setBackgroundResource(items[position].background)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val source = view.btnIcon
    }

    fun updateItem(items : List<Store>) {
        Log.d("gdg", "items : ${items.size}")
        this.items = items
        notifyDataSetChanged()
    }

}