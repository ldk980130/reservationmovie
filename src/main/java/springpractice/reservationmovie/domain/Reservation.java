package springpractice.reservationmovie.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class Reservation {

    @Id
    @Column(name = "reservation_id")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY)
    private ScreeningInfo screeningInfo;

    private int adultCount;
    private int childCount;

    private int totalPrice;

    private ReservationStatus status;

    private LocalDateTime time;

    //== 연관관게 편의 매서드 ==//
    private void setMember(Member member) {
        this.member = member;
        member.getReservations().add(this);
    }

    //== 생성 매서드 ==//
    protected Reservation() {
    }

    public static Reservation create(Member member, ScreeningInfo screeningInfo,
                                     int adultCount, int childCount) {

        boolean isCreatable = screeningInfo.checkRemnant(adultCount + childCount);
        if (isCreatable) throw new IllegalStateException();

        Reservation reservation = new Reservation();

        reservation.setMember(member);
        reservation.screeningInfo = screeningInfo;

        reservation.adultCount = adultCount;
        reservation.childCount = childCount;

        reservation.totalPrice = screeningInfo.getAdultPrice() * adultCount
                + screeningInfo.getChildPrice() * childCount;

        reservation.status = ReservationStatus.RESERVED;
        reservation.time = LocalDateTime.now();

        reservation.id = System.currentTimeMillis()+"";

        return reservation;
    }

    /**
     * 비지니스 로직
     */
    public String remove() {
        this.status = ReservationStatus.CANCEL;
        this.getScreeningInfo()
                .addRemnant(this.getAdultCount() + this.childCount);
        return this.getId();
    }
}
