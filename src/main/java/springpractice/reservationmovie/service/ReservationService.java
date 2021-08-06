package springpractice.reservationmovie.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springpractice.reservationmovie.domain.Member;
import springpractice.reservationmovie.domain.Reservation;
import springpractice.reservationmovie.domain.ScreeningInfo;
import springpractice.reservationmovie.repository.ReservationRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

    @Transactional
    public String reserve(Member member, ScreeningInfo screeningInfo, int adultCount, int childCount) {
        Reservation reservation = Reservation.create(member, screeningInfo, adultCount, childCount);
        reservationRepository.save(reservation);
        return reservation.getId();
    }

    @Transactional
    public String cancel(Reservation reservation) {
       return reservation.remove();
    }

    public Reservation findOne(String id) {
        return reservationRepository.findById(id);
    }

    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    public List<Reservation> showMemberReservations(Member member) {
        return reservationRepository.findByMember(member);
    }
}
