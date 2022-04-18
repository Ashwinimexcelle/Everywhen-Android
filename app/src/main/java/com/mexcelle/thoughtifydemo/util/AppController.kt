package com.mexcelle.thoughtifydemo.util

import android.app.Application
import android.os.StrictMode


val prefs: SharedPref by lazy {
    AppController.prefs!!
}

class AppController : Application() {



    companion object {
        var prefs: SharedPref? = null
        lateinit var instance: AppController
            private set
    }

    override fun onCreate() {

        //sharedPref = SharedPref(applicationContext)

        super.onCreate()
        instance = this
        prefs = SharedPref(applicationContext)
        FontsOverride.setDefaultFont(this, "MONOSPACE", "fonts/poppins_semibold.ttf");

        TypefaceUtil.overrideFont(applicationContext, "SERIF", "fonts/poppins_semibold.ttf")
        // CalligraphyConfig.initDefault("fonts/Raleway_Regular.ttf");
        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
        val detectFileUriExposure = builder.detectFileUriExposure()
    }

}
