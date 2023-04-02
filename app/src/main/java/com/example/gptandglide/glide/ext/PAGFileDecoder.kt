package com.example.gptandglide.glide.ext

import com.bumptech.glide.load.Options
import com.bumptech.glide.load.ResourceDecoder
import com.bumptech.glide.load.engine.Resource
import com.bumptech.glide.load.resource.SimpleResource
import org.libpag.PAGFile
import java.io.File
import java.io.FileInputStream


/**
 * @author 唐小陆
 * Created on 2023/3/31
 * Desc:
 */
class PAGFileDecoder : ResourceDecoder<File?, PAGFile?> {
    override fun decode(source: File, width: Int, height: Int, options: Options): Resource<PAGFile?>? {
        var pagFile: PAGFile? = null
        pagFile = PAGFile.Load(source.absolutePath)
        return pagFile?.let { SimpleResource(it) }
    }

    override fun handles(source: File, options: Options): Boolean {
        return isPAGFile(source)
    }

    fun isPAGFile(file: File): Boolean {
        if (!file.isFile || !file.canRead()) {
            return false
        }
        if (file.extension.toLowerCase() != "pag") {
            return false
        }
        val header = ByteArray(8)
        FileInputStream(file).use { fis ->
            if (fis.read(header) != header.size) {
                return false
            }
        }
        return header[0].toInt() == 0x50 && header[1].toInt() == 0x41 &&
                header[2].toInt() == 0x47 && header[3].toInt() == 0x20
    }
}