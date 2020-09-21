package hello.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService = new MemberServiceImpl();

    @Test
    void join() {
        //given
        Member member = new Member(1L, "junsang", Grade.VIP);

        //when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        System.out.println("? : " + member);
        System.out.println("? : " + findMember);

        //then
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
