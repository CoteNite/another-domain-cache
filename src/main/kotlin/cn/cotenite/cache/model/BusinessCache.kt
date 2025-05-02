package cn.cotenite.cache.model

import cn.cotenite.cache.model.common.CommonCache

/**
 * @Author  RichardYoung
 * @Description
 * @Date  2025/5/3 02:22
 */
data class BusinessCache<T>(
    var data:T,
    val commonCache: CommonCache
){

    fun with(data:T): BusinessCache<T> {
        this.data = data
        this.commonCache.exist=true
        return this
    }

    fun withVersion(version:Long): BusinessCache<T> {
        this.commonCache.version = version
        return this
    }

    fun retryLater(): BusinessCache<T> {
        this.commonCache.retryLater = true
        return this
    }

    fun notExist(): BusinessCache<T> {
        this.commonCache.exist = false
        return this
    }

}
