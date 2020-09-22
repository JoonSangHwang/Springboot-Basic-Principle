package hello.core.member;

public class MemberServiceImpl implements MemberService {

    //    private final MemberRepository memberRepository = new MemoryMemberRepository();

    /**
     * MemberServiceImpl
     * -> memberRepository(추상 클래스) & MemoryMemberRepository(구현 클래스) 의존 중
     * - 의존관계가 인터페이스 뿐만 아니라 구현까지 모두 의존하는 문제점이 있음
     * - DIP 를 위반
     */
    private final MemberRepository memberRepository;        // DIP 해결, 관심사의 분리(객체를 생성하고 연결하는 역할과 실행하는 역할이 명확히 분리 됨)

    /**
     * 생성자를 통해 어떤 구현 객체가 주입이 될지는 오직 외부(AppConfig)에서 결정된다.
     * 이제부터 의존관계에 대한 고민은 외부에 맡기고 실행에만 집중 하면 된다.
     */
    public MemberServiceImpl(MemoryMemberRepository memoryMemberRepository) {
        this.memberRepository = memoryMemberRepository;
    }


    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
