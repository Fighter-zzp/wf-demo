package com.zzp.wfdemo.service

import com.zzp.wfdemo.data.User
import reactor.core.publisher.Flux

import reactor.core.publisher.Mono




/**
 * 用户服务
 */
interface UserService {
    /**
     * Retrieve
     * @param id 用户id
     * @return .
     */
    fun getUser(id: Long): Mono<User?>?

    /**
     * Create
     * @param user 用户
     * @return .
     */
    fun insertUser(user: User): Mono<User?>?

    /**
     * Update
     * @param user 用户
     * @return .
     */
    fun updateUser(user: User): Mono<User?>?

    /**
     * Delete
     * @param id 用户id
     * @return .
     */
    fun deleteUser(id: Long): Mono<Void?>?

    /**
     * Retrieve
     * @param userName 用户名
     * @param desc 描述
     * @return .
     */
    fun findUsers(userName: String, desc: String): Flux<User>

    /**
     * 查询全部
     * @return .
     */
    fun findAll(): Flux<User>
}