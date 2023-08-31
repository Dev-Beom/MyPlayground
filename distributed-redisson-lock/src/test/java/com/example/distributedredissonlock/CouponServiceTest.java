package com.example.distributedredissonlock;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
class CouponServiceTest {
    @Autowired
    private CouponRepository couponRepository;
    @Autowired
    private CouponService couponService;

    Coupon coupon;

    @BeforeEach
    void setUp() {
        coupon = new Coupon("AA_001", 100L);
        couponRepository.save(coupon);
    }

    @AfterEach
    void tearDown() {
        couponRepository.deleteAll();
    }

    /**
     * 1. 쿠폰 100개가 준비되어있다
     * 2. 사용자 100명이 동시에 쿠폰을 발급받기 위해 쿠폰 발급을 요청한다
     * 3. 정상적으로 남은 쿠폰 갯수가 0이 되어야 한다
     */
    @Test
    void 쿠폼차감_분산락_적용_동시성_100명_테스트() throws InterruptedException {
        int numberOfThreads = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        for (int i=0; i<numberOfThreads; i++) {
            executorService.submit(() -> {
                try {
                    couponService.decrease(coupon.getId());
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        Coupon persistCoupon = couponRepository.findById(coupon.getId())
                .orElseThrow(IllegalArgumentException::new);

        assertThat(persistCoupon.getAvailableStock()).isZero();
    }


    /**
     * 1. 쿠폰 100개가 준비되어있다
     * 2. 사용자 100명이 동시에 쿠폰을 발급받기 위해 쿠폰 발급을 요청한다
     * 3. 정상적으로 남은 쿠폰 갯수가 0이 아니여야한다.
     */
    @Test
    void 쿠폼차감_분산락_미적용_동시성_100명_테스트() throws InterruptedException {
        int numberOfThreads = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        for (int i=0; i<numberOfThreads; i++) {
            executorService.submit(() -> {
                try {
                    couponService.decreaseWithout(coupon.getId());  // 분산락 없는 로직
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        Coupon persistCoupon = couponRepository.findById(coupon.getId())
                .orElseThrow(IllegalArgumentException::new);

        assertThat(persistCoupon.getAvailableStock()).isNotZero();
    }
}