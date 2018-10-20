package kr.pe.deliverycloneapp2.flow.register

import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mlsdev.rximagepicker.RxImagePicker
import com.mlsdev.rximagepicker.Sources
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_register.*
import kr.pe.deliverycloneapp2.R
import kr.pe.deliverycloneapp2.model.Store
import kr.pe.deliverycloneapp2.mvp.BaseMvpActivity
import timber.log.Timber


class RegisterActivity : BaseMvpActivity<RegisterContract.View, RegisterContract.Presenter>(), RegisterContract.View{

    override var mPresenter: RegisterContract.Presenter = RegisterPresenter()

    private var compositeDisposable : CompositeDisposable = CompositeDisposable()

    private var uri : Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        title = "등록 페이지"

        btnRegister.setOnClickListener {
            registerProc()
        }

        btnGallery.setOnClickListener {
            openImagePicker(Sources.GALLERY)
        }

        btnCamera.setOnClickListener {
            openImagePicker(Sources.CAMERA)
        }

    }

    fun registerProc() {
        btnRegister.visibility = View.GONE
        registerPogressBar.visibility = View.VISIBLE
        val store = Store(
            name=tv_title.text.toString(),
            categoryName = spinner.selectedItem.toString()
        )
        Timber.d("$store")
        mPresenter.register(uri = this.uri, store = store)
    }

    fun openImagePicker(source: Sources) {
        val disposable = RxImagePicker.with(fragmentManager).requestImage(source)
            .subscribeBy(
                onNext = {
                    this.uri = it
                    val options = RequestOptions()
                    options.circleCrop()
                    Glide.with(this)
                        .load(it)
                        .apply(options)
                        .into(thumbnail)
                }
            )

        compositeDisposable.add(disposable)
    }

    // TODO 등록화면 개발
    // TODO 이미지 피커 개발
    // TODO 등록 완료
    // TODO
    override fun registerDone() {
        btnRegister.visibility = View.VISIBLE
        registerPogressBar.visibility = View.GONE
        Toast.makeText(this, "등록완료", Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}
