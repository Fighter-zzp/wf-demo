package com.zzp.wfdemo.handler

import com.zzp.wfdemo.result.ResponseResult
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.HandlerResult
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

/**
 * 无法解决fulx的处理
 */
/*
@Aspect
@Configuration
@ConditionalOnClass(name = ["org.springframework.web.reactive.result.method.annotation.ResponseBodyResultHandler"])
*/
class AspectResponseBodyResultHandler {

    /**
     * 统一封装响应, Aop增强 ResponseBodyResultHandler.handleResult
     *
     * See: https://docs.spring.io/spring/docs/current/spring-framework-reference/web-reactive.html
     */
//    @Around("execution(* org.springframework.web.reactive.result.method.annotation.ResponseBodyResultHandler.handleResult(..)) && args(exchange, result)")
    fun aroundHandleResult(pjp: ProceedingJoinPoint, exchange: ServerWebExchange, result: HandlerResult): Mono<*> {
        result.apply {
            return returnValue?.let {
                val list = mutableListOf<Any>()
                val value: Any = when (returnValue) {
                    is Mono<*> -> (returnValue as Mono<*>).map {
                        if (it !is ResponseResult)
                            ResponseResult.Ok(it)
                        else it
                    }
                    is Flux<*> -> (returnValue as Flux<*>).map {
                        if (it is ResponseResult) {
                            it
                        } else {
                            list.add(it)
                            ResponseResult.Ok(list)
                        }
                    }
                    else -> ResponseResult.fail("服务异常")
                }

                val handlerResult = HandlerResult(handler, value, returnTypeSource, bindingContext)
                return pjp.proceed(arrayOf(exchange, handlerResult)) as Mono<*>
            } ?: Mono.empty<Void>()
        }
    }

}