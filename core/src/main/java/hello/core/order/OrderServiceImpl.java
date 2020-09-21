package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

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
