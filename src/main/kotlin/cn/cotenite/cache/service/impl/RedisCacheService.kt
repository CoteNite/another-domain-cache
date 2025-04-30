package cn.cotenite.cache.service.impl

import cn.cotenite.cache.service.DistributedCacheService
import com.alibaba.fastjson2.JSON
import org.redisson.api.RedissonClient
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit

/**
 * @Author  RichardYoung
 * @Description
 * @Date  2025/5/1 02:20
 */
@Service
@ConditionalOnProperty(name = ["distributed.cache.type"], havingValue = "redis")
class RedisCacheService(
    private val redissonClient: RedissonClient,
):DistributedCacheService{

    override fun put(key: String, value: String) {
        redissonClient.getBucket<Any>(key).setAsync(value)
    }

    override fun put(key: String, value: Any) {
        redissonClient.getBucket<Any>(key).setAsync(value)
    }

    override fun put(key: String, value: Any, timeout: Long, unit: TimeUnit) {
        redissonClient.getBucket<Any>(key).setAsync(value,timeout,unit)
    }

    override fun put(key: String, value: Any, expireTime: Long) {
        this.put(key,value,expireTime,TimeUnit.SECONDS)
    }

    override fun <T> getObject(key: String, targetClass: Class<T>): T? {
        val jsonStr = redissonClient.getBucket<String>(key).get()
        return if (jsonStr == null) null else JSON.parseObject(jsonStr,targetClass)
    }

    override fun getString(key: String): String? {
        val any = redissonClient.getBucket<Any>(key).get()
        return JSON.toJSONString(any)
    }

    override fun <T> getList(key: String, targetClass: Class<T>): List<T>? {
        val rList = redissonClient.getList<T>(key)
        return if (rList.isExists) rList.get() else null
    }


    override fun delete(key: String): Boolean {
        val deletedCount = redissonClient.keys.delete(key)
        return deletedCount > 0
    }

    override fun hasKey(key: String): Boolean {
        return redissonClient.keys.countExists(key) > 0
    }


}