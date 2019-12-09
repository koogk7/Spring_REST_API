package com.example.restapi.events;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/*
 원래 TDD 는 요거 먼저 작성하고, 구현해야 하고
 하나의 데이터만 테스트 하는게 아니라 최소 데이터 3개를 가지고 만드는 것이 진정한 TDD
 */
@RunWith(SpringRunner.class)
@WebMvcTest // 웹과 관련된 빈들이 등록됨, 슬라이싱 테스트, 단위 테스트라고 보기에는 너무 많은 애들이 개입되어 있음
public class EventControllerMockingTests {

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
                .id(100)
                .free(true)
                .build();

        /* Controller에서 DTO를 받아 변환한 객체를 eventRepository에 넣어주기 때문에
           이 목킹이 제대로 동작하지 않음
           주어진 목킹은 event 객체가 들어 왔을 때 event를 리턴하는데, controller에서는 modelMaper가 리턴한 객체를
            eventRepository에 넣어주기 때문에 null을 리턴
            따라서 강의에서는 통합테스트로 변경, Mock 테스트는 목킹할 객체와 관리포인트가 많아지기 때문에 테스트 코드가 자주 깨짐

         */
        Mockito.when(eventRepository.save(event)).thenReturn(event);

        // 요청을 만들어줌
        mockMvc.perform(post("/api/events")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content(objectMapper.writeValueAsString(event))
                    .accept(MediaTypes.HAL_JSON))
                .andDo(print()) // 응답 콘솔로 확인
                .andExpect(status().isCreated()) // 201
                .andExpect(jsonPath("id").exists())
                .andExpect(header().exists(HttpHeaders.LOCATION))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE))
                .andExpect(jsonPath("id").value(Matchers.not(100)))
                .andExpect(jsonPath("free").value(Matchers.not(true)));
    }


}
