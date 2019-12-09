package com.example.restapi.events;

import lombok.*;

import java.time.LocalDateTime;

/**
EqualsAndHashCode는 기본적으로 모든 필드를 이용해서 비교
객체 간의 연관관계가 있을 때 상호참조과 있게되면 스택오버플로우가 발생 할 수 있다
때문에 EqualsAndHashCode Of로 연관관계가 있는 필드를 주는건 바람직하지 않음

 롬북 어노테이션은 스프링 메타 어노테이션으로 동작하지 않기 때문에 이 어노테이션의 중복을 줄일 수 없음 ㅠ
롬북에서는 @Data를 제공하는데, EqualsAndHashCode를 모든 필드를 사용해서 구현하기 때문에 권장하지 않음

target 패키지에서 롬복이 적용된 코드를 확인 할 수 있음
 */
@Builder @AllArgsConstructor @NoArgsConstructor
@Getter @Setter @EqualsAndHashCode(of = "id")
public class Event {
    private Integer id;
    private String name;
    private String description;
    private LocalDateTime beginEnrollmentDateTime;
    private LocalDateTime closeEnrollmentDateTime;
    private LocalDateTime beginEventDateTime;
    private LocalDateTime endEventDateTime;
    private String location; // (optional) 이게 없으면 온라인 모임
    private int basePrice; // (optional)
    private int maxPrice; // (optional)
    private int limitOfEnrollment;
    private boolean offline;
    private boolean free;
    private EventStatus eventStatus;

}
