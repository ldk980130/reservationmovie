package springpractice.reservationmovie.domain;

import lombok.Getter;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Entity
@Getter
public class Reservation {

    @Id
    @Column(name = "reservation_id")
    private String id;

    //=고유아이디 생성 매서드=//
    private String createUniqueId(Long memberId) {
        return System.currentTimeMillis() + "" + memberId;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ScreeningInfo screeningInfo;

    private int adultCount;
    private int childCount;

    private int totalPrice;

    private ReservationStatus status;

    private String time;

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

        if (!screeningInfo.checkRemnant(adultCount + childCount)) {
            throw new IllegalStateException();
        }

        Reservation reservation = new Reservation();

        reservation.setMember(member);
        reservation.screeningInfo = screeningInfo;

        reservation.adultCount = adultCount;
        reservation.childCount = childCount;

        screeningInfo.subtractRemnant(adultCount + childCount);

        reservation.totalPrice = screeningInfo.getAdultPrice() * adultCount
                + screeningInfo.getChildPrice() * childCount;

        reservation.status = ReservationStatus.RESERVED;
        reservation.time = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());

        reservation.id = reservation.createUniqueId(member.getId());

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
