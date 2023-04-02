package com.example.gptandglide.glide.ext

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import org.libpag.PAGView

/**
 * @author 唐小陆
 * Created on 2023/4/1
 * Desc:
 */
class AutoAnimationView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val pagView: PAGView = PAGView(context)
    private val imageView: ImageView = ImageView(context)

    init {
        addView(imageView)
        addView(pagView)
    }

    fun setImageDrawable(drawable: Drawable?) {
        when (drawable) {
            is PAGFileDrawable -> {
                imageView.visibility = View.GONE
                pagView.visibility = View.VISIBLE
                pagView.composition = drawable.mPAGFile
                pagView.setRepeatCount(50)
                pagView.play()
//                pagView.postDelayed({pagView.play()},128)

            }
            else -> {
                imageView.visibility = View.VISIBLE
                pagView.visibility = View.GONE
                imageView.setImageDrawable(drawable)
            }
        }
    }
}
