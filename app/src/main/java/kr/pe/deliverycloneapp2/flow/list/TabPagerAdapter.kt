package kr.pe.deliverycloneapp2.flow.list

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import kr.pe.deliverycloneapp2.flow.list.fragment.ListFragment
import timber.log.Timber

class TabPagerAdapter(fm: FragmentManager,private val menus: Array<String>, private val tabCount: Int)
    : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        Timber.d("position : $position")
        return ListFragment.newInstance(menus[position])
    }

    override fun getCount(): Int = tabCount

}