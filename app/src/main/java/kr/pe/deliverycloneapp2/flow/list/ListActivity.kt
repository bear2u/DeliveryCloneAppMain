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

    lateinit var category: Category

    lateinit var menus : Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.elevation = 0f

        category = intent.getParcelableExtra<Category>("item")
        menus = resources.getStringArray(R.array.menus)
        Timber.d(category.toString())

        initTab()
        initViewPager()

        fab.setOnClickListener {
            startActivity(
                Intent(this, RegisterActivity::class.java)
            )
        }
    }

    private fun initTab() {

        menus.forEach {
            tabLayout.addTab(tabLayout.newTab().setText(it))
        }
    }

    private fun initViewPager() {
        viewPager.apply {
            this.adapter = TabPagerAdapter(supportFragmentManager, menus, 3)
        }


        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{

            override fun onTabReselected(tab: TabLayout.Tab?) {
                viewPager.currentItem = tab?.position ?: 0
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab) {

                viewPager.currentItem = tab.position
            }
        })

//        viewPager.currentItem = menus.indexOf(category.title)

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }

}
