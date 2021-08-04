package springpractice.reservationmovie.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import springpractice.reservationmovie.domain.ScreeningInfo;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ScreeningInfoRepository {

    private final EntityManager em;

    public void save(ScreeningInfo screeningInfo) {
        em.persist(screeningInfo);
    }

    public void delete(ScreeningInfo screeningInfo) {
        em.remove(screeningInfo);
    }

    public ScreeningInfo findById(Long id) {
        return em.find(ScreeningInfo.class, id);
    }

    public List<ScreeningInfo> findByTitle(String movieTitle) {
        return em.createQuery("select s from ScreeningInfo s where s.movieTitle =:movieTitle",
                        ScreeningInfo.class)
                .setParameter("movieTitle", movieTitle)
                .getResultList();
    }

    public List<ScreeningInfo> findAll() {
        return em.createQuery("select s from ScreeningInfo s", ScreeningInfo.class)
                .getResultList();
    }
}
