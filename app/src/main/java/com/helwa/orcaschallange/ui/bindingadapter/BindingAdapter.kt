package com.helwa.orcaschallange.ui.bindingadapter

import android.app.Activity
import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYouListener
import com.helwa.orcaschallange.R
import com.like.LikeButton


/*
var requestBuilder: RequestBuilder<PictureDrawable>? = null
*/


@BindingAdapter("adapter")
fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}

@BindingAdapter("isFavourite")
fun setFavourite(likeButton: LikeButton, isFavourite: Boolean? = false) {
    isFavourite?.let { likeButton.isLiked = it }
}

@BindingAdapter("imgSrc")
fun setImageSrc(imageView: ImageView, imageUrl: String) {

    GlideToVectorYou.justLoadImage(imageView.context as Activity?, Uri.parse(imageUrl), imageView)
        imageUrl.let {
            Glide.with(imageView.context).load(Uri.parse(imageUrl))
                .placeholder(R.drawable.default_crest)
                .error(R.drawable.default_crest).into(imageView)
        }
}

