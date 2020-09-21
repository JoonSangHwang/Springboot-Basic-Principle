package hello.core.member;

public class MemberServiceImpl implements MemberService {

    /**
     * MemberServiceImpl
     * - memberRepository(추상 클래스) + MemoryMemberRepository(구현체) 의존 중
     * - 의존관계가 인터페이스 뿐만 아니라 구현까지 모두 의존하는 문제점이 있음
     * - DIP 를 위반
     */
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
