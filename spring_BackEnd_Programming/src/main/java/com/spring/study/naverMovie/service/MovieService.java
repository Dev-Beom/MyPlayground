package com.spring.study.naverMovie.service;
import com.spring.study.naverMovie.domain.Movie;
import com.spring.study.naverMovie.domain.MovieGroup;
import com.spring.study.naverMovie.domain.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 영화 정보를 제공하는 서비스 클래스
 */
@RequiredArgsConstructor
@Service
public class MovieService {
    private final MovieRepository movieRepository;

    public List<Movie> search(final String query) {
        MovieGroup movieGroup = new MovieGroup(movieRepository.findByQuery(query));
        return movieGroup.getListOrderRating();
    }
}
