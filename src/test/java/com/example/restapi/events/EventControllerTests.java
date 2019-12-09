package com.example.restapi.events;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*

 */
@RunWith(SpringRunner.class)
@WebMvcTest // 웹과 관련된 빈들이 등록됨, 슬라이싱 테스트, 단위 테스트라고 보기에는 너무 많은 애들이 개입되어 있음
public class EventControllerTests {

    @Autowired // 요청을 만들고 검증 할 수 있는 클래스, 웹 서버를 띄우지 않아 조금 더 빠르다
    MockMvc mockMvc; // 목킹이 되어있는 서블릿을 상대로 가짜요청을 만들어서, 그 서블릿에게 보내고 응답을 확인 활 수 있음

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    EventRepository eventRepository; // 목킹 객체임으로 반환값이 다 null, 따라서 Mockito를 이용해서 리턴값을 명시해줘야 함

    @Test
    public void createEvent() throws Exception {
        Event event = Event.builder()
                .name("Spring")
                .description("REST API Developement with Spring")
                .beginEventDateTime(LocalDateTime.of(2018, 11, 23, 14, 21))
                .closeEnrollmentDateTime(LocalDateTime.of(2018, 11, 24, 14, 21))
                .basePrice(100)
                .maxPrice(200)
                .limitOfEnrollment(100)
                .location("강남역 D2 스타트업 팩토리")
                .build();
        event.setId(10);;
        Mockito.when(eventRepository.save(event)).thenReturn(event);

        // 요청을 만들어줌
        mockMvc.perform(post("/api/events")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content(objectMapper.writeValueAsString(event))
                    .accept(MediaTypes.HAL_JSON))
                .andDo(print()) // 응답 콘솔로 확인
                .andExpect(status().isCreated()) // 201
                .andExpect(jsonPath("id").exists());
    }


}
