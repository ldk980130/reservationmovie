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
        Member member = Member.create("ldk", "1802",
                "010-4199-6985", "ldk@gmail.com", 24);

        //when
        memberService.join(member);
        Member findMember = memberService.searchById("ldk");

        //then
        assertThat(findMember).isEqualTo(member);
        System.out.println(findMember.getEmail());
    }

    @Test
    public void 아이디중복() throws Exception {
        //given
        Member member1 = Member.create("ldk", "1802",
                "010-4199-6985", "ldk@gmail.com", 20);
        Member member2 = Member.create("ldk", "1802",
                "010-4199-6985", "ldk@gmail.com", 24);

        //when
        memberService.join(member1);

        //then
        assertThrows(DataIntegrityViolationException.class,
                () -> memberService.join(member2));

    }

    @Test
    public void 전체조회() throws Exception {
        //given
        Member member1 = Member.create("ldk", "1802",
                "010-4199-6985", "ldk@gmail.com", 20);
        Member member2 = Member.create("ldk23", "1802",
                "010-4199-6985", "ldk@gmail.com", 24);

        //when
        memberService.join(member1);
        memberService.join(member2);

        //then
        assertThat(memberService.searchAll().size()).isEqualTo(2);
    }

    @Test
    public void 회원삭제() throws Exception {
        //given
        Member member1 = Member.create("ldk", "1802",
                "010-4199-6985", "ldk@gmail.com", 20);
        Member member2 = Member.create("ldk23", "1802",
                "010-4199-6985", "ldk@gmail.com", 24);

        //when
        memberService.join(member1);
        memberService.join(member2);
        memberService.leave(member1);

        //then
        assertThat(memberService.searchAll().size()).isEqualTo(1);

    }

}