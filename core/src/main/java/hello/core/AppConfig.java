package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * IoC 컨테이너 또는 DI 컨테이너
 * Application 의 실제 동작에 필요한 구현 객체를 생성한다.
 */
@Configuration
public class AppConfig {

    /**
     * DI (Dependency Injection) 의존관계 주입 또는 의존성 주입
     * 생성한 객체 인스턴스의 레퍼런스를 생성자를 통해서 주입한다. (30페이지 그림)
     */
    @Bean
    public MemberService memberService() {
        // new MemoryMemberRepository() 객체 인스턴스 생성 후, 참조(레퍼런스) 값을 MemberServiceImpl 생성자의 파라미터로 전달
        return new MemberServiceImpl(memoryMemberRepository());
    }

    @Bean
    public MemoryMemberRepository memoryMemberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memoryMemberRepository(), discountPolicy());
    }

    @Bean
    private DiscountPolicy discountPolicy() {
        /**
         * OCP
         * 소프트웨어 요소는 확장에는 열려 있으나 변경에는 닫혀 있어야 한다.
         */
//      return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
