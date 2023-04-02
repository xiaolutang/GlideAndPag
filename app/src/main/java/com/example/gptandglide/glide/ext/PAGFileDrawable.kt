package com.example.gptandglide.glide.ext

import android.graphics.Canvas
import android.graphics.drawable.Drawable
import org.libpag.PAGFile

/**
 * @author 唐小陆
 * Created on 2023/3/31
 * Desc:
 */
class PAGFileDrawable(var mPAGFile: PAGFile) : Drawable() {

    override fun getIntrinsicWidth(): Int {
        return mPAGFile.width()
    }

    override fun getIntrinsicHeight(): Int {
        return mPAGFile.height()
    }

    override fun draw(canvas: Canvas) {
        PAGFile.Load("")
    }

    override fun setAlpha(alpha: Int) {
        // do nothing
    }

    override fun setColorFilter(colorFilter: android.graphics.ColorFilter?) {
        // do nothing
    }

    override fun getOpacity(): Int {
        return android.graphics.PixelFormat.TRANSLUCENT
    }

}