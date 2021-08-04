package springpractice.reservationmovie.service;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import springpractice.reservationmovie.domain.Movie;
import springpractice.reservationmovie.domain.ScreeningInfo;

import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ScreeningInfoServiceTest {

    @Autowired ScreeningInfoService screeningInfoService;

    @Test
    public void 상영정보저장_이름검색() throws Exception {
        //given
        ScreeningInfo screeningInfo1 = ScreeningInfo.create(
                Movie.create("정글 크루즈", 120),
                LocalTime.of(13, 30));
        ScreeningInfo screeningInfo2 = ScreeningInfo.create(
                Movie.create("정글 크루즈", 120),
                LocalTime.of(15, 30));
        ScreeningInfo screeningInfo3 = ScreeningInfo.create(
                Movie.create("랑종", 120),
                LocalTime.of(15, 30));

        //when
        screeningInfoService.resister(screeningInfo1);
        screeningInfoService.resister(screeningInfo2);
        screeningInfoService.resister(screeningInfo3);
        List<ScreeningInfo> findInfos = screeningInfoService.searchByTitle("정글 크루즈");

        //then
        assertThat(findInfos.size()).isEqualTo(2);
    }

    @Test
    public void 상영정보전체검색_삭제() throws Exception {
        //given
        ScreeningInfo screeningInfo1 = ScreeningInfo.create(
                Movie.create("정글 크루즈", 120),
                LocalTime.of(13, 30));
        ScreeningInfo screeningInfo2 = ScreeningInfo.create(
                Movie.create("정글 크루즈", 120),
                LocalTime.of(15, 30));
        ScreeningInfo screeningInfo3 = ScreeningInfo.create(
                Movie.create("랑종", 120),
                LocalTime.of(15, 30));

        //when
        screeningInfoService.resister(screeningInfo1);
        screeningInfoService.resister(screeningInfo2);
        screeningInfoService.resister(screeningInfo3);
        screeningInfoService.delete(screeningInfo2);
        List<ScreeningInfo> screeningInfos = screeningInfoService.searchAll();

        //then
        assertThat(screeningInfos.size()).isEqualTo(2);
    }

    @Test
    public void 예약인원만큼좌석감소() throws Exception {
        //given
        ScreeningInfo screeningInfo = ScreeningInfo.create(
                Movie.create("정글 크루즈", 120),
                LocalTime.of(13, 30));

        //when
        screeningInfo.reserveSeat(9);

        //then
        assertThat(screeningInfo.getSeatingCapacity()).isEqualTo(1);
    }

    @Test(expected = IllegalStateException.class)
    public void 예약인원좌석초과() throws Exception {
        //given
        ScreeningInfo screeningInfo = ScreeningInfo.create(
                Movie.create("정글 크루즈", 120),
                LocalTime.of(13, 30));

        //when
        screeningInfo.reserveSeat(11);

        //then
        System.out.println("실패");
    }

}