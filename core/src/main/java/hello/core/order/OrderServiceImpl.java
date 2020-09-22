package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

/**
 * SRP 단일 책임 원칙
 * 클라이언트 객체는 직접 구현 객체를 생성 & 연결 & 실행 하는 다양한 책임이 존재함
 * 구현 객체를 생성하고, 연결하는 책임은 AppConfig가 담당
 * 클라이언트 객체는 오직 실행하는 책임만 담당
 */
public class OrderServiceImpl implements OrderService {

    // 메모리 회원 리포지토리 구현체 생성
    // private final MemberRepository memberRepository = new MemoryMemberRepository();

    // 고정 금액 할인 정책을 구현체
    // private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    /**
     * 설계 할인 정책 변경 !
     * -> DiscountPolicy 인터페이스에 의존 (o) && RateDiscountPolicy 구현체 클래스에 의존 (0)
     * ----> DIP 위반 [추상에만 의존....]
     * ----> DIP 위반하지 않도록 인터페이스에만 의존하도록 의존 관계를 변경하자
     * ----> private DiscountPolicy discountPolicy;
     * ----> 누군가가 DiscountPolicy 구현 객체를 대신 생성하고 주입해주어야 한다.
     *
     * -> 설계 할인 정책을 변경하기 위해 RateDiscountPolicy 로 변경하는 순간, OrderServiceImpl 소스도 변경 됨
     * ----> OCP 위반
     */
    private final MemberRepository memberRepository;    // DIP 해결, 관심사의 분리(객체를 생성하고 연결하는 역할과 실행하는 역할이 명확히 분리 됨)
    private final DiscountPolicy discountPolicy;        // DIP 해결, 관심사의 분리(객체를 생성하고 연결하는 역할과 실행하는 역할이 명확히 분리 됨)

    /**
     * DIP 의존 관계 역전 원칙
     * 프로그래머는 "추상화에 의존해야지, 구체화에 의존하면 안된다." 의존성 주입은 이 원칙을 따르는 방법 중 하나다.
     */

    /**
     * 생성자를 통해 어떤 구현 객체가 주입이 될지는 오직 외부(AppConfig)에서 결정된다.
     * 이제부터 의존관계에 대한 고민은 외부에 맡기고 실행에만 집중 하면 된다.
     */
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
