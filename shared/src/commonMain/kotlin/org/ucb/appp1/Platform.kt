package org.ucb.appp1

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
