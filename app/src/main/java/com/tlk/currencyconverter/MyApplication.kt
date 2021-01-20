package com.tlk.currencyconverter

import android.app.Application
import com.tlk.currencyconverter.di.component.ApplicationComponent
import com.tlk.currencyconverter.di.component.DaggerApplicationComponent

class MyApplication: Application() {

    val appComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(applicationContext)
    }

}