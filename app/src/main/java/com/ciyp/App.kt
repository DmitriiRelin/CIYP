package com.ciyp

import android.app.Application
import com.ciyp.di.AppComponent
import com.ciyp.di.DaggerAppComponent

class App: Application() {

    //843241


    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().applicationContext(this).build()
    }

}