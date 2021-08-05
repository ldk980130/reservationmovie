package springpractice.reservationmovie.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Getter
public class ScreeningInfo {

    @Id
    @GeneratedValue
    @Column(name = "screening_info_id")
    private Long id;

    private int remnant;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    private String movieTitle;

    private LocalTime startTime;
    private LocalTime endTime;

    private int adultPRice;
    private int childPrice;

    //== 연관관게 편의 매서드 ==//
    private void setMovie(Movie movie) {
        this.movie = movie;
        this.movieTitle = movie.getTitle();
        movie.getScreeningInfoList().add(this);
    }

    //== 생성 매서드 ==//
    protected ScreeningInfo() {
    }

    public static ScreeningInfo create(Movie movie, LocalTime startTime) {
        ScreeningInfo info = new ScreeningInfo();
        info.setMovie(movie);
        info.startTime = startTime;
        info.endTime = info.startTime.plusMinutes(movie.getRunningTime());
        info.remnant = 10;
        return info;
    }

    /**
     * 비지니스 로직
     */
    public boolean checkRemnant(int count) {
        if (count > this.remnant) {
            return false;
        }
        return true;
    }

    public void addRemnant(int count) {
        this.remnant += count;
    }

    public void subtractRemnant(int count) {
        this.remnant -= count;
    }
}
