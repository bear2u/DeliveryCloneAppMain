package kr.pe.deliverycloneapp2.flow.list.fragment

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_list.*
import kr.pe.deliverycloneapp2.R
import kr.pe.deliverycloneapp2.model.Store
import kr.pe.deliverycloneapp2.mvp.BaseMvpFragment
import kr.pe.deliverycloneapp2.utils.ItemOffsetDecoration
import timber.log.Timber


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "categoryName"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ListFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ListFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class ListFragment : BaseMvpFragment<ListFragmentContract.View, ListFragmentContract.Presenter>(), ListFragmentContract.View {


    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var listener: OnFragmentInteractionListener? = null
    private lateinit var listRecylerViewAdapter : ListRecylerViewAdapter

    override var mPresenter: ListFragmentContract.Presenter = ListFragmentPresenter()

    lateinit var act : Activity

    private var items : ArrayList<Store> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerView()


        //mPresenter.input(100)
    }

    override fun onResume() {
        super.onResume()
        mPresenter.fetchItems(param1 ?: "")
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context);
        act = context as Activity
    }

    override fun fetchItemsDone(items: List<Store>) {
        Timber.d("update done items : $items")
        listRecylerViewAdapter.updateItem(items)
    }

    override fun output(count: Int) {
        Log.d("gdg", "#9 ui $count")
    }

    private fun initRecyclerView() {
        listRecyclerView.layoutManager = LinearLayoutManager(context)
        listRecylerViewAdapter = ListRecylerViewAdapter(items, act, ::handleItem)
        listRecyclerView.adapter = listRecylerViewAdapter
        val itemDecoration = ItemOffsetDecoration(act, R.dimen.list_item_offset)
        listRecyclerView.addItemDecoration(itemDecoration)
        listRecyclerView.setEmptyView(empty_view)
    }

    fun handleItem(item: Store) {

    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(categoryName: String) =
            ListFragment().apply {
                Timber.d("categoryName: $categoryName")
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, categoryName)
//                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
