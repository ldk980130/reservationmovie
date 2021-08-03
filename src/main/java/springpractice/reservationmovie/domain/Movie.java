package springpractice.reservationmovie.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Movie {

    @Id @GeneratedValue
    @Column(name = "movie_id")
    private Long id;

    private String title;
    private int runningTime;

    @OneToMany(mappedBy = "movie")
    List<ScreeningInfo> screeningInfoList = new ArrayList<>();

    public static Movie create(String title, int runningTime) {
        Movie movie = new Movie();
        movie.title = title;
        movie.runningTime = runningTime;
        return movie;
    }

}
