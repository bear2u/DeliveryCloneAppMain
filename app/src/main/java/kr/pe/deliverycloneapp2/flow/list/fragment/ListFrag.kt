package kr.pe.deliverycloneapp2.flow.list.fragment

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.frag_list.*
import kr.pe.deliverycloneapp2.R
import kr.pe.deliverycloneapp2.model.Store
import kr.pe.deliverycloneapp2.mvp.BaseMvpFragment
import kr.pe.deliverycloneapp2.utils.ItemOffsetDecoration

class ListFrag : BaseMvpFragment<ListContract.View, ListContract.Presenter>(), ListContract.View {

    private var items : ArrayList<Store> = ArrayList()

    override var mPresenter: ListContract.Presenter = ListPresenter()

    lateinit var act : Activity

    private lateinit var listRecylerViewAdapter : ListRecylerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.frag_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        act = activity as Activity
        initRecyclerView()

        mPresenter.getStores("")
    }



    private fun initRecyclerView() {
        listRecyclerView.layoutManager = LinearLayoutManager(getContext())
        listRecylerViewAdapter = ListRecylerViewAdapter(items, act, ::handleItem)
        listRecyclerView.adapter = listRecylerViewAdapter
        val itemDecoration = ItemOffsetDecoration(act, R.dimen.list_item_offset)
        listRecyclerView.addItemDecoration(itemDecoration)
    }

    fun handleItem(item: Store) {

    }

    override fun updateList(items: ArrayList<Store>) {
        listRecylerViewAdapter.updateItem(items)
    }
}