package com.example.filmsearch.presentation

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.filmsearch.R
import com.google.android.material.snackbar.Snackbar

fun View.showSnackBar(
    text: String,
    actionText: String,
    action: (View) -> Unit,
    length: Int = Snackbar.LENGTH_INDEFINITE
) {
    Snackbar.make(this, text, length).setAction(actionText, action).show()
}

fun ImageView.loadPicture(imageURL: String) {
    Glide.with(this)
        .load(imageURL)
        .apply(RequestOptions.bitmapTransform(RoundedCorners(28)))
        .error(R.drawable.fortress)
        .placeholder(R.drawable.fortress)
        .into(this)
}