package com.abora.nagwatask.base

import android.app.Activity
import android.app.AlertDialog
import android.view.LayoutInflater
import android.widget.TextView
import com.abora.nagwatask.R


const val fatal = "fatal"
const val app = "app"


fun Activity.myToast(msg: String) {
//    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    var dialog: AlertDialog? = null
    var toastText: TextView
    var toastBtn: TextView


    var view = LayoutInflater.from(this).inflate(R.layout.toast_dialog, null)
    toastText = view.findViewById(R.id.toastMsg)
    toastBtn = view.findViewById(R.id.toastBtn)


    toastText?.text = msg

    toastBtn?.setOnClickListener {
        if (dialog?.isShowing == true) {
            dialog?.dismiss()
        }
    }


    dialog = AlertDialog.Builder(this)
        .setView(view)
        .setCancelable(false)
        .show()

    dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)


}

//fun Fragment.myToast(msg: String) {
////    Toast.makeText(this.activity, msg, Toast.LENGTH_LONG).show()
//
//    var dialog: AlertDialog? = null
//    var toastText: TextView
//    var toastBtn: TextView
//
//    val view = LayoutInflater.from(this.activity).inflate(R.layout.toast_dialog, null)
//    toastText = view.findViewById(R.id.toastMsg)
//    toastBtn = view.findViewById(R.id.toastBtn)
//
//
//
//    toastText?.text = msg
//    toastBtn?.setOnClickListener {
//        if (dialog?.isShowing == true) {
//            dialog?.dismiss()
//        }
//    }
//
//
//    dialog = AlertDialog.Builder(this.activity)
//        .setView(view)
//        .setCancelable(false)
//        .show()
//
//    dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
//
//
//}
//
//fun Activity.confirmDailog(msg: String, confirm: (AlertDialog?) -> Unit) {
////    Toast.makeText(this.activity, msg, Toast.LENGTH_LONG).show()
//
//    var dialog: AlertDialog? = null
//    var toastText: TextView
//    var confirmBtn: TextView
//    var cancelBtn: TextView
//
//
//    val view = LayoutInflater.from(this).inflate(R.layout.confirm_dialog, null)
//    toastText = view.findViewById(R.id.toastMsg)
//    confirmBtn = view.findViewById(R.id.confirmBtn)
//    cancelBtn = view.findViewById(R.id.cancelBtn)
//
//
//
//
//    toastText?.text = msg
//    cancelBtn?.setOnClickListener {
//        if (dialog?.isShowing == true) {
//            dialog?.dismiss()
//        }
//    }
//
//    confirmBtn.setOnClickListener {
//        confirm.invoke(dialog)
//    }
//
//    dialog = AlertDialog.Builder(this)
//        .setView(view)
//        .setCancelable(false)
//        .show()
//
//    dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
//
//
//}
//
//fun Fragment.confirmDailog(msg: String, confirm: (AlertDialog?) -> Unit) {
////    Toast.makeText(this.activity, msg, Toast.LENGTH_LONG).show()
//
//    var dialog: AlertDialog? = null
//    var toastText: TextView
//    var confirmBtn: TextView
//    var cancelBtn: TextView
//
//
//    val view = LayoutInflater.from(activity).inflate(R.layout.confirm_dialog, null)
//    toastText = view.findViewById(R.id.toastMsg)
//    confirmBtn = view.findViewById(R.id.confirmBtn)
//    cancelBtn = view.findViewById(R.id.cancelBtn)
//
//
//
//
//    toastText?.text = msg
//    cancelBtn?.setOnClickListener {
//        if (dialog?.isShowing == true) {
//            dialog?.dismiss()
//        }
//    }
//
//    confirmBtn.setOnClickListener {
//        confirm.invoke(dialog)
//    }
//
//    dialog = AlertDialog.Builder(activity)
//        .setView(view)
//        .setCancelable(false)
//        .show()
//
//    dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
//
//
//}
//
//fun Activity.myToastWithClick(msg: String, toastClick: (AlertDialog) -> Unit) {
//
//    var dialog: AlertDialog? = null
//    val toastText: TextView
//    val toastBtn: TextView
//
//    val view = LayoutInflater.from(this).inflate(R.layout.toast_dialog, null)
//    toastText = view.findViewById(R.id.toastMsg)
//    toastBtn = view.findViewById(R.id.toastBtn)
//
//    toastText?.text = msg
//
//    toastBtn?.setOnClickListener {
//        if (dialog?.isShowing == true) {
//            toastClick.invoke(dialog!!)
//        }
//    }
//    dialog = AlertDialog.Builder(this)
//        .setView(view)
//        .setCancelable(false)
//        .show()
//
//    dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
//
//}
//
//fun Activity.myToastLogin(msg: String, toastClick: ToastClickLogin) {
////    Toast.makeText(this.activity, msg, Toast.LENGTH_LONG).show()
//    var dialog: AlertDialog? = null
//    var toastText: TextView
//    var toastBtn: TextView
//
//    val view = LayoutInflater.from(this).inflate(R.layout.toast_dialog, null)
//    toastText = view.findViewById(R.id.toastMsg)
//    toastBtn = view.findViewById(R.id.toastBtn)
//
//
//
//    toastText?.text = msg
//    toastBtn?.setOnClickListener {
//        if (dialog?.isShowing == true) {
//            toastClick.toastClickLogin(dialog!!)
//        }
//    }
//
//
//    dialog = AlertDialog.Builder(this)
//        .setView(view)
//        .setCancelable(false)
//        .show()
//
//    dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
//
//
//}
//
//fun Fragment.myToastWithclick(msg: String, toastClick: (AlertDialog) -> Unit) {
////    Toast.makeText(this.activity, msg, Toast.LENGTH_LONG).show()
//
//    var dialog: AlertDialog? = null
//    var toastText: TextView
//    var toastBtn: TextView
//
//    val view = LayoutInflater.from(this.activity).inflate(R.layout.toast_dialog, null)
//    toastText = view.findViewById(R.id.toastMsg)
//    toastBtn = view.findViewById(R.id.toastBtn)
//
//    toastText?.text = msg
//    toastBtn?.setOnClickListener {
//        if (dialog?.isShowing == true) {
//            toastClick.invoke(dialog!!)
//        }
//    }
//
//    dialog = AlertDialog.Builder(this.activity)
//        .setView(view)
//        .setCancelable(false)
//        .show()
//
//    dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
//
//
//}
//
//fun Fragment.myToastLogin(msg: String, toastClick: ToastClickLogin) {
////    Toast.makeText(this.activity, msg, Toast.LENGTH_LONG).show()
//
//    var dialog: AlertDialog? = null
//    var toastText: TextView
//    var toastBtn: TextView
//
//    val view = LayoutInflater.from(this.activity).inflate(R.layout.toast_dialog, null)
//    toastText = view.findViewById(R.id.toastMsg)
//    toastBtn = view.findViewById(R.id.toastBtn)
//
//
//    toastText?.text = msg
//    toastBtn?.setOnClickListener {
//        if (dialog?.isShowing == true) {
//            toastClick.toastClickLogin(dialog!!)
//        }
//    }
//
//
//    dialog = AlertDialog.Builder(this.activity)
//        .setView(view)
//        .setCancelable(false)
//        .show()
//
//    dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
//
//
//}
//
//interface ToastClick {
//    fun toastClick(dialog: Dialog)
//}
//
//interface ToastClickLogin {
//    fun toastClickLogin(dialog: Dialog)
//}
//
//fun ImageView.loadImg(url : String){
//    if(!TextUtils.isEmpty(url))
//        Picasso.get().load(url).into(this)
//}
//
//fun Activity.openRevalActivity(view: View, intent: Intent) {
//    val revealX = (view.x + view.width / 2).toInt()
//    val revealY = (view.y + view.height / 2).toInt()
//
//    intent.putExtra(RevealAnimation.EXTRA_CIRCULAR_REVEAL_X, revealX)
//    intent.putExtra(RevealAnimation.EXTRA_CIRCULAR_REVEAL_Y, revealY)
//    ActivityCompat.startActivity(this, intent, null)
//    overridePendingTransition(0, 0)
//}

suspend fun <T : Any> safeCall(call: suspend () -> NetworkResult<T>): NetworkResult<T> = try {
    call.invoke()
} catch (e: Exception) {
    e.printStackTrace()
    NetworkResult.Error(e)
}




