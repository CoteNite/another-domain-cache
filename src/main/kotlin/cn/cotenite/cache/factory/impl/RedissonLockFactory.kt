package cn.cotenite.cache.factory.impl

import cn.cotenite.cache.entiy.DistributedLock
import cn.cotenite.cache.factory.DistributedLockFactory
import org.redisson.api.RedissonClient
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

/**
 * @Author  RichardYoung
 * @Description
 * @Date  2025/5/1 03:20
 */
@Component
@ConditionalOnProperty(name = ["distributed.lock.type"], havingValue = "redisson")
class RedissonLockFactory(
    private val redissonClient: RedissonClient
):DistributedLockFactory{

    override fun getDistributedLock(key: String): DistributedLock {
        val rLock = redissonClient.getLock(key)

        return object : DistributedLock {
            override fun tryLock(waitTime: Long, leaseTime: Long, unit: TimeUnit?): Boolean {
                val isLock = rLock.tryLock(waitTime, leaseTime, unit)
                return isLock
            }

            override fun lock(leaseTime: Long, unit: TimeUnit?) {
                rLock.lock(leaseTime,unit)
            }

            override fun unlock() {
                if (isLocked() && isHeldByCurrentThread()) {
                    rLock.unlock()
                }
            }

            override fun isLocked(): Boolean {
                return rLock.isLocked
            }

            override fun isHeldByThread(threadId: Long): Boolean {
                return rLock.isHeldByThread(threadId)
            }

            override fun isHeldByCurrentThread(): Boolean {
                return rLock.isHeldByCurrentThread
            }
        }
    }


}