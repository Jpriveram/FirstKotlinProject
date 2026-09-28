package edu.ucb.project.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import edu.ucb.project.login.presentation.viewmodel.LoginViewModel
import edu.ucb.project.user_search.presentation.viewmodel.UserSearchViewModel


val presentationModule = module {
    viewModelOf(::LoginViewModel)
    viewModelOf(::UserSearchViewModel)
}
