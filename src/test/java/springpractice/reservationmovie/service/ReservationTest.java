package springpractice.reservationmovie.service;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import springpractice.reservationmovie.domain.*;

import java.time.LocalTime;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

public class ReservationTest {

    @Test
    public void 예매생성() throws Exception {
        //given
        Member member = Member.create("이동규", 24);
        Movie movie = Movie.create("랑종", 120, EnumRate.FIFTEEN);
        ScreeningInfo screeningInfo = ScreeningInfo.create(movie, LocalTime.now());

        //when
        Reservation reservation = Reservation.create(member, screeningInfo, 1, 1);

        //then
        assertThat(reservation.getMember().getName()).isEqualTo("이동규");
        assertThat(reservation.getScreeningInfo().getMovieTitle()).isEqualTo("랑종");
        assertThat(reservation.getTotalPrice()).isEqualTo(23000);
        assertThat(reservation.getStatus()).isEqualTo(ReservationStatus.RESERVED);
        assertThat(screeningInfo.getRemnant()).isEqualTo(8);
    }

    @Test(expected = IllegalStateException.class)
    public void 예매생성불가() throws Exception {
        //given
        Member member = Member.create("이동규", 24);
        Movie movie = Movie.create("랑종", 120, EnumRate.FIFTEEN);
        ScreeningInfo screeningInfo = ScreeningInfo.create(movie, LocalTime.now());

        //when
        Reservation reservation = Reservation.create(member, screeningInfo, 10, 1);

        //then
    }

    @Test
    public void 예매취소() throws Exception {
        //given
        Member member = Member.create("이동규", 24);
        Movie movie = Movie.create("랑종", 120, EnumRate.FIFTEEN);
        ScreeningInfo screeningInfo = ScreeningInfo.create(movie, LocalTime.now());
        Reservation reservation = Reservation.create(member, screeningInfo, 4, 1);

        //when
        reservation.remove();

        //then
        assertThat(screeningInfo.getRemnant()).isEqualTo(10);
        assertThat(reservation.getStatus()).isEqualTo(ReservationStatus.CANCEL);
    }

    @Test
    public void 같은상영정보_다른예매() throws Exception {
        //given
        Movie movie = Movie.create("랑종", 120, EnumRate.FIFTEEN);
        ScreeningInfo screeningInfo = ScreeningInfo.create(movie, LocalTime.now());

        Member member1 = Member.create("이동규", 24);
        Member member2 = Member.create("이민수", 24);

        //when
        Reservation reservation1 = Reservation.create(member1, screeningInfo, 4, 1);
        Reservation reservation2 = Reservation.create(member2, screeningInfo, 4, 1);

        //then
        assertThat(screeningInfo.getRemnant()).isEqualTo(0);
    }
}