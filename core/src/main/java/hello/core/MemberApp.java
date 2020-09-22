package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    /**
     * 애플리케이션 로직으로 이렇게 테스트 하는 것은 좋은 방법이 아니다. JUnit 테스트를 사용하자.
     */
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();    // Impl 주입

        /**
         * ApplicationContext 스프링 컨테이너 (49p 그림)
         * - Bean 관리 해주는 객체
         * - ApplicationContext 은 인터페이스이다. AnnotationConfigApplicationContext 는 구현클래스이다.
         * - 참고 : 더 정확히는 스프링 컨테이너를 부를 때 BeanFactory , ApplicationContext 로 구분해서 이야기한다.
         *          BeanFactory 를 직접 사용하는 경우는 거의 없으므로 일반적으로 ApplicationContext 를 스프링 컨테이너라 한다.
         *
         ****** [60p 그림]
         ****** BeanFactory
         *      - 스프링 컨테이너의 최상위 인터페이스다.
         *      - 스프링 빈을 관리하고 조회하는 역할을 담당한다.
         *      - getBean() 을 제공한다.
         *      - 지금까지 우리가 사용했던 대부분의 기능은 BeanFactory가 제공하는 기능이다.
         *
         ****** ApplicationContext
         *      - BeanFactory 기능을 모두 상속받아서 제공한다.
         *      - 빈을 관리하고 검색하는 기능을 BeanFactory가 제공해주는데, 그러면 둘의 차이가 뭘까?
         *      - 애플리케이션을 개발할 때는 빈은 관리하고 조회하는 기능은 물론이고, 수 많은 부가기능이 필요하다.
         *
         ****** 정리
         *      - ApplicationContext는 BeanFactory의 기능을 상속받는다.
         *      - ApplicationContext는 빈 관리기능 + 편리한 부가 기능을 제공한다.
         *      - BeanFactory를 직접 사용할 일은 거의 없다. 부가기능이 포함된 ApplicationContext를 사용한다.
         *      - BeanFactory나 ApplicationContext를 스프링 컨테이너라 한다.
         */
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        // 스프링 컨테이너에서 꺼내어 사용
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class); // 메소드 이름이 Key

        Member member = new Member(1L, "junsang", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);

        System.out.println("new Member : " + member.getName());
        System.out.println("find Member : " + findMember.getName());
    }
}
