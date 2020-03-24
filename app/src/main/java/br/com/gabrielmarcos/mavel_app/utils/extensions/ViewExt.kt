package br.com.gabrielmarcos.mavel_app.utils.extensions

import android.view.View
import android.widget.ImageView
import br.com.gabrielmarcos.mavel_app.R
import com.bumptech.glide.Glide

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide(keepInLayout: Boolean = false) {
    visibility = if (keepInLayout) View.INVISIBLE else View.GONE
}

fun ImageView.loadImage(image: String){
    Glide.with(this)
        .load(image)
        .placeholder(R.drawable.ic_marvel)
        .error(R.drawable.ic_marvel)
        .into(this)
}
