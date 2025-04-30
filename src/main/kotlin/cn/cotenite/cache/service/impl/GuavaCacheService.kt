package cn.cotenite.cache.service.impl

import cn.cotenite.cache.factory.LocalCacheFactory
import cn.cotenite.cache.service.LocalCacheService
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Service

/**
 * @Author  RichardYoung
 * @Description
 * @Date  2025/5/1 01:54
 */
@Service
@ConditionalOnProperty(name = ["local.cache.type"], havingValue = "guava")
class GuavaCacheService<K : Any,V : Any>: LocalCacheService<K, V> {

    private val cache = LocalCacheFactory.getLocalGuavaCache<K,V>()

    override fun put(key: K, value: V) {
        cache.put(key,value)
    }

    override fun getIfPresent(key: K): V? {
        return cache.getIfPresent(key)
    }


}