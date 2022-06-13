package com.tmob.casestudy.util

import android.graphics.Color
import android.graphics.Typeface
import android.text.SpannableStringBuilder
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.text.bold
import androidx.databinding.BindingAdapter
import com.tmob.casestudy.R

@BindingAdapter("detailShowed")
fun LinearLayout.detailShowed(value: Boolean?) {
    value?.let {
        if (it)
            this.setBackgroundColor(ContextCompat.getColor(this.context, R.color.marked))
        else
            this.setBackgroundColor(Color.WHITE)
    }
}

@BindingAdapter("textBold")
fun TextView.bold(text: String?) {
    text?.let {
        this.text = it
        this.setTypeface(null, Typeface.BOLD);
    }
}

@BindingAdapter("textfollowing")
fun TextView.setFollowing(size: Int?) {
    size?.let {
        val s = SpannableStringBuilder()
            .bold { append("Following ") }
            .append(it.toString())
        this.text = s
    }
}

@BindingAdapter("textfollowers")
fun TextView.setFollowers(size: Int?) {
    size?.let {
        val s = SpannableStringBuilder()
            .bold { append("Followers ") }
            .append(it.toString())
        this.text = s
    }

}

@BindingAdapter("roundedImg")


fun ImageView.setConfig(url: String?) {
    url?.let {
        GlideApp.with(this.context)
            .asBitmap()
            .load(url)
            .error(ContextCompat.getDrawable(this.context, android.R.drawable.stat_notify_error))
            .placeholder(android.R.drawable.ic_menu_camera)
            .circleCrop()
            .into(this)
    }

}

