package springpractice.reservationmovie.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import springpractice.reservationmovie.domain.Movie;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MovieRepository {

    private final EntityManager em;

    public void save(Movie movie) {
        em.persist(movie);
    }

    public void delete(Movie movie) {
        em.remove(movie);
    }

    public Movie findById(Long id) {
        return em.find(Movie.class, id);
    }

    public Optional<Movie> findByTitle(String title) {
        return em.createQuery("select m from Movie m where m.title =:title", Movie.class)
                .setParameter("title", title)
                .getResultList()
                .stream().findAny();
    }

    public List<Movie> findAll() {
        return em.createQuery("select m from Movie m", Movie.class)
                .getResultList();
    }
}
