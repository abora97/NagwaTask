package com.abora.nagwatask.base

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.abora.nagwatask.R
import com.abora.nagwatask.application.MyApp
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import org.json.JSONException
import org.json.JSONObject
import java.util.*
import javax.inject.Inject


abstract class BaseActivity<T : ViewDataBinding> : DaggerAppCompatActivity() {

    lateinit var dataBinding: T


    var dialog: AlertDialog? = null

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    abstract fun resourceId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, resourceId())
        dataBinding.lifecycleOwner = this

        changeLang()

        window.decorView.layoutDirection = if (MyApp.language == "ar") {
            View.LAYOUT_DIRECTION_RTL
        } else {
            View.LAYOUT_DIRECTION_LTR
        }



    }


    protected fun changeLang() {

        val dm = resources.displayMetrics
        val conf = resources.configuration

        val locale = Locale(MyApp.language)
        if (conf.locale != locale) {
            conf.setLayoutDirection(locale)
            conf.setLocale(locale)
            resources.updateConfiguration(conf, dm)
//            recreate()
//            overridePendingTransition(0, 0)

        }
    }


    fun toggleLoadingDialog(show: Boolean) {

        if (dialog == null) {
            dialog = AlertDialog.Builder(this)
                .setView(R.layout.progress)
                .setCancelable(false)
                .create()

            dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)

        }

        if (!show)
            dialog?.dismiss()
        else if (show)
            dialog?.show()


    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.no_change, R.anim.exit_slide_right)
    }

    override fun onDestroy() {
        super.onDestroy()
        dialog?.dismiss()
    }

}