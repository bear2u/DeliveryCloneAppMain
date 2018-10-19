package kr.pe.deliverycloneapp2.flow.register

import android.os.Bundle
import android.view.MenuItem
import com.mlsdev.rximagepicker.RxImagePicker
import com.mlsdev.rximagepicker.Sources
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_register.*
import kr.pe.deliverycloneapp2.R
import kr.pe.deliverycloneapp2.model.Store
import kr.pe.deliverycloneapp2.mvp.BaseMvpActivity
import timber.log.Timber

class RegisterActivity : BaseMvpActivity<RegisterContract.View, RegisterContract.Presenter>(), RegisterContract.View{

    override var mPresenter: RegisterContract.Presenter = RegisterPresenter()

    private var imageUrl : String?= null

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

        var store = Store(
            name = tv_title.text.toString(),
            categoryName = "치킨"
        )
        mPresenter.register(store)
    }

    fun openImagePicker(source: Sources) {
        RxImagePicker.with(fragmentManager).requestImage(source)
            .subscribeBy(
                onNext = {
                    mPresenter.uploadImage(it)
                }
            )
    }

    // TODO 등록화면 개발
    // TODO 이미지 피커 개발
    // TODO 등록 완료
    // TODO
    override fun registerDone() {

    }

    override fun uploadImageDone(imgUrl: String?) {
        this.imageUrl = imgUrl
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item)
    }
}
