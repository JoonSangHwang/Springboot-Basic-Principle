package hello.core.scan.filter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.Assertions.assertThat;

public class ComponentFilerAppConfigTest {

    /**
     * FilterType은 5가지 옵션이 있다
     * 
     * - ANNOTATION: 기본값, 애노테이션을 인식해서 동작한다.
     *      ex) org.example.SomeAnnotation
     * - ASSIGNABLE_TYPE: 지정한 타입과 자식 타입을 인식해서 동작한다.
     *      ex) org.example.SomeClass
     * - ASPECTJ: AspectJ 패턴 사용
     *      ex) org.example..*Service+
     * - REGEX: 정규 표현식
     *      ex) org\.example\.Default.*
     * - CUSTOM: TypeFilter 이라는 인터페이스를 구현해서 처리
     *      ex) org.example.MyTypeFilter
     */

    @Test
    void filterScan() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);

        // 빈 이름 조회
        BeanA beanA = ac.getBean("beanA", BeanA.class);

        // 빈(beanA) 존재 하는지 검증
       assertThat(beanA).isNotNull();

        // 빈(beanB) 존재 하지 않는지 검증
        Assertions.assertThrows(NoSuchBeanDefinitionException.class,() -> ac.getBean("beanB", BeanB.class));
    }


    @Configuration
    @ComponentScan(
            // 컴포넌트 대상
            includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),

            // 컴포넌트
            excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)
    )
    static class ComponentFilterAppConfig {

    }
}
