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
    public void resister(Movie movie) {
        movieRepository.save(movie);
    }

    @Transactional
    public void delete(Movie movie) {
        movieRepository.delete(movie);
    }

    public Movie searchById(Long id) {
        return movieRepository.findById(id);
    }

    public Optional<Movie> searchByTitle(String title) {
        return movieRepository.findByTitle(title);
    }

    public List<Movie> searchAll() {
        return movieRepository.findAll();
    }
}
