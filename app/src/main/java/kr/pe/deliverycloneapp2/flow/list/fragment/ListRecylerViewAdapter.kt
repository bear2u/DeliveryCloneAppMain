package kr.pe.deliverycloneapp2.flow.list.fragment

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.list_recyclerview_item.view.*
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
        holder.title.text = items[position].name
        val requestOptions = RequestOptions().apply{
            this.placeholder(R.drawable.place_hoder_icon)
            this.error(R.drawable.no_image)
            this.circleCrop()
        }
        Glide.with(holder.itemView)
            .setDefaultRequestOptions(requestOptions)
            .load(items[position].thumbnail)
            .into(holder.thumbnail)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title = view.list_title
        val thumbnail = view.list_iv
    }

    fun updateItem(items : List<Store>) {
        Log.d("gdg", "items : ${items}")
        this.items = items
        notifyDataSetChanged()
    }

}