package com.helloanwar.appwritekmm

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform