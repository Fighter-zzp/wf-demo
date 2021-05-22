package com.zzp.wfdemo.service.impl

import com.zzp.wfdemo.service.UserService
import com.zzp.wfdemo.data.User
import com.zzp.wfdemo.repository.UserRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.LocalDateTime
import java.time.ZoneOffset
import javax.annotation.Resource

/**
 * user服务实现
 */
@Service
class UserServiceImpl : UserService {

    @Resource
    private lateinit var repository: UserRepository

    override fun getUser(id: Long): Mono<User?>? {
        return repository.findById(id)
    }

    override fun insertUser(user: User): Mono<User?>? {
        user.id = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
        return repository.save(user);
    }

    override fun updateUser(user: User): Mono<User?>? {
       return when{
            user.id != 0L -> repository.save(user)
           else -> Mono.empty<User>()
       }
    }

    override fun deleteUser(id: Long): Mono<Void?>? {
        repository.deleteById(id)
        return null;
    }

    override fun findUsers(userName: String, desc: String): Flux<User> {
        return repository.findByUserNameLikeOrDescLike(userName, desc)
    }

    override fun findAll(): Flux<User> {
        return repository.findAll()
    }


}