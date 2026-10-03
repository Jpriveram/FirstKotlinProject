package edu.ucb.project.di


import edu.ucb.project.config.AppDatabase
import edu.ucb.project.config.getDatabaseBuilder
import org.koin.core.module.Module
import org.koin.dsl.module


actual fun platformModule(): Module = module {
    single<AppDatabase> {
        getDatabaseBuilder(get()).build()
    }
}