package com.example.gptandglide.glide.ext

import android.content.Context
import com.bumptech.glide.load.Options
import com.bumptech.glide.load.ResourceDecoder
import com.bumptech.glide.load.engine.Resource
import com.bumptech.glide.load.resource.file.FileResource
import java.io.*

/**
 * @author 唐小陆
 * Created on 2023/4/2
 * Desc:
 */
class PagDecoder(private val context: Context) : ResourceDecoder<InputStream, File> {
    override fun decode(source: InputStream, width: Int, height: Int, options: Options): Resource<File>? {
        // 将输入流保存为本地文件
        val file = saveInputStreamToFile(source)
        // 返回本地文件的Resource对象
        return FileResource(file)
    }

    override fun handles(source: InputStream, options: Options): Boolean {
        // 判断输入流是否为PAG类型
        val header = ByteArray(8)
        source.mark(8)
        source.read(header)
        source.reset()
//        val result = isPAGFile(header)
        val result = isPag(source)
        return result
    }

    private fun isPAGFile(header: ByteArray): Boolean {
        // 判断文件头部信息是否与 PAG 文件的头部信息相同
        return header[0] == 'P'.toByte() &&
                header[1] == 'A'.toByte() &&
                header[2] == 'G'.toByte() &&
                header[3] == 'F'.toByte()
    }

//    fun isPag(input: InputStream): Boolean {
//        val header = ByteArray(8)
//        input.mark(8)
//        input.read(header)
//        input.reset()
//        return header[0] == 0x50.toByte() && // 80
//                header[1] == 0x41.toByte() && // 65
//                header[2] == 0x47.toByte() && // 71
//                header[3] == 0x0D.toByte() && // 13
//                header[4] == 0x0A.toByte() && // 10
//                header[5] == 0x1A.toByte() && // 26
//                header[6] == 0x0A.toByte() && // 10
//                header[7] == 0x00.toByte()    // 0
//    }

    fun isPag(inputStream: InputStream): Boolean {
        val pagFileHeader = byteArrayOf(0x50, 0x41, 0x47)
        val headerBuffer = ByteArray(3)
        inputStream.mark(3)
        inputStream.read(headerBuffer)
        inputStream.reset()
        return headerBuffer.contentEquals(pagFileHeader)
    }


    @Throws(IOException::class)
    private fun saveInputStreamToFile(input: InputStream): File {
        // 创建一个临时文件来保存输入流
        val outputDir: File = context.cacheDir
        val outputFile = File.createTempFile("temp", ".tmp", outputDir)

        // 使用流的方式将输入流写入文件
        val output: OutputStream = FileOutputStream(outputFile)
        val buffer = ByteArray(4 * 1024) // 4KB
        var bytesRead: Int
        while (input.read(buffer).also { bytesRead = it } != -1) {
            output.write(buffer, 0, bytesRead)
        }
        output.flush()
        output.close()

        return outputFile
    }
}
