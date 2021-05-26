package com.sample.demo

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.blankj.utilcode.util.Utils
import frame.library.db.RoomProvider

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Utils.init(this) // UtilCode
        RoomProvider.init(this) // Room
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

}