package edu.ucsb.cs156.spring.backenddemo.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import edu.ucsb.cs156.spring.backenddemo.services.RedditQueryService;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;




@WebMvcTest(value = RedditController.class)
public class RedditControllerTests {
  @Autowired
  private MockMvc mockMvc;
  @MockBean
  RedditQueryService mockRedditQueryService;

  @Test
  public void test_getPosts() throws Exception {
  
    String fakeJsonResult="{ \"fake\" : \"result\" }";
    when(mockRedditQueryService.getJSON("UCSantaBarbara")).thenReturn(fakeJsonResult);

    String url = String.format("/api/reddit/get?subreddit=UCSantaBarbara");

    MvcResult response = mockMvc
        .perform( get(url).contentType("application/json"))
        .andExpect(status().isOk()).andReturn();

    String responseString = response.getResponse().getContentAsString();

    assertEquals(fakeJsonResult, responseString);
  }

}