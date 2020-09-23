package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * SRP 단일 책임 원칙
 * 클라이언트 객체는 직접 구현 객체를 생성 & 연결 & 실행 하는 다양한 책임이 존재함
 * 구현 객체를 생성하고, 연결하는 책임은 AppConfig가 담당
 * 클라이언트 객체는 오직 실행하는 책임만 담당
 */
@Component
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;    // DIP 해결, 관심사의 분리(객체를 생성하고 연결하는 역할과 실행하는 역할이 명확히 분리 됨)
    private final DiscountPolicy discountPolicy;        // DIP 해결, 관심사의 분리(객체를 생성하고 연결하는 역할과 실행하는 역할이 명확히 분리 됨)

    @Autowired
    public OrderServiceImpl(MemoryMemberRepository memoryMemberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memoryMemberRepository;
        this.discountPolicy = discountPolicy;
    }

    /**
     * 주문 생성 요청이 오면, 회원 정보를 조회하고, 할인 정책을 적용한 다음 주문 객체를 생성해서 반환한다.
     * @param memberId      회원 id
     * @param itemName      상품 명
     * @param itemPrice     상품 가격
     * @return              주문
     */
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {

        // 회원을 찾는다.
        Member member = memberRepository.findById(memberId);

        // 해당 해원의 할인 금액을 조회한다.
        int discountPrice = discountPolicy.discount(member, itemPrice);

        // 주문을 생성한다.
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
