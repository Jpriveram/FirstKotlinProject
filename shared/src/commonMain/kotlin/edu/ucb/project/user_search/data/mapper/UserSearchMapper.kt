package edu.ucb.project.user_search.data.mapper

import edu.ucb.project.user_search.data.dto.UserSearchDto
import edu.ucb.project.user_search.domain.model.UserInfoModel

fun UserSearchDto.toDomain() : UserInfoModel = UserInfoModel(
    email = email?:"",
    company = "",
    avatarUrl = avatarUrl?:"",
    alias = ""
)
