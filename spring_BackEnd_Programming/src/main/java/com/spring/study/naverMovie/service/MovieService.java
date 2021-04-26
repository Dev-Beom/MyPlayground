package com.spring.study.naverMovie.service;
import com.spring.study.naverMovie.domain.Movie;
import com.spring.study.naverMovie.domain.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MovieService {
    private final MovieRepository movieRepository;

    public List<Movie> search(final String query) {
        return movieRepository.findByQuery(query);
    }
}
