package com.spring_study.springboot.web.dto;

import com.spring_study.springboot.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {
    private Long id;
    private String title;
    private String contents;
    private String author;

    public PostsResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.contents = entity.getContent();
        this.author = entity.getAuthor();
    }
}
