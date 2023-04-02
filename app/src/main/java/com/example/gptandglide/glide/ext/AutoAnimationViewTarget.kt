package com.example.gptandglide.glide.ext

import android.graphics.drawable.Drawable
import com.bumptech.glide.request.target.CustomViewTarget
import com.bumptech.glide.request.transition.Transition

/**
 * @author 唐小陆
 * Created on 2023/4/1
 * Desc:
 */
class AutoAnimationViewTarget(
    private val view: AutoAnimationView
) : CustomViewTarget<AutoAnimationView, Drawable>(view) {

    override fun onResourceLoading(placeholder: Drawable?) {
        view.setImageDrawable(null)
    }

    override fun onResourceCleared(placeholder: Drawable?) {

    }

    override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
        view.setImageDrawable(resource)
    }

    override fun onLoadFailed(errorDrawable: Drawable?) {

    }
}

