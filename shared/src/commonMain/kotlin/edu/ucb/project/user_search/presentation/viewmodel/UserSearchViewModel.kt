package edu.ucb.project.user_search.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.ucb.project.user_search.domain.usecase.SearchUserUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
class UserSearchViewModel(val searchUserUseCase: SearchUserUseCase): ViewModel() {
    private val _state = MutableStateFlow<UserSearchState>(UserSearchState())
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<UserSearchEffects>()
    val effect = _effect.asSharedFlow()

    fun emitEvent(event: UserSearchEvents) {
        when (event) {
            is UserSearchEvents.OnAliasChange -> {
                _state.update { it.copy(alias = event.value) }
            }

            UserSearchEvents.OnBack -> {
                emitEffect(UserSearchEffects.NavigateToBack)
            }

            UserSearchEvents.OnSubmit -> {
                viewModelScope.launch {
                    searchUserUseCase.invoke(_state.value.alias).fold(
                        onSuccess = { userInfo ->
                            _state.update {
                                it.copy(email = userInfo.email, avatarUrl = userInfo.avatarUrl)
                            }
                        },
                        onFailure = {}
                    )
                }

            }
        }
    }

    private fun emitEffect(effect: UserSearchEffects) {
        viewModelScope.launch {
            _effect.emit(effect)
        }
    }
}
