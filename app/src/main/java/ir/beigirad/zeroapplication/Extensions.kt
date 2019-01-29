package ir.beigirad.zeroapplication

import android.app.Activity
import android.content.Context
import android.graphics.Paint
import android.graphics.Rect
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.tabs.TabLayout
import ir.beigirad.app.R
import java.util.*
import java.util.regex.Pattern

fun ViewGroup.inflate(resource: Int): View {
    return LayoutInflater.from(this.context).inflate(resource, this, false)
}

fun ImageView.loadUrl(url: String?) {
    Glide.with(this)
//        .applyDefaultRequestOptions(RequestOptions().placeholder(R.mipmap.img_pleceholder))
        .load(url)
        .into(this)
}

fun Context.toast(text: CharSequence) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun Context.toast(textRes: Int) {
    Toast.makeText(this, textRes, Toast.LENGTH_SHORT).show()
}

fun Context.displayMetrics(): DisplayMetrics {
    val displayMetrics = DisplayMetrics()
    (this as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)
    return displayMetrics
}

fun TextView.strictThrough() {
    paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
}

fun <T : FrameLayout> T.isViewVisible(view: View): Boolean {
    val scrollBounds = Rect()
    getDrawingRect(scrollBounds)

    val _top = view.y
    val _bottom = _top + view.height

    return scrollBounds.top < _top && scrollBounds.bottom > _bottom
}

inline fun <T : Fragment> T.withArgs(argBuilder: Bundle.() -> Unit) =
    this.apply { Bundle().apply(argBuilder) }

fun <T> List<T>.random(): T? = if (this.isNotEmpty()) get(Random().nextInt(size)) else null

fun TabLayout.addTabs(vararg titles: String) {
    titles.forEachIndexed { index, it -> addTab(newTab().setText(it).setTag(index)) }
}

fun String.indexOfAll(arg: String?): List<Pair<Int, Int>> {
    val pos = ArrayList<Pair<Int, Int>>()
    val matcher = Pattern.compile(arg).matcher(this)
    while (matcher.find()) {
        pos.add(Pair(matcher.start(), matcher.end()))
    }
    return pos
}