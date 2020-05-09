package com.newline.autobrightness.ui

import android.graphics.Color
import android.graphics.drawable.AdaptiveIconDrawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.setPadding
import com.newline.autobrightness.R
import com.newline.autobrightness.utils.CircleImageView
import com.newline.autobrightness.utils.IconShapeOverrideHelper


/**
 * @name AutoBrightness
 * @author Realmo
 * @email   momo.weiye@gmail.com
 * @version 1.0.0
 * @time 2020/5/8 19:02
 * @describe
 */
class RoundIconActivity : AppCompatActivity() {

    var patch =
        "M50 0A50 50,0,1,1,50 100A50 50,0,1,1,50 0"

    private lateinit var imageView: AppCompatImageView

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_round_icon)
        IconShapeOverrideHelper.apply(patch)
        imageView = findViewById(R.id.iv_round)
        //"com.mobisystems.office"
        //"org.chromium.chrome"
        //"org.kman.AquaMail"
        val drawable = IconShapeOverrideHelper.getAppIcon(packageManager,"org.chromium.chrome")
        if(drawable is AdaptiveIconDrawable){

        }else{
            imageView.setPadding(20)
            imageView.setBackgroundResource(R.drawable.shape_iv_round)
        }

        imageView.setImageDrawable(drawable)


//        val bitmap = IconShapeOverrideHelper.getAppIcon2(packageManager,packageName)
//        imageView.setImageBitmap(bitmap)

    }


}