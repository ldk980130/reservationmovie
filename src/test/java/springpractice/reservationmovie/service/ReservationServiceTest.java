package springpractice.reservationmovie.service;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import springpractice.reservationmovie.domain.*;

import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ReservationServiceTest {

    @Autowired ReservationService reservationService;

    @Test
    public void 예매저장_검색() throws Exception {
        //given
        Member member = Member.create("이동규", 24);
        Movie movie = Movie.create("랑종", 120, EnumRate.FIFTEEN);
        ScreeningInfo screeningInfo = ScreeningInfo.create(movie, LocalTime.now());

        //when
        Reservation reservation = Reservation.create(member, screeningInfo, 1, 1);
        String id = reservationService.reserve(reservation);

        //then
        assertThat(reservationService.findOne(id)).isEqualTo(reservation);
    }

    @Test
    public void 예메고유아이디판별() throws Exception {
        //given
        Member member = Member.create("이동규", 24);
        Movie movie = Movie.create("랑종", 120, EnumRate.FIFTEEN);
        ScreeningInfo screeningInfo = ScreeningInfo.create(movie, LocalTime.now());

        //when
        Reservation reservation1 = Reservation.create(member, screeningInfo, 1, 1);
        Reservation reservation2 = Reservation.create(member, screeningInfo, 1, 1);
        reservationService.reserve(reservation1);
        reservationService.reserve(reservation2);

        //then
        assertThat(reservation1.getId()).isNotEqualTo(reservation2.getId());
    }

    @Test
    public void 예매전체검색() throws Exception {
        //given
        Member member1 = Member.create("이동규", 24);
        Member member2 = Member.create("이동규", 24);
        Movie movie = Movie.create("랑종", 120, EnumRate.FIFTEEN);
        ScreeningInfo screeningInfo = ScreeningInfo.create(movie, LocalTime.now());

        //when
        Reservation reservation1 = Reservation.create(member1, screeningInfo, 1, 1);
        Reservation reservation2 = Reservation.create(member2, screeningInfo, 1, 1);
        reservationService.reserve(reservation1);
        reservationService.reserve(reservation2);

        //then
        assertThat(reservationService.findAll().size()).isEqualTo(2);
    }

    @Test
    public void 예매취소() throws Exception {
        //given
        Member member = Member.create("이동규", 24);
        Movie movie = Movie.create("랑종", 120, EnumRate.FIFTEEN);
        ScreeningInfo screeningInfo = ScreeningInfo.create(movie, LocalTime.now());
        Reservation reservation = Reservation.create(member, screeningInfo, 1, 1);
        String id = reservationService.reserve(reservation);

        //when
        Reservation findReservation = reservationService.findOne(id);reservationService.cancel(findReservation);

        //then
        assertThat(reservation.getStatus()).isEqualTo(ReservationStatus.CANCEL);
        assertThat(screeningInfo.getRemnant()).isEqualTo(10);
    }

    @Test
    public void 회원별예약조회() throws Exception {
        //given
        Member member1 = Member.create("이동규", 24);
        Member member2 = Member.create("철수", 21);
        Movie movie1 = Movie.create("랑종", 120, EnumRate.FIFTEEN);
        Movie movie2 = Movie.create("정글 크루즈", 119, EnumRate.TWELVE);
        ScreeningInfo screeningInfo1 = ScreeningInfo.create(movie1, LocalTime.now());
        ScreeningInfo screeningInfo2 = ScreeningInfo.create(movie2, LocalTime.now());

        //when
        Reservation reservation1 = Reservation.create(member1, screeningInfo1, 1, 1);
        Reservation reservation2 = Reservation.create(member1, screeningInfo2, 1, 1);
        Reservation reservation3 = Reservation.create(member2, screeningInfo1, 1, 1);
        reservationService.reserve(reservation1);
        reservationService.reserve(reservation2);
        reservationService.reserve(reservation3);

        //then
        List<Reservation> reservations = reservationService.showMemberReservations(member1);
        assertThat(reservations.size()).isEqualTo(2);
    }
}