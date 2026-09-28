package edu.ucb.project.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module


fun initKoin(appDeclaration: KoinAppDeclaration = {}, sharedModules: () -> Unit) {
    startKoin {
        appDeclaration()
        module {
            sharedModules()
        }
    }
}
