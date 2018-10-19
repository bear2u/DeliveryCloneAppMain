package kr.pe.deliverycloneapp2.mvp

import android.content.Context
import android.support.annotation.StringRes

interface BaseMvpView {

    fun getContext(): Context?

    fun showError(error: String?)

    fun showError(@StringRes stringResId: Int)

    fun showMessage(@StringRes strResId: Int)

    fun showMessage(message: String)
}