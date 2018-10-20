package kr.pe.deliverycloneapp2.flow.list

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import kr.pe.deliverycloneapp2.flow.list.fragment.ListFrag

class TabPageAdapter(fm: FragmentManager, private val menus : Array<String>) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return ListFrag.newInstance(menus[position])
    }

    override fun getCount(): Int {
        return menus.size
    }
}