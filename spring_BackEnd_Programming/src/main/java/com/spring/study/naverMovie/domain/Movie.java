package com.spring.study.naverMovie.domain;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;


/**
 * 영화 정보를 객체화 한 모델 클래스
 */
@Builder
@Getter
public class Movie {
    private String title;
    private String link;
    private float userRating;
}
