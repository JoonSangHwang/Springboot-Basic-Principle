package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {

    /**
     * 애플리케이션 로직으로 이렇게 테스트 하는 것은 좋은 방법이 아니다. JUnit 테스트를 사용하자.
     */
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
//        OrderService orderService = appConfig.orderService();

        /**
         * ApplicationContext 스프링 컨테이너
         * - Bean 관리 해주는 객체
         */
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        // 스프링 컨테이너에서 꺼내어 사용
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class); // 메소드 이름이 Key
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);     // 메소드 이름이 Key

        long memberId = 1L;

        // 회원 생성
        Member member = new Member(memberId, "memberA", Grade.VIP);

        // 회원 가입
        memberService.join(member);

        // 주문 생성
        Order order = orderService.createOrder(memberId, "itemA", 20000);

        // [로그] 주문
        System.out.println("order = " + order);         // order = Order{memberId=1, itemName='itemA', itemPrice=10000, discountPrice=1000}
        System.out.println("calculatePrice = " + order.calculatePrice());         // 9000
    }
}
