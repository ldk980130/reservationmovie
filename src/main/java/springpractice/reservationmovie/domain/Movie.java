package springpractice.reservationmovie.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Movie {

    @Id
    @GeneratedValue
    @Column(name = "movie_id")
    private Long id;

    private String title;

    private int runningTime;

    @Enumerated(EnumType.STRING)
    private EnumRate rate;

    @OneToMany(mappedBy = "movie")
    List<ScreeningInfo> screeningInfoList = new ArrayList<>();

    //== 생성 매서드 ==//
    protected Movie() {
    }

    public static Movie create(String title, int runningTime, EnumRate rate) {
        Movie movie = new Movie();
        movie.title = title;
        movie.runningTime = runningTime;
        movie.rate = rate;
        return movie;
    }

}
