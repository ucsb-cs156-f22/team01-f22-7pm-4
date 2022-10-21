package edu.ucsb.cs156.spring.backenddemo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.header;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestClientTest(RedditQueryService.class)

public class RedditQueryServiceTests {

    @Autowired
    private MockRestServiceServer mockRestServiceServer;

    @Autowired
    private RedditQueryService RedditQueryService;

    @Test
    public void test_getJSON() {
        String subreddit = "UCSantaBarbara";
        String expectedURL = RedditQueryService.ENDPOINT.replace("{subreddit}", subreddit);
        String fakeJSON = "{ \"fake\" : \"result\" }";

        // java.net.URI uri = new java.net.URI(expectedURL);

        this.mockRestServiceServer.expect(requestTo(expectedURL))
                .andExpect(header("Accept", MediaType.APPLICATION_JSON.toString()))
                .andExpect(header("Content-Type", MediaType.APPLICATION_JSON.toString()))
                .andExpect(header("User-Agent", "spring-boot:cs156-team01:f22 (by /u/imawesomeguy117)"))
                .andRespond(withSuccess(fakeJSON, MediaType.APPLICATION_JSON));
        String actualResult = RedditQueryService.getJSON("UCSantaBarbara");
        assertEquals(fakeJSON, actualResult);
    }

}
