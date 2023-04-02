package com.example.gptandglide.glide.ext

import android.graphics.drawable.Drawable
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.RequestManager
import org.libpag.PAGFile
import org.libpag.PAGView

/**
 * @author 唐小陆
 * Created on 2023/3/31
 * Desc:
 */


fun RequestBuilder<PAGFile>.into(pagView:PAGView){
    into(PAGViewTarget(pagView))
}

fun RequestBuilder<Drawable>.into(pagView: AutoAnimationView){
    into(AutoAnimationViewTarget(pagView))
}

fun RequestManager.asPAGFile():RequestBuilder<PAGFile>{
    return `as`(PAGFile::class.java)
}