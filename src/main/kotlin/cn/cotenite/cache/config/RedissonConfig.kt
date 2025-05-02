package cn.cotenite.cache.config

import org.redisson.Redisson
import org.redisson.api.RedissonClient
import org.redisson.config.Config
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.*

/**
 * @Author  RichardYoung
 * @Description
 * @Date  2025/5/3 01:55
 */
@Configuration
@ConditionalOnProperty(name = ["spring.redis.host"], havingValue = "redisson")
class RedissonConfig(
    @Value("\${spring.redis.address}")
    private var redisAddress:String
){

    @Bean(name = ["redissonClient"])
    @ConditionalOnProperty(name = ["redis.arrange.type"], havingValue = "single")
    fun singleRedissonClient(): RedissonClient? {
        val config = Config()
        config.useSingleServer().setAddress(redisAddress).setDatabase(0)
        return Redisson.create(config)
    }

    @Bean(name = ["redissonClient"])
    @ConditionalOnProperty(name = ["redis.arrange.type"], havingValue = "cluster")
    fun clusterRedissonClient():RedissonClient{
        val config = Config()
        val clusterServersConfig = config.useClusterServers()
        clusterServersConfig.nodeAddresses = listOf(redisAddress)
        return Redisson.create(config)
    }

}