package com.zzp.wfdemo.result

/**
 * 响应结果对象
 */
class ResponseResult {
    var code: Int = 10001
    var success: Boolean = true
    var msg: String? = null
    var data: Any? = null

    companion object {
        fun Ok() = ResponseResult()

        fun Ok(data: Any?): ResponseResult {
            return ResponseResult().apply {
                data?.let {
                    if (it is List<*>)
                        this.data = mapOf("list" to it)
                    else
                        this.data = it
                }
            }
        }



        fun fail(msg: String?): ResponseResult {
            return ResponseResult().apply {
                this.code = 50001
                this.msg = msg
                this.success = false
            }
        }
    }
}