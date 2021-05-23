package com.zzp.wfdemo.common.entity.test

import com.fasterxml.jackson.databind.ObjectMapper
import com.zzp.wfdemo.result.ResponseResult
import reactor.core.publisher.Flux
import java.util.stream.Collector
import java.util.stream.Collectors


fun main(args: Array<String>) {
    val integerFlux = Flux.just(1, 5,6,7,89,90,7)
    val collect = integerFlux.collect({Result<Int>()},{ u, v->
        u.data.add(v)
        u.msg = "msg"
    })
    collect.subscribe(System.out::println)
    /*val mutableListOf = mutableListOf<Int>()
    mutableListOf.add(1)
    println(mutableListOf is ArrayList)*/
}

private class Result<T>{
    var data = mutableListOf<T>()
    var msg:String = ""

    override fun toString(): String {
        val om = ObjectMapper()
        return om.writeValueAsString(this)
    }
}