package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.stereotype.Component;

@Component
public class RateDiscountPolicy implements DiscountPolicy {

    private int discountPercent = 10;   //1000원 할인

    /**
     * VIP면 10% 할인, 아니면 할인 없음
     */
    @Override
    public int discount(Member member, int price) {
        // VIP 인 경우
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        }

        // VIP 아닌 경우
        else {
            return 0;
        }
    }
}
