package com.abora.nagwatask.base

import android.graphics.Color
import android.os.CountDownTimer
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.View.GONE
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abora.nagwatask.R
import com.google.android.material.textfield.TextInputLayout
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


@BindingAdapter("app:errorText")
fun setErrorMessage(view: TextInputLayout, errorMessage: String?) {
    view.error = errorMessage
}

@BindingAdapter("app:cardBg")
fun cardBg(cardView: CardView,color : String?) {
    color?: return
    cardView.setCardBackgroundColor(Color.parseColor(color))
}


@BindingAdapter("app:timer")
fun timer(textView: TextView, dateString : String?) {

    if(TextUtils.isEmpty(dateString)){
        textView.visibility = GONE
        return
    }

    if(dateString?.contains("visible") == true){
        return
    }

    val simplreDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val date = simplreDateFormat.parse(dateString)
    val milliSeconds = date.time - Calendar.getInstance().time.time

    val days = TimeUnit.MILLISECONDS.toDays(milliSeconds)

    if(days > 0){
        textView.text = "$days ${textView.context.getString(R.string.left)}"
    }else {
        object :  CountDownTimer(milliSeconds,1000){
            override fun onFinish() {
                textView.text = "Expired"
            }

            override fun onTick(tick: Long) {

                textView.text = String.format("%02d:%02d:%02d",TimeUnit.MILLISECONDS.toHours(tick),TimeUnit.MILLISECONDS.toMinutes(tick)%60,TimeUnit.MILLISECONDS.toSeconds(tick)%60)
            }
        }.start()
    }

}


@BindingAdapter("app:colorForText")
fun colorForText(textView: TextView, color : String) {
    textView.setTextColor(Color.parseColor(color))
}


@BindingAdapter("app:loadImg")
fun loadImg(imageView: ImageView, url : String?) {
    if(!TextUtils.isEmpty(url)){
        Picasso.get().load(url).into(imageView)
    }
}
