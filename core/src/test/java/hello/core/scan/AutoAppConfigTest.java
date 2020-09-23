package hello.core.scan;

import hello.core.AutoAppConfig;
import hello.core.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class AutoAppConfigTest {

    @Test
    @DisplayName("로그를 잘 보면 컴포넌트 스캔이 잘 동작하는 것을 확인할 수 있다.")
    void basicScan() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);

        /**
         * 스캔 대상
         * - 9:42:10.400 [Test worker] DEBUG org.springframework.context.annotation.ClassPathBeanDefinitionScanner - Identified candidate component class: file [D:\STS_Relese\git_ex01\core\build\classes\java\main\hello\core\discount\RateDiscountPolicy.class]
         * - 19:42:10.403 [Test worker] DEBUG org.springframework.context.annotation.ClassPathBeanDefinitionScanner - Identified candidate component class: file [D:\STS_Relese\git_ex01\core\build\classes\java\main\hello\core\member\MemberServiceImpl.class]
         * - 19:42:10.403 [Test worker] DEBUG org.springframework.context.annotation.ClassPathBeanDefinitionScanner - Identified candidate component class: file [D:\STS_Relese\git_ex01\core\build\classes\java\main\hello\core\member\MemoryMemberRepository.class]
         * - 19:42:10.404 [Test worker] DEBUG org.springframework.context.annotation.ClassPathBeanDefinitionScanner - Identified candidate component class: file [D:\STS_Relese\git_ex01\core\build\classes\java\main\hello\core\order\OrderServiceImpl.class]
         *
         *
         * Singleton Bean 생성 완료
         * - 19:42:10.537 [Test worker] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'autoAppConfig'
         * - 19:42:10.543 [Test worker] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'rateDiscountPolicy'
         * - 19:42:10.544 [Test worker] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'memberServiceImpl'
         * - 19:42:10.562 [Test worker] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'memoryMemberRepository'
         *
         *
         * 주입 정보
         * - 19:42:10.564 [Test worker] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Autowiring by type from bean name 'memberServiceImpl' via constructor to bean named 'memoryMemberRepository'
         * - 19:42:10.566 [Test worker] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'orderServiceImpl'
         * - 19:42:10.570 [Test worker] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Autowiring by type from bean name 'orderServiceImpl' via constructor to bean named 'memoryMemberRepository'
         * - 19:42:10.571 [Test worker] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Autowiring by type from bean name 'orderServiceImpl' via constructor to bean named 'rateDiscountPolicy'
         */
    }
}
