package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service
//이게 없으면 그냥 순수 자바 클래스, 스프링이 이걸 알 방법이 없음
//컨테이너에 서비스를 등록해줌
//@Component
public class MemberService {

    private final MemberRepository memberRepository;

    //@Autowired//memberRepository가 필요하다고 인식해서 spring container에 넣어줌
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member) {
        //같은 이름이 있는 중복 회원x
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }//원래 join 클래스 안에 있던 거였는데 ctrl+t 로 따로 메소드로 뽑아버림

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
