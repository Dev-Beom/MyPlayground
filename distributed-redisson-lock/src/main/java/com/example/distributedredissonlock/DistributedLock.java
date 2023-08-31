package com.example.distributedredissonlock;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * Redisson Distributed Lock annotation
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DistributedLock {
    /**
     * lock 이름
     */
    String key();

    /**
     * 락의 시간 단위
     * @return TimeUnit.SECONDS
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;

    /**
     * 락을 기다리는 시간
     * 락 획득을 위해 waitTime 만큼 대기한다
     * @return default 5 seconds
     */
    long waitTime() default 5L;

    /**
     * 락 임대 시간
     * 락을 획득한 이후 leaseTime 이 지나면 락을 해제한다
     * @return default 3 seconds
     */
    long leaseTime() default 3L;
}
