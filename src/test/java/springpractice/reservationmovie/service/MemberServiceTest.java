package springpractice.reservationmovie.service;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import springpractice.reservationmovie.domain.Member;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;

    @Test
    public void 회원생성() throws Exception {
        //given
        Member member = Member.create("이동규", 24);

        //when
        Long memberId = memberService.join(member);

        //then
        assertThat(memberService.findOne(memberId)).isEqualTo(member);
    }

    @Test
    public void 회원전체조회() throws Exception {
        //given
        Member member1 = Member.create("이동규", 24);
        Member member2 = Member.create("김철수", 24);
        memberService.join(member1);
        memberService.join(member2);

        //when
        List<Member> all = memberService.findAll();

        //then
        assertThat(all.size()).isEqualTo(2);
    }

}