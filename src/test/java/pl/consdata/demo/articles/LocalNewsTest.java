package pl.consdata.demo.articles;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.consdata.demo.DemoApplication;
import pl.consdata.demo.articles.api.LocalNews;
import pl.consdata.demo.articles.api.ResponseTransformer;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


@RunWith(SpringRunner.class)
@WebMvcTest(DemoApplication.class)
public class LocalNewsTest {

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = standaloneSetup(new LocalNews(new ResponseTransformer())).build();
    }


    @Test
    public void newsGotSuccessfully() throws Exception {
        this.mockMvc.perform(get("/api/news/pl/technology"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("technology"))); // I just want to be sure response is not null
    }
}
