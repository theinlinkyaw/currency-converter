package com.tlk.currencyconverter.ui.main

import com.tlk.currencyconverter.di.module.ViewModelModule
import com.tlk.currencyconverter.di.scope.ActivityScope
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ViewModelModule::class])
interface MainComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): MainComponent
    }

    fun inject(activity: MainActivity)
}