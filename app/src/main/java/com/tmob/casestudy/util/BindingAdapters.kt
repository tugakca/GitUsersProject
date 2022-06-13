package com.tmob.casestudy.util

import android.content.Context
import android.graphics.Typeface
import android.text.SpannableStringBuilder
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.text.bold
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.request.RequestOptions
import com.tmob.casestudy.R


fun ImageView.downloadFromUrl(url: String?, progressDrawable: CircularProgressDrawable) {

    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.mipmap.ic_launcher_round)

    GlideApp.with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)

}

fun placeholderProgressBar(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 40f
        start()
    }
}

@BindingAdapter("android:downloadUrl")
fun downloadImage(view: ImageView, url: String?) {
    view.downloadFromUrl(url, placeholderProgressBar(view.context))
}

@BindingAdapter("textBold")
fun TextView.bold(text: String?) {
    text?.let {
        this.text = text
    }
    this.setTypeface(null, Typeface.BOLD);
}

@BindingAdapter("textfollowing")
fun TextView.setFollowing(size: Int) {
    val s = SpannableStringBuilder()
        .bold { append("Following ") }
        .append(size.toString())
    this.text=s


}

@BindingAdapter("textfollowers")
fun TextView.setFollowers(size: Int) {
    val s = SpannableStringBuilder()
        .bold { append("Followers ") }
        .append(size.toString())
    this.text=s
}

@BindingAdapter("roundedImg")
fun ImageView.setConfig(url:String) {
        GlideApp.with(this.context)
            .asBitmap()
            .load(url).error(ContextCompat.getDrawable(this.context, android.R.drawable.stat_notify_error)).placeholder(android.R.drawable.ic_menu_camera)
            .circleCrop()
            .into(this)
    }

