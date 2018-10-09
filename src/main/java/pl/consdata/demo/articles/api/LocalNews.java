package pl.consdata.demo.articles.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.consdata.demo.articles.domain.News;


@Controller
public class LocalNews {

    private final ResponseTransformer transformer;

    @Autowired
    public LocalNews(ResponseTransformer transformer) {
        this.transformer = transformer;
    }


    @RequestMapping(value = "api/news/{country}/{category}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<News> getNews(@PathVariable("country") String country, @PathVariable("category") String category) {

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(NewsApiConfig.URL)
                .queryParam("country", country)
                .queryParam("category", category)
                .queryParam("apiKey", NewsApiConfig.API_KEY);

        ExternalResponseDTO externalResponse = new RestTemplate().getForObject(builder.toUriString(), ExternalResponseDTO.class);

        News news = transformer.toNews(externalResponse);
        news.setCountry(country);
        news.setCategory(category);

        return new ResponseEntity<>(news, HttpStatus.OK);
    }
}
