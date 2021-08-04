package springpractice.reservationmovie.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity @Getter
public class Reservation {

    @Id @GeneratedValue
    @Column(name = "reservation_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY)
    private ScreeningInfo screeningInfo;

    private int totalPrice;

    private int adult;
    private int minor;

    //== 연관관게 편의 매서드 ==//
    private void setMember(Member member) {
        this.member = member;
        member.getReservations().add(this);
    }

    //== 생성 매서드 ==//
    public static Reservation create(Member member, ScreeningInfo screeningInfo, int adult, int minor) {
        Reservation reservation = new Reservation();

        reservation.setMember(member);
        reservation.screeningInfo = screeningInfo;

        reservation.adult = adult;
        reservation.minor = minor;

        reservation.totalPrice = adult * 13000 + minor * 10000;

        reservation.screeningInfo.reserveSeat(adult + minor);

        return reservation;
    }
}
