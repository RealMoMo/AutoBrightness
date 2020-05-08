package com.newline.autobrightness.service

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.os.Message
import android.provider.Settings
import android.util.Log
import com.mstar.android.tvapi.common.TvManager
import com.mstar.android.tvapi.common.exception.TvCommonException
import com.newline.autobrightness.*


class BrightnessService : Service() {


    private var handler: Handler? = null
    private var ambientLightValue = 100

    override fun onCreate() {
        super.onCreate()
        Log.d("realmo","init")
        initHandler()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }


    override fun onDestroy() {
        super.onDestroy()
        handler?.removeCallbacksAndMessages(null)
        handler=  null
    }


    private fun initHandler() {
        handler = object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message?) {
                ambientLightValue = getAmbientLight()
                Log.d("realmo","light:"+ambientLightValue)
                setBackLightLevel(ambientLightValue)

                sendEmptyMessageDelayed(0x666, timeInterval)
            }
        }
        handler?.sendEmptyMessageDelayed(0x666, timeInterval)
    }

    private fun getAmbientLight(): Int {
        try {
            return TvManager.getInstance()
                .setTvosCommonCommand("GetAmbientLightValue")[0].toInt()
        } catch (e: TvCommonException) {
            e.printStackTrace()
        }
        return 100
    }



    private fun setBackLightLevel(level: Int) {
        when(level){
            in levelRange6Min .. 70000F-> setBackLight(level6)
            in levelRange5Min .. levelRange5Max-> setBackLight(level5)
            in levelRange4Min .. levelRange4Max-> setBackLight(level4)
            in levelRange3Min .. levelRange3Max-> setBackLight(level3)
            in levelRange2Min .. levelRange2Max-> setBackLight(level2)
            in levelRange1Min .. levelRange1Max-> setBackLight(level1)
        }

    }

    private fun setBackLight(backLight:Int){
        Settings.System.putInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS, backLight);
    }
}