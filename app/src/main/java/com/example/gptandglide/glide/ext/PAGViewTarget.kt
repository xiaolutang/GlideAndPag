package com.example.gptandglide.glide.ext

import android.graphics.drawable.Drawable
import com.bumptech.glide.request.target.CustomViewTarget
import com.bumptech.glide.request.transition.Transition
import org.libpag.PAGFile
import org.libpag.PAGView

/**
 * @author 唐小陆
 * Created on 2023/3/31
 * Desc:
 */
class PAGViewTarget(
    private val view: PAGView
) : CustomViewTarget<PAGView, PAGFile>(view) {

    override fun onResourceLoading(placeholder: Drawable?) {
        view.stop()
    }

    override fun onResourceCleared(placeholder: Drawable?) {
        view.stop()
    }

    override fun onResourceReady(resource: PAGFile, transition: Transition<in PAGFile>?) {
        view.composition = resource
        view.setRepeatCount(50)
        view.play()
    }

    override fun onLoadFailed(errorDrawable: Drawable?) {

    }
}
