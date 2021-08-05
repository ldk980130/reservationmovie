package springpractice.reservationmovie.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import springpractice.reservationmovie.domain.Member;
import springpractice.reservationmovie.domain.Reservation;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReservationRepository {

    private final EntityManager em;

    public void save(Reservation reservation) {
        em.persist(reservation);
    }

    public void delete(Reservation reservation) {
        em.remove(reservation);
    }

    public Reservation findById(String id) {
        return em.find(Reservation.class, id);
    }

    public List<Reservation> findByMember(Member member) {
        return member.getReservations();
    }

    public List<Reservation> findAll() {
        return em.createQuery("select r from Reservation r",Reservation.class)
                .getResultList();
    }


}
