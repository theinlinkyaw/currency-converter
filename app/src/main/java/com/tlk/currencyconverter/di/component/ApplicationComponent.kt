package com.tlk.currencyconverter.di.component

import android.content.Context
import com.tlk.currencyconverter.di.module.AppSubcomponents
import com.tlk.currencyconverter.di.module.NetworkModule
import com.tlk.currencyconverter.di.module.StorageModule
import com.tlk.currencyconverter.ui.main.MainComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppSubcomponents::class, NetworkModule::class, StorageModule::class])
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }

    fun mainComponent(): MainComponent.Factory
}