package com.example.gptandglide.glide.ext

import android.content.Context
import android.util.Log
import com.bumptech.glide.load.Options
import com.bumptech.glide.load.ResourceDecoder
import com.bumptech.glide.load.engine.Resource
import com.bumptech.glide.load.resource.SimpleResource
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

/**
 * @author 唐小陆
 * Created on 2023/4/1
 * Desc:
 */
class InputStreamToFileDecoder(private val context: Context) : ResourceDecoder<InputStream, File> {

    override fun handles(source: InputStream, options: Options): Boolean {
        // 可以在此处添加适当的逻辑来判断给定的输入流是否可以处理。
        // 这里假设任何输入流都可以处理。
        val result=  isPAGStream(source)
        return result
    }

    private fun isPAGStream(inputStream: InputStream): Boolean {
        val header = ByteArray(4)
        inputStream.mark(4)
        if (inputStream.read(header) != header.size) {
            return false
        }
        inputStream.reset()
        return header[0].toInt() == 0x50 && header[1].toInt() == 0x41 &&
                header[2].toInt() == 0x47
    }

    override fun decode(source: InputStream, width: Int, height: Int, options: Options): Resource<File>? {
        val file = createTempFile(context.cacheDir)
        source.use { input ->
            FileOutputStream(file).use { output ->
                input.copyTo(output)
            }
        }
        return SimpleResource(file)
    }

    private fun createTempFile(cacheDir: File): File {
        val fileName = "temp_${System.currentTimeMillis()}"
        val file =  File(cacheDir, fileName)
        Log.e("TAG", "InputStreamToFileDecoder createTempFile: ${file.absolutePath}", )
        return file
    }
}