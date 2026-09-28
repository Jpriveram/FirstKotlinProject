package edu.ucb.project.login.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.ucb.project.login.domain.usecase.LoginUseCase
import edu.ucb.project.login.domain.vo.Email
import edu.ucb.project.login.domain.vo.Password
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(val loginUseCase: LoginUseCase): ViewModel(){

    private val _state = MutableStateFlow<LoginState>(LoginState())
    val state = _state.asStateFlow()

    private val _effects = MutableSharedFlow<LoginEffect>()
    val effects = _effects.asSharedFlow()

    private fun emitEffect(effect: LoginEffect) {
        viewModelScope.launch {
            _effects.emit(effect)
        }
    }

    fun emitEvent(event: LoginEvent) {
        when(event) {
            is LoginEvent.OnUsernameChanged -> {
                _state.update { it.copy(username = event.value) }
            }
            is LoginEvent.OnPasswordChanged -> {
                _state.update { it.copy(password = event.value) }
            }
            LoginEvent.OnLoginClick -> {
                emitEffect(LoginEffect.NavigateToHome)
                var isValid = true
                if (state.value.username.isBlank()) {
                    emitEffect(LoginEffect.ShowToast("The email field is required"))
                    isValid = false
                } else if (state.value.password.isBlank()) {
                    emitEffect(LoginEffect.ShowToast("The password field is required"))
                    isValid = false
                }
                if (isValid) {
                    viewModelScope.launch {
                        loginUseCase.invoke(
                            Email(state.value.username),
                            Password(state.value.password)
                        )
                            .fold(
                                onSuccess = {
                                    emitEffect(LoginEffect.NavigateToHome)
                                },
                                onFailure = {
                                    emitEffect(LoginEffect.ShowToast("Credential Invalid"))
                                }
                            )
                    }

                }
            }
        }
    }

}



