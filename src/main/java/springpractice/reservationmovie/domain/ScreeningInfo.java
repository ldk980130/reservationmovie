package springpractice.reservationmovie.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalTime;

@Entity @Getter
public class ScreeningInfo {

    @Id
    @GeneratedValue
    @Column(name = "screening_info_id")
    private Long id;

    private int seatingCapacity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    private LocalTime startTime;
    private LocalTime endTime;

    //== 연관관게 편의 매서드 ==//
    private void setMovie(Movie movie) {
        this.movie = movie;
        movie.getScreeningInfoList().add(this);
    }

    //== 생성 매서드 ==//
    public static ScreeningInfo create(Movie movie, LocalTime startTime) {
        ScreeningInfo info = new ScreeningInfo();
        info.setMovie(movie);
        info.startTime = startTime;
        info.endTime = info.startTime.plusMinutes(movie.getRunningTime());
        info.seatingCapacity = 10;
        return info;
    }

    public void reserveSeat(int num) {

        if (this.seatingCapacity < num) {
            throw new IllegalStateException("자리가 없습니다.");
        }
        this.seatingCapacity -= num;
    }
}
