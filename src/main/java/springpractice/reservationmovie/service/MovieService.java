package springpractice.reservationmovie.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springpractice.reservationmovie.domain.Movie;
import springpractice.reservationmovie.repository.MovieRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MovieService {

    private final MovieRepository movieRepository;

    @Transactional
    public Long resister(Movie movie) {
        movieRepository.save(movie);
        return movie.getId();
    }

    @Transactional
    public Long delete(Movie movie) {
        movieRepository.delete(movie);
        return movie.getId();
    }

    public Movie findOne(Long id) {
        return movieRepository.findById(id);
    }

    public Optional<Movie> findByTitle(String title) {
        return movieRepository.findByTitle(title);
    }

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }
}
