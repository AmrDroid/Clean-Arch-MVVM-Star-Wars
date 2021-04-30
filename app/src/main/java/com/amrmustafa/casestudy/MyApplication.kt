package com.amrmustafa.casestudy
import android.app.Application

import com.amrmustafa.casestudy.di.networkModule
import com.amrmustafa.casestudy.di.viewModelsModule
import com.amrmustafa.casestudy.di.localDataSourceModule
import com.amrmustafa.casestudy.di.remoteDataSourceModule
import com.amrmustafa.casestudy.di.useCasesModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

internal class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(
                networkModule,
                viewModelsModule,
                localDataSourceModule,
                remoteDataSourceModule,
                useCasesModule
            )
        }

    }

}