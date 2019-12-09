package com.example.restapi.events;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
/*
자바 11 버전 업데이트 문제점
1. Inteli J J 18.1 버전을 사용중 --> 18.3 버전부터 자바 11 지원
2. JDK 1.8만 설치되어 있었음 --> 오라클에서 11 설치
3. 프로젝트 구조에서 소스 타겟, 디펜던시 타겟을 11로 설정해줘야 함
 */
class EventTest {

    @Test
    public void builder(){
        Event event = Event.builder()
                .name("Inflean Spring REST API")
                .description("REST API developement with Spring")
                .build();
        assertThat(event).isNotNull();
    }

    @Test
    public void javaBean() {
        //Given
        String name = "Event";
        String description = "Spring"; // 스트링 문자열 블락 지정 후 cmd + alt + v 단축키 사용시 문자열을 변수로 뺄 수 있다!

        // When
        Event event = new Event();
        event.setName(name);
        event.setDescription(description);

        // Then
        assertThat(event.getName()).isEqualTo(name);
        assertThat(event.getDescription()).isEqualTo(description);
    }
}