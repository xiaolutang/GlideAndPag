package com.example.gptandglide.glide.ext

import android.util.Log
import com.bumptech.glide.load.Options
import com.bumptech.glide.load.engine.Resource
import com.bumptech.glide.load.resource.SimpleResource
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder
import org.libpag.PAGFile
import java.io.File
import java.io.FileInputStream

/**
 * @author 唐小陆
 * Created on 2023/4/1
 * Desc:
 */
class PAGFileDrawableTranscoder : ResourceTranscoder<File?, PAGFileDrawable?> {

    override fun transcode(toTranscode: Resource<File?>, options: Options): Resource<PAGFileDrawable?>? {
        val file = toTranscode.get()
        if (!isPAGFile(file)) {
            return null
        }
        val pagFile = PAGFile.Load(file.absolutePath)
        Log.e("TAG", "PAGFileDrawableTranscoder transcode:  ${file.absolutePath}  $pagFile")
        val drawable = PAGFileDrawable(pagFile)
        return SimpleResource(drawable)
    }

    private fun isPAGFile(file: File): Boolean {
        if (!file.isFile || !file.canRead()) {
            return false
        }
        val header = ByteArray(3)
        FileInputStream(file).use { fis ->
            if (fis.read(header) != header.size) {
                return false
            }
        }
        return header[0].toInt() == 0x50 && header[1].toInt() == 0x41 &&
                header[2].toInt() == 0x47
    }
}
