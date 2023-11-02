package me.yueunoh.springbootdeveloper.dto;

import lombok.Getter;
import me.yueunoh.springbootdeveloper.domain.Article;

import java.time.LocalDateTime;

@Getter
public class ArticleResponse {

    private final Long id;
    private final String title;
    private final String content;
    private LocalDateTime createdAt;

    // Article 객체를 받아 해당 객체의 제목과 내용을 가져옴
    public ArticleResponse(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.createdAt = article.getCreatedAt();
    }
}
