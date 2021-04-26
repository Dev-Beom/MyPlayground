package com.spring.study.Service;

import com.spring.study.Model.Movie;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class MovieService {
    public List<Movie> query(final String query) {
        return Arrays.asList(
                Movie.builder().title("영화 1").link("http://test").build(),
                Movie.builder().title("영화 2").link("http://test").build(),
                Movie.builder().title("영화 1").link("http://test").build(),
                Movie.builder().title("영화 1").link("http://test").build(),
                Movie.builder().title("영화 1").link("http://test").build()
        );
    }
}
