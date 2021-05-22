package com.zzp.wfdemo.data

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Field

/**
 * 用户数据
 */
data class User(
    @Id var id: Long,
    @Field("sex") val gender: String,
    @Field("user_name") val userName: String,
    val desc: String
) {
}