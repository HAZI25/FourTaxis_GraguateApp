package com.example.fourtaxis_graduateapp.presentation

import android.app.Application
import com.example.fourtaxis_graduateapp.di.DaggerApplicationComponent

class FourTaxisApplication: Application() {
    val component by lazy {
        DaggerApplicationComponent.create()
    }
}