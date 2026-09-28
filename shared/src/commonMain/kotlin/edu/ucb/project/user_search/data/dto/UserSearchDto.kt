package edu.ucb.project.user_search.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class UserSearchDto(
    val email: String?=null,
    @SerialName("avatar_url")
    val avatarUrl: String? =null,
)
