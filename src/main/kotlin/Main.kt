package org.example

import org.koin.dsl.bind
import org.koin.dsl.koinApplication
import org.koin.dsl.module
import org.koin.plugin.module.dsl.create
import org.koin.plugin.module.dsl.single

fun main()
{
    val koin = koinApplication {
        modules(appModule)
    }.koin

    val dependant = koin.get<MyDependant>()
    dependant.doStuff()
}

interface HelloService {
    fun sayHello(): String
}

class HelloServiceImpl(private val target: String) : HelloService {
    override fun sayHello() = "Hello, $target!"
}

interface MyDependant
{
    fun doStuff()
}
class MyDependantImpl(private val helloService: HelloService) : MyDependant
{
    override fun doStuff()
    {
        println(helloService.sayHello())
    }
}

private fun helloService(): HelloService = HelloServiceImpl("World")

val appModule = module {
    single { create(::helloService) }
    single<MyDependantImpl>() bind MyDependant::class
}

