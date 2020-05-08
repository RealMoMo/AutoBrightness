package com.newline.autobrightness.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.jaygoo.widget.OnRangeChangedListener
import com.jaygoo.widget.RangeSeekBar
import com.newline.autobrightness.R
import com.newline.autobrightness.databinding.ActivityMainBinding
import com.newline.autobrightness.*
import com.newline.autobrightness.service.BrightnessService
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnRangeChangedListener {

    lateinit var activityMainBinding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        initView()
    }

    private fun initView() {
        sb_level1_range.setProgress(levelRange1Min, levelRange1Max)
        sb_level2_range.setProgress(levelRange2Min, levelRange2Max)
        sb_level3_range.setProgress(levelRange3Min, levelRange3Max)
        sb_level4_range.setProgress(levelRange4Min, levelRange4Max)
        sb_level5_range.setProgress(levelRange5Min, levelRange5Max)
        sb_level6_range.setProgress(levelRange6Min, levelRange6Max)

        sb_level1_range.setIndicatorTextDecimalFormat("0")
        sb_level2_range.setIndicatorTextDecimalFormat("0")
        sb_level3_range.setIndicatorTextDecimalFormat("0")
        sb_level4_range.setIndicatorTextDecimalFormat("0")
        sb_level5_range.setIndicatorTextDecimalFormat("0")
        sb_level6_range.setIndicatorTextDecimalFormat("0")


        sb_level1_range.setOnRangeChangedListener(this)
        sb_level2_range.setOnRangeChangedListener(this)
        sb_level3_range.setOnRangeChangedListener(this)
        sb_level4_range.setOnRangeChangedListener(this)
        sb_level5_range.setOnRangeChangedListener(this)
        sb_level6_range.setOnRangeChangedListener(this)

        sw_enable.setOnCheckedChangeListener { buttonView, isChecked ->
            val intent = Intent(this,BrightnessService::class.java)
            if(isChecked){
                updateConfig()
                startService(intent)
            }else{
                stopService(intent)
            }
        }


    }

    override fun onStartTrackingTouch(view: RangeSeekBar?, isLeft: Boolean) {
    }

    override fun onRangeChanged(
        view: RangeSeekBar?,
        leftValue: Float,
        rightValue: Float,
        isFromUser: Boolean
    ) {
        if(!isFromUser){
            return
        }
        when(view){
            sb_level1_range->{
                levelRange1Min = leftValue
                levelRange1Max = rightValue
            }
            sb_level2_range->{
                levelRange2Min = leftValue
                levelRange2Max = rightValue
                levelRange3Min = levelRange2Max
            }
            sb_level3_range->{
                levelRange3Min = leftValue
                levelRange3Max = rightValue
                levelRange4Min = levelRange3Max
            }
            sb_level4_range->{
                levelRange4Min = leftValue
                levelRange4Max = rightValue
                levelRange5Min = levelRange4Max
            }
            sb_level5_range->{
                levelRange5Min = leftValue
                levelRange5Max = rightValue
                levelRange6Min = levelRange5Max
            }
            sb_level6_range->{
                levelRange6Min = leftValue
                levelRange6Max = rightValue
            }
        }
    }

    override fun onStopTrackingTouch(view: RangeSeekBar?, isLeft: Boolean) {
    }


    fun updateConfig(){
        level1 = et_level1.editText?.text.toString().toInt()
        level2 = et_level2.editText?.text.toString().toInt()
        level3 = et_level3.editText?.text.toString().toInt()
        level4 = et_level4.editText?.text.toString().toInt()
        level5 = et_level5.editText?.text.toString().toInt()
        level6 = et_level6.editText?.text.toString().toInt()

        timeInterval = et_timeinterval.editText?.text.toString().toLong()

    }

    override fun onDestroy() {
        super.onDestroy()
        val intent = Intent(this,BrightnessService::class.java)
        stopService(intent)

    }


}
