package service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

//@Service
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    //이런 방식을 DI 라고 함
    //@Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    /**
     * 회원 가입
     */
    public Long join(Member member) {
        //같은 이름이 있는 중복 회원 X
        //커맨드 + 옵션 + v  = 자동으로 작성한 코드가 리턴으로 감
        //Optional<Member> result = memberRepository.findByName(member.getName());
        //result.orElseGet() 권장하지는 않음
//        result.ifPresent(m -> {
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });

        //코드 정리 [메서드화 시키기] 코드 작성 후 커맨드 + 옵션 + M
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                        .ifPresent(m -> {
                            throw new IllegalStateException("이미 존재하는 회원입니다.");
                        });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMember() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }


}
