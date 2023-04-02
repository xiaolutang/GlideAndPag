package com.example.gptandglide

import android.app.Application
import com.bumptech.glide.Glide
import com.example.gptandglide.glide.ext.*
import org.libpag.PAGFile
import java.io.File
import java.io.InputStream


/**
 * @author 唐小陆
 * Created on 2023/4/2
 * Desc:
 */
class App: Application() {
    override fun onCreate() {
        super.onCreate()
        Glide.get(this).registry
            .prepend(File::class.java, PAGFile::class.java, PAGFileDecoder())
            .register(File::class.java, PAGFile::class.java, PAGFileTranscoder())
            .register(
                File::class.java,
                PAGFileDrawable::class.java,
                PAGFileDrawableTranscoder()
            )
            .prepend(InputStream::class.java, File::class.java, InputStreamToFileDecoder(this))
    }
}