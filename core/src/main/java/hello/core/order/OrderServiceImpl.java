package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    /**
     * 메모리 회원 리포지토리와, 고정 금액 할인 정책을 구현체로 생성한다.
     */
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();


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
