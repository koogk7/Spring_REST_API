## Test Driven Developed
> 제대로 된 TDD는 테스트 코드를 먼저 작성하고 구현하고 하나의 데이터만 테스트 하는 것이 아니라 최소 3개 이상의 데이터를 가지고 만들어야 한다.

### Mock
  - `MockBean`
    - 목킹 객체 주입
    - 목킹 객체의 반환값은 null
    - 반환값 사용을 위해서는 `Mockito`를 아래와 같이 사용해서 반환값을 명시해야함 
    ```java 
        Mockito.when(eventRepository.save(event)).thenReturn(event);  
      ```
  
   
### WebMvcTest  
   - 웹과 관련된 빈들만 등록됨
   - 일부만 테스트하기 때문에 슬라이싱 테스트라고 함
   - 단위 테스트라고 보기에는 너무 많은 애들이 존재
   - 테스트의 관심사는 오직 Request와 그에 따른 Response
   - 통합 테스트를 진행하기 어려운 테스트를 진행
     - 외부 API 같은 Rollback 처리가 힘들거나 불가능한 테스트를 주로 사용
     - 예를 들어 외부 결제 모듈 API를 콜하면 안 되는 케이스에서 주로 사용 할 수 있음
     - 이런 문제는 통합 테스트에서 해당 객체를 Mock 객체로 변경해서 테스트를 변경해서 테스트
   - `MockMvc`  
        - 요청을 검증 할 수 있는 클래스
        - 목킹이 되어있는 서블릿에게 요청을 보내고 응답을 확인 할 수 있다
        - 'perform' 메소드를 통해 요청을 만들 수 있음
        ```java
        mockMvc.perform(post("/api/events")
                            .contentType(MediaType.APPLICATION_JSON_UTF8)
                            .content(objectMapper.writeValueAsString(event))
                            .accept(MediaTypes.HAL_JSON))
                        .andDo(print()) // 응답 콘솔로 확인
                        .andExpect(status().isCreated()) // 201
                        .andExpect(jsonPath("id").exists())
                        .andExpect(header().exists(HttpHeaders.LOCATION))
                        .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE));
        ```
     
     
## 추가 Refer
[스프링가이드](https://github.com/cheese10yun/spring-guide/blob/master/docs/test-guide.md#mock-api-테스트)