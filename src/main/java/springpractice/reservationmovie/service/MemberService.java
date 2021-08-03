package springpractice.reservationmovie.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springpractice.reservationmovie.domain.Member;
import springpractice.reservationmovie.repository.MemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Transactional
    public void leave(Member member) {
        memberRepository.delete(member);
    }

    public Member searchById(String id) {
        return memberRepository.findById(id);
    }

    public List<Member> searchAll() {
        return memberRepository.findAll();
    }
}
