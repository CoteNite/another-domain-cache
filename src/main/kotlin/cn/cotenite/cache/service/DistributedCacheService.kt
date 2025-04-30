package cn.cotenite.cache.service

import java.util.concurrent.TimeUnit



/**
 * @Author  RichardYoung
 * @Description
 * @Date  2025/5/1 00:06
 */
interface DistributedCacheService {

    fun put(key: String, value: String)
    fun put(key: String, value: Any)
    fun put(key: String, value: Any, timeout: Long, unit: TimeUnit)
    fun put(key: String, value: Any, expireTime: Long)
    fun <T> getObject(key: String, targetClass: Class<T>): T?
    fun getString(key: String): String?
    fun <T> getList(key: String, targetClass: Class<T>): List<T>?
    fun delete(key: String): Boolean
    fun hasKey(key: String): Boolean
    
}