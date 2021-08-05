package springpractice.reservationmovie.service;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import springpractice.reservationmovie.domain.EnumRate;
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
    public void 정보생성_검색() throws Exception {
        //given
        ScreeningInfo info = ScreeningInfo.create(
                Movie.create("asdf", 120, EnumRate.FIFTEEN),
                LocalTime.of(13, 30));

        //when
        Long id = screeningInfoService.resister(info);
        ScreeningInfo findInfo = screeningInfoService.findOne(id);

        //then
        assertThat(findInfo).isEqualTo(info);
    }

    @Test
    public void 정보전체검색_삭제() throws Exception {
        //given
        ScreeningInfo info1 = ScreeningInfo.create(
                Movie.create("asdf", 120, EnumRate.FIFTEEN),
                LocalTime.of(13, 30));
        ScreeningInfo info2 = ScreeningInfo.create(
                Movie.create("asdf", 120, EnumRate.FIFTEEN),
                LocalTime.of(13, 30));
        screeningInfoService.resister(info1);
        screeningInfoService.resister(info2);

        //when
        int beforeDelete = screeningInfoService.findAll().size();
        screeningInfoService.delete(info1);
        int afterDelete = screeningInfoService.findAll().size();

        //then
        assertThat(afterDelete).isEqualTo(beforeDelete-1);
    }

    @Test
    public void 이름검색() throws Exception {
        //given
        ScreeningInfo info1 = ScreeningInfo.create(
                Movie.create("asdf", 120, EnumRate.FIFTEEN),
                LocalTime.of(13, 30));
        ScreeningInfo info2 = ScreeningInfo.create(
                Movie.create("asdf", 120, EnumRate.FIFTEEN),
                LocalTime.of(13, 30));
        screeningInfoService.resister(info1);
        screeningInfoService.resister(info2);

        //when
        List<ScreeningInfo> list = screeningInfoService.findByTitle("asdf");

        //then
        assertThat(list.size()).isEqualTo(2);
    }

    @Test
    public void 좌석수감소() throws Exception {
        //given
        ScreeningInfo info = ScreeningInfo.create(
                Movie.create("asdf", 120, EnumRate.FIFTEEN),
                LocalTime.of(13, 30));

        //when
        info.subtractRemnant(4);

        //then
        assertThat(info.getRemnant()).isEqualTo(6);
    }

    @Test
    public void 좌석수증가() throws Exception {
        //given
        ScreeningInfo info = ScreeningInfo.create(
                Movie.create("asdf", 120, EnumRate.FIFTEEN),
                LocalTime.of(13, 30));

        //when
        info.subtractRemnant(4);
        info.addRemnant(4);

        //then
        assertThat(info.getRemnant()).isEqualTo(10);
    }

    @Test
    public void 좌석수검증() throws Exception {
        //given
        ScreeningInfo info = ScreeningInfo.create(
                Movie.create("asdf", 120, EnumRate.FIFTEEN),
                LocalTime.of(13, 30));

        //when
        boolean result = info.checkRemnant(11);

        //then
        assertThat(result).isEqualTo(false);
    }
}