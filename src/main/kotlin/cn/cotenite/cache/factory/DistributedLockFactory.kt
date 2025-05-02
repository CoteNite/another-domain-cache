package cn.cotenite.cache.factory

import cn.cotenite.cache.entiy.DistributedLock

/**
 * @Author  RichardYoung
 * @Description
 * @Date  2025/5/1 03:18
 */
interface DistributedLockFactory {

    fun getDistributedLock(key:String): DistributedLock

}