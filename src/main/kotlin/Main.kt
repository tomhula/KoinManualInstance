package org.example

import org.koin.core.context.startKoin
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.plugin.module.dsl.single

fun main()
{
    startKoin {
        modules(appModule)
    }
}

interface HelloService {
    fun sayHello(): String
}

class HelloServiceImpl(private val target: String) : HelloService {
    override fun sayHello() = "Hello, $target!"
}

class MyDependantImpl(private val helloService: HelloService) : MyDependant

interface MyDependant

val appModule = module {
    single<HelloService> { HelloServiceImpl("World") }
    single<MyDependantImpl>() bind MyDependant::class
}

