package kr.pe.deliverycloneapp2.flow.list

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_list.*
import kr.pe.deliverycloneapp2.R
import kr.pe.deliverycloneapp2.flow.register.RegisterActivity
import kr.pe.deliverycloneapp2.model.Category
import timber.log.Timber

class ListActivity : AppCompatActivity() {

    var category : Category? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.elevation = 0f

        category = intent.getParcelableExtra("item")
        title = category?.title

        fab.setOnClickListener {
            Timber.d("clicked fab")
            startActivity(Intent(this, RegisterActivity::class.java))
        }

//        generateSampleData()

        initTab()
        initViewPager()

    }

    private fun initTab() {

        resources.getStringArray(R.array.menus).forEach {
            tabLayout.addTab(tabLayout.newTab().setText(it))
        }
    }

    private fun initViewPager() {
        val pagerAdapter = TabPageAdapter(supportFragmentManager, tabLayout.tabCount)
        pager.adapter = pagerAdapter
        pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab) {
                pager.currentItem = tab.position
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item)
    }
}
