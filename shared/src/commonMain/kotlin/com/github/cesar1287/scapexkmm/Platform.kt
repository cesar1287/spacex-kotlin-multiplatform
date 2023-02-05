package com.github.cesar1287.scapexkmm

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform