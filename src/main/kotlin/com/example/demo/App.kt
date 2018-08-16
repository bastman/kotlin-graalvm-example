package com.example.demo

import java.time.Instant

class App {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("HELLO :)")
            println(" - I am a kotlin app compiled to an executable using graalvm native")
            println(" - now: ${Instant.now()}")
            println(" - this: ${App::class.java.canonicalName}")
            println("It works :)")
        }
    }
}