package kr.pe.deliverycloneapp2.flow.register

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_register.*
import kr.pe.deliverycloneapp2.R
import kr.pe.deliverycloneapp2.model.Category
import kr.pe.deliverycloneapp2.model.Store
import kr.pe.deliverycloneapp2.mvp.BaseMvpActivity
import kr.pe.deliverycloneapp2.utils.getRandomUUID

class RegisterActivity : BaseMvpActivity<RegisterContract.View,
        RegisterContract.Presenter>(), RegisterContract.View {
    override fun registerDone() {
        Toast.makeText(this, "등록 완료", Toast.LENGTH_SHORT).show()
        finish()
    }

    override var mPresenter: RegisterContract.Presenter = RegisterPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.elevation = 0f

        title = "업체 등록"

        btnRegister.setOnClickListener{
            register()
        }


    }

    private fun register() {
        val title = tv_title.text.toString()
        val content = tv_content.text.toString()
        var category = spinner.selectedItem.toString()

        mPresenter.register(
            Store().apply {
                this.id = getRandomUUID()
                this.title = title
                this.category = Category(title = category)
                this.content = content
            }
        )
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }

}
