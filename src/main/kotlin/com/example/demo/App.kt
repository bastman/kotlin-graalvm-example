package com.example.demo

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef

val YAML = ObjectMapper(YAMLFactory()).findAndRegisterModules()
val JSON = jacksonObjectMapper().findAndRegisterModules()

private typealias HashMap = Map<String, Any?>

class App {

    fun exec() = yml1()

    fun json1() {
        val s = """
                {
                    "bar":"XXXXXX",
                    "data": {
                        "x1":"X1",
                        "x2":"X2"
                    }
                }
            """.trimIndent()
        println(s)

        val foo = Foo(
                bar = "123"
        )
        println(foo)

        val asJson: String = JSON.writeValueAsString(foo)

        println("as json: $asJson")

        println("===== deserialize ....")
        val f: HashMap = JSON.readValue(s, jacksonTypeRef<HashMap>())
        println(f)
    }

    fun yml1() {
        val source = readStdIn()
        println(source)
        println("===== deserialize ....")
        val f: HashMap = YAML.readValue(source, jacksonTypeRef<HashMap>())
        println(f)
        println("===== serialize ....")
        val sink = YAML.writeValueAsString(f)
        println(sink)
    }


    companion object {
        @JvmStatic
        fun main(args: Array<String>) = App().exec()


    }
}

data class Foo(val bar: String)

