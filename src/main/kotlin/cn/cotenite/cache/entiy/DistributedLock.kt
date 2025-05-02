package cn.cotenite.cache.entiy

import java.util.concurrent.TimeUnit



/**
 * @Author  RichardYoung
 * @Description
 * @Date  2025/5/1 03:19
 */
interface DistributedLock{

    fun tryLock(waitTime: Long, leaseTime: Long, unit: TimeUnit?): Boolean
    fun lock(leaseTime: Long, unit: TimeUnit?)
    fun unlock()
    fun isLocked(): Boolean
    fun isHeldByThread(threadId: Long): Boolean
    fun isHeldByCurrentThread(): Boolean

}