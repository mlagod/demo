package pl.consdata.demo.articles.api;

import org.springframework.stereotype.Service;
import pl.consdata.demo.articles.domain.Article;
import pl.consdata.demo.articles.domain.News;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResponseTransformer {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public News toNews(ExternalResponseDTO dto){
        News news = new News();

        List<Article> articles = dto
                .getArticles()
                .stream()
                .map(this::toArticle)
                .collect(Collectors.toList());

        news.setArticles(articles);

        return news;
    }

    private Article toArticle(ExternalArticleDTO dto) {
        Article article = new Article();

        article.setAuthor(dto.getAuthor());
        article.setDate(dateFormat.format(dto.getPublishedAt()));
        article.setDescription(dto.getDescription());
        article.setImageUrl(dto.getUrlToImage());
        article.setTitle(dto.getTitle());
        article.setSourceName(dto.getSource().getName());
        article.setArticleUrl(dto.getUrl());

        return article;
    }
}
