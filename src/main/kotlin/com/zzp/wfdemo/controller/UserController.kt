package com.zzp.wfdemo.controller

import com.zzp.wfdemo.data.User
import com.zzp.wfdemo.service.UserService
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import javax.annotation.Resource

/**
 * controller
 */
@RequestMapping("/user")
@RestController
class UserController {

    @Resource
    private lateinit var userService: UserService

    @GetMapping("/")
    fun allUser(): Flux<User> = userService.findAll()

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Long?): Mono<User?>? = when (id) {
        is Long -> userService.getUser(id)
        else -> Mono.empty()
    }

    @GetMapping("/{userName}/{note}")
    fun queryUser(@PathVariable note: String?, @PathVariable userName: String?): Flux<User> {
        return userService.findUsers(userName ?: "", note ?: "")
    }

    @PostMapping("/")
    fun saveUser(@RequestBody user: User?): Mono<User?>? = when (user) {
        is User -> userService.insertUser(user)
        else -> Mono.empty()
    }

    @PutMapping("/")
    fun changeUser(@RequestBody user: User?): Mono<User?>? {
        return when (user) {
            is User -> userService.updateUser(user)
            else -> Mono.empty()
        }
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long?): Mono<Void?>? {
        return userService.deleteUser(id ?: 0)
    }

}