package com.example.demo


import java.io.BufferedReader
import java.io.InputStreamReader

fun readStdIn(): String {
    val buffReader = BufferedReader(InputStreamReader(System.`in`))
    return buffReader.lineSequence().joinToString(separator = System.lineSeparator())
}

