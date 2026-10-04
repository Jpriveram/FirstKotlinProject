package edu.ucb.project.di

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform