package kr.pe.deliverycloneapp2.flow.main

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Criteria
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.support.design.widget.NavigationView
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import kr.pe.deliverycloneapp2.flow.list.ListActivity
import kr.pe.deliverycloneapp2.model.Category
import kr.pe.deliverycloneapp2.mvp.BaseMvpActivity
import kr.pe.deliverycloneapp2.utils.ItemOffsetDecoration
import kr.pe.deliverycloneapp2.R


class MainActivity : BaseMvpActivity<MainContract.View, MainContract.Presenter>(), MainContract.View, NavigationView.OnNavigationItemSelectedListener, LocationListener {
    private lateinit var locationManager : LocationManager
    private lateinit var provider: String

    lateinit var mTitle : TextView

    override var mPresenter: MainContract.Presenter = MainPresenter()

    val items = arrayOf(
            Category(0, R.drawable.bdt_home_a_ctgr_all, title = "전체"),
            Category(1, R.drawable.bdt_home_a_ctgr_seasonal, R.drawable.bdt_btn_brown, title = "계절메뉴"),
            Category(2, R.drawable.bdt_home_a_ctgr_chicken, title = "치킨"),
            Category(3, R.drawable.bdt_home_a_ctgr_chinese, title = "중식"),
            Category(4, R.drawable.bdt_home_a_ctgr_pizza, title = "피자"),
            Category(5, R.drawable.bdt_home_a_ctgr_bossam, title = "족발,보쌈"),
            Category(6, R.drawable.bdt_home_a_ctgr_burger, title = "도시락"),
            Category(7, R.drawable.bdt_home_a_ctgr_japanese, title = "일식")
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        checkGPS()
        checkPermission()

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        mTitle = toolbar.findViewById<TextView>(R.id.toolbar_title)
        mTitle.text = getString(R.string.app_name)
        toggle.syncState()
        toolbar.setNavigationIcon(R.drawable.bdt_navi_side)
        nav_view.setNavigationItemSelectedListener(this)

        initRecyclerView()

    }

    private fun initRecyclerView() {
        mainRecyclerView.layoutManager = GridLayoutManager(getContext(), 2)
        mainRecyclerView.adapter = MainRecylerViewAdapter(items, getContext(), ::handleItem)
        val itemDecoration = ItemOffsetDecoration(getContext(), R.dimen.item_offset)
        mainRecyclerView.addItemDecoration(itemDecoration)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun updateAddress(dong : String?) {
        mTitle.text = dong ?: getString(R.string.app_name);
    }

    private fun checkPermission() {

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            updateMyLocation()
        } else {
            ActivityCompat.requestPermissions(this,arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 1);
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (permissions.size == 1 &&
                permissions[0] == android.Manifest.permission.ACCESS_FINE_LOCATION &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return
            }
        }else {
            updateMyLocation()
        }
    }


    override fun onLocationChanged(p0: Location?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onProviderEnabled(p0: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onProviderDisabled(p0: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }



    fun checkGPS() {
        val service = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val enabled = service
                .isProviderEnabled(LocationManager.GPS_PROVIDER)

        if (!enabled) {
            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            startActivity(intent)
        }
    }

    fun handleItem(item: Category) {
        startActivity(
            Intent(this, ListActivity::class.java).apply {
                this.putExtra("item", item)
            }
        )
    }

    fun updateMyLocation() {
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val criteria = Criteria()
        // 정확도
        criteria.setAccuracy(Criteria.NO_REQUIREMENT);
        // 전원 소비량
        criteria.setPowerRequirement(Criteria.NO_REQUIREMENT);
        // 고도, 높이 값을 얻어 올지를 결정
        criteria.setAltitudeRequired(false);
        // provider 기본 정보(방위, 방향)
        criteria.setBearingRequired(false);
        // 속도
        criteria.setSpeedRequired(false);
        // 위치 정보를 얻어 오는데 들어가는 금전적 비용
        criteria.setCostAllowed(true);

        provider = locationManager.getBestProvider(criteria, false);
        val location = locationManager.getLastKnownLocation(provider);
        if(location != null) {
            Log.d("gdg","${location.latitude},${location.longitude}")
            mPresenter.getAddr(lng = location.longitude, lat = location.latitude)
        }
    }

}
