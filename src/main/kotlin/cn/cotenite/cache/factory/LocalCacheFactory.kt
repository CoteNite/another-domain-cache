package cn.cotenite.cache.factory

import cn.hutool.core.util.RandomUtil
import com.github.benmanes.caffeine.cache.Caffeine
import com.google.common.cache.Cache
import com.google.common.cache.CacheBuilder
import java.util.concurrent.TimeUnit

/**
 * @Author  RichardYoung
 * @Description
 * @Date  2025/5/1 01:50
 */
object LocalCacheFactory{

    fun <K : Any,V : Any> getLocalGuavaCache(): Cache<K, V> {
        return CacheBuilder.newBuilder()
            .initialCapacity(15)
            .concurrencyLevel(5)  //并发等级->可以写入的最大线程数
            .expireAfterWrite(60L+RandomUtil.randomInt(60), TimeUnit.SECONDS)
            .build()
    }

    fun <K:Any,V:Any> getLocalCaffeineCache():com.github.benmanes.caffeine.cache.Cache<K,V>{
        return Caffeine.newBuilder()
            .initialCapacity(15)
            .expireAfterWrite(60L+RandomUtil.randomInt(60), TimeUnit.SECONDS)
            .build()
    }

}