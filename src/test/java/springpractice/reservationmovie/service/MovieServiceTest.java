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

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MovieServiceTest {

    @Autowired MovieService movieService;

    @Test
    public void 영화등록_검색() throws Exception {
        //given
        Movie movie = Movie.create("정글 크루즈", 120, EnumRate.FIFTEEN);

        //when
        Long id = movieService.resister(movie);
        Movie findMovie = movieService.findOne(id);

        //then
        assertThat(findMovie).isEqualTo(movie);
    }

    @Test
    public void 영화이름검색() throws Exception {
        //given
        Movie movie = Movie.create("정글 크루즈", 120, EnumRate.FIFTEEN);

        //when
        Long id = movieService.resister(movie);
        Movie findMovie = movieService.findByTitle("정글 크루즈").get();

        //then
        assertThat(findMovie).isEqualTo(movie);
    }

    @Test
    public void 영화전체검색() throws Exception {
        //given
        Movie movie1 = Movie.create("정글 크루즈", 120, EnumRate.FIFTEEN);
        Movie movie2 = Movie.create("랑종", 120, EnumRate.FIFTEEN);

        //when
        movieService.resister(movie1);
        movieService.resister(movie2);
        List<Movie> movies = movieService.findAll();

        //then
        assertThat(movies.size()).isEqualTo(2);
    }

    @Test
    public void 영화삭제() throws Exception {
        //given
        Movie movie1 = Movie.create("정글 크루즈", 120, EnumRate.FIFTEEN);
        Movie movie2 = Movie.create("랑종", 120, EnumRate.FIFTEEN);

        //when
        movieService.resister(movie1);
        movieService.resister(movie2);
        movieService.delete(movie1);
        List<Movie> movies = movieService.findAll();

        //then
        assertThat(movies.size()).isEqualTo(1);
    }
}