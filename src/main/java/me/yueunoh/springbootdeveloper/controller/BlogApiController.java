package me.yueunoh.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.yueunoh.springbootdeveloper.domain.Article;
import me.yueunoh.springbootdeveloper.dto.AddArticleRequest;
import me.yueunoh.springbootdeveloper.dto.ArticleResponse;
import me.yueunoh.springbootdeveloper.dto.UpdateArticleRequest;
import me.yueunoh.springbootdeveloper.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3000") // 프론트엔드 도메인
public class BlogApiController {

    private final BlogService blogService;

    // 블로그 글 추가
    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request) {
        Article savedArticle = blogService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle);
    }

    // 블로그 글 전체 조회
    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles() {
        List<ArticleResponse> articles = blogService.findAll()
                .stream()
                .map(ArticleResponse::new) // 각 Article 객체를 ArticleResponse 객체로 매핑
                .toList();

        return ResponseEntity.ok()
                .body(articles);
    }

    // 블로그 글 단일 조회
    @GetMapping("/api/articles/{id}")
    // URL 경로에서 값 추출
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable long id) {
        Article article = blogService.findById(id);

        return ResponseEntity.ok()
                .body(new ArticleResponse(article));
    }

    // 블로그 글 삭제
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable long id) {
        blogService.delete(id);

        return ResponseEntity.ok()
                .build();
    }

    // 블로그 글 수정
    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable long id, @RequestBody UpdateArticleRequest request) {
        Article updatedArticle = blogService.update(id, request);

        return ResponseEntity.ok()
                .body(updatedArticle);
    }
 }
