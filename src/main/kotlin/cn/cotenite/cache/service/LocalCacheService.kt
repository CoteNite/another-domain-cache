package cn.cotenite.cache.service

/**
 * @Author  RichardYoung
 * @Description
 * @Date  2025/5/1 00:06
 */
interface LocalCacheService<K,V>{

    fun put(key:K,value:V)

    fun getIfPresent(key:K):V?

}