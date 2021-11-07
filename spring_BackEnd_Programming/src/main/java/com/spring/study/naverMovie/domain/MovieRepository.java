package com.spring.study.naverMovie.domain;

import java.util.List;

public interface MovieRepository{
    List<Movie> findByQuery(String query);
}
