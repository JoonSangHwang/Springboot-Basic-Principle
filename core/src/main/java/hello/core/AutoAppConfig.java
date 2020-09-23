package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 * 컴포넌트 스캔은 @Component 붙은 클래스를 스캔해서 스프링 빈으로 등록해준다.
 *
 * @ComponentScan 은 @Component 가 붙은 모든 클래스를 스프링 빈으로 등록한다.
 * 이때 스프링 빈의 기본 이름은 클래스명을 사용하되 맨 앞글자만 소문자를 사용한다.
 * 빈 이름 기본 전략: MemberServiceImpl 클래스 memberServiceImpl
 * 빈 이름 직접 지정: 만약 스프링 빈의 이름을 직접 지정하고 싶으면 @Component("memberService2") 이런식으로 이름을 부여하면 된다.
 */
@Configuration
@ComponentScan(
        /**
         * 탐색 위치와 기본 스캔 대상
         * - 멤버와 오더 패키지 하위 것만 스캔 대상이 됨
         * - 모든 자바 클래스를 다 컴포넌트 스캔하면 시간이 오래 걸린다. 그래서 꼭 필요한 위치부터 탐색하도록 시작 위치를 지정할 수 있다.
         * - 만약 지정하지 않으면 @ComponentScan 이 붙은 설정 정보 클래스의 패키지가 시작 위치가 된다
         * - 참고로 스프링 부트를 사용하면 스프링 부트의 대표 시작 정보인 @SpringBootApplication 를 이 프로젝트 시작 루트 위치에 두는 것이 관례이다.
         *   (그리고 이 설정안에 바로 @ComponentScan 이 들어있다!)
        */
        // basePackages =  {"hello.core.member", "hello.core.order"},

        // 어노테이션 @Configuration 은 스캔 대상에서 제외 (이미 수동으로 하고 있음)
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
}
