package com.example.restapi.events;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*

 */
@RunWith(SpringRunner.class)
@WebMvcTest // 웹과 관련된 빈들이 등록됨, 슬라이싱 테스트, 단위 테스트라고 보기에는 너무 많은 애들이 개입되어 있음
public class EventControllerTests {

    @Autowired // 요청을 만들고 검증 할 수 있는 클래스, 웹 서버를 띄우지 않아 조금 더 빠르다
    MockMvc mockMvc; // 목킹이 되어있는 서블릿을 상대로 가짜요청을 만들어서, 그 서블릿에게 보내고 응답을 확인 활 수 있음

    @Test
    public void createEvent() throws Exception {
        // 요청을 만들어줌
        mockMvc.perform(post("/api/events")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaTypes.HAL_JSON))
                .andExpect(status().isCreated()); // 201
    }


}
