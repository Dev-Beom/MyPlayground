package com.spring.study.naverMovie.controller;

import com.spring.study.naverMovie.domain.Movie;
import com.spring.study.naverMovie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 클라이언트에게 제공하는 엔드포인트 클래스
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/movie")
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/search")
    public List<Movie> getMovieByQuery(@RequestParam(name = "title") String movieTitle) {
        return movieService.search(movieTitle);
    }
}
