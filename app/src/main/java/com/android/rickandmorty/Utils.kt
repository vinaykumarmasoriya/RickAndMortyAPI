package com.android.rickandmorty

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide


fun ViewGroup.inflate(layoutRes: Int): View =
    LayoutInflater.from(context).inflate(layoutRes, this, false)

fun loadImage(imageView: ImageView, url: String, context: Context) {
    val circularProgress = CircularProgressDrawable(context)
    circularProgress.strokeWidth = 5f
    circularProgress.centerRadius = 30f
    circularProgress.start()

    Glide.with(context)
        .asBitmap()
        .placeholder(circularProgress)
        .error(R.drawable.ic_launcher_background)
        .load(url)
        .override(100, 100)
        .into(imageView)
        .waitForLayout()
}

fun RecyclerView.isLastItemFullyVisible(): Boolean {
    val items = layoutManager?.itemCount
    val first = items?.minus(1)?.let { layoutManager?.findViewByPosition(it) }
    val isPartially = first?.let { layoutManager?.isViewPartiallyVisible(it, true, false) }
    isPartially?.let { return isPartially }
    return false
}

fun RecyclerView.onEndScroll(run: () -> Unit) {
    this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            if (isLastItemFullyVisible()) run()
        }
    })
}
