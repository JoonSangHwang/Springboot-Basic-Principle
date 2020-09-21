package hello.core.member;

public interface MemberService {

    // 회원 가입
    public void join(Member member);

    // 회원 조회
    public Member findMember(Long memberId);
}
