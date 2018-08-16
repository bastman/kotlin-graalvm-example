package com.example.demo

import java.time.Instant

class App {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("HELLO !!! ${Instant.now()}")
        }
    }
}