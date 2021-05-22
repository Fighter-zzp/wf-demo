package com.zzp.wfdemo.repository

import com.zzp.wfdemo.data.User
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Flux

/**
 * 用户dao层
 */
interface UserRepository : ReactiveMongoRepository<User, Long> {
    /**
     * 查询用户
     * @param userName 用户名称
     * @param desc 用户描述
     */
    fun findByUserNameLikeOrDescLike(userName: String, desc: String): Flux<User>
}