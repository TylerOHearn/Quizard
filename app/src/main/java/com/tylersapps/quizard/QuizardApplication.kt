package com.tylersapps.quizard

import android.app.Application
import timber.log.Timber

class QuizardApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}