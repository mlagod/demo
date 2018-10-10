package pl.consdata.demo.articles.api;

import java.util.List;

public class ExternalResponseDTO {

    private List<ExternalArticleDTO> articles;

    public List<ExternalArticleDTO> getArticles() {
        return articles;
    }

    public void setArticles(List<ExternalArticleDTO> articles) {
        this.articles = articles;
    }
}
