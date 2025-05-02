package cn.cotenite.cache.model.common

/**
 * @Author  RichardYoung
 * @Description
 * @Date  2025/5/3 02:20
 */
data class CommonCache(
    var exist:Boolean=false,
    var version:Long=-1,
    var retryLater:Boolean=false
)