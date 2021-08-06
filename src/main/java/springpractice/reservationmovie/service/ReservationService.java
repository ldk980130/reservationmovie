package springpractice.reservationmovie.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springpractice.reservationmovie.domain.Member;
import springpractice.reservationmovie.domain.Reservation;
import springpractice.reservationmovie.domain.ScreeningInfo;
import springpractice.reservationmovie.repository.MemberRepository;
import springpractice.reservationmovie.repository.ReservationRepository;
import springpractice.reservationmovie.repository.ScreeningInfoRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final MemberRepository memberRepository;
    private final ScreeningInfoRepository screeningInfoRepository;

    @Transactional
    public String reserve(Long memberId, Long infoId, int adultCount, int childCount) {
        Member member = memberRepository.findById(memberId);
        ScreeningInfo screeningInfo = screeningInfoRepository.findById(infoId);

        Reservation reservation = Reservation.create(member, screeningInfo, adultCount, childCount);
        reservationRepository.save(reservation);
        return reservation.getId();
    }

    @Transactional
    public void cancel(String id) {
        Reservation reservation = reservationRepository.findById(id);
        reservation.remove();
    }

    public Reservation findOne(String id) {
        return reservationRepository.findById(id);
    }

    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    public List<Reservation> showMemberReservations(Long memberId) {
        Member member = memberRepository.findById(memberId);
        return member.getReservations();
    }
}
