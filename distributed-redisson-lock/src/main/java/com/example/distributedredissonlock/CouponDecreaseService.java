package com.example.distributedredissonlock;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CouponDecreaseService {
    private final CouponRepository couponRepository;

    @DistributedLock(key = "#key")
    public void couponDecrease(String key, Long couponId) {
        this.decrease(key, couponId);
    }

    @Transactional
    public void couponDecreaseWithoutLock(String key, Long couponId) {
        this.decrease(key, couponId);
    }

    private void decrease(String key, Long couponId) {
        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow(IllegalArgumentException::new);
        coupon.decrease();
    }
}
