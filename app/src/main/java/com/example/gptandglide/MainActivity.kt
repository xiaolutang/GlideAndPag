package com.example.gptandglide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.gptandglide.glide.ext.AutoAnimationView
import com.example.gptandglide.glide.ext.asPAGFile
import com.example.gptandglide.glide.ext.into
import org.libpag.PAGView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setContentView(R.layout.activity_main)
        val pagView = findViewById<PAGView>(R.id.pagView)
        val pagView1 = findViewById<PAGView>(R.id.pagView1)
        val aniLeftPag = findViewById<AutoAnimationView>(R.id.aniLeftPag)
        val aniRightImage = findViewById<AutoAnimationView>(R.id.aniRightImage)
        val tvTest = findViewById<TextView>(R.id.tvTest)
        tvTest.setOnClickListener {
            Glide.with(this).asPAGFile().load("https://github.com/Tencent/libpag/blob/main/assets/data_video.pag?raw=true").into(pagView)
            Glide.with(this).asPAGFile().load("https://github.com/Tencent/libpag/blob/main/assets/RangeSelectorTriangleEaseHighAndLow.pag?raw=true").into(pagView1)
            Glide.with(this).load("https://github.com/Tencent/libpag/blob/main/assets/TrackingAnimatorVertical.pag?raw=true").into(aniLeftPag)
            Glide.with(this).load("https://img1.baidu.com/it/u=3007048469,3759326707&fm=253&fmt=auto&app=120&f=JPEG?w=889&h=500").into(aniRightImage)
        }
    }
}