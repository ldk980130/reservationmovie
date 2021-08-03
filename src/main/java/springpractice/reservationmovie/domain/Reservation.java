package springpractice.reservationmovie.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity @Getter
public class Reservation {

    @Id @GeneratedValue
    @Column(name = "reservation_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY)
    private ScreeningInfo screeningInfo;

    private int price;

    //== 연관관게 편의 매서드 ==//
    private void setMember(Member member) {
        this.member = member;
        member.getReservations().add(this);
    }

    public static Reservation create(Member member, ScreeningInfo screeningInfo) {
        Reservation reservation = new Reservation();

        reservation.setMember(member);
        reservation.screeningInfo = screeningInfo;

        if(member.getStatus() == Status.ADULT) reservation.price = 13000;
        else reservation.price = 10000;

        return reservation;
    }
}
