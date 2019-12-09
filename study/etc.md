## ModelMapper
- 객체간의 전환에 ModelMapper 사용
- Entity, DTO 사이의 전환에 사용
- ModelMapper는 리플렉션을 통해 구현이 되어있음

## spring.jackson
- `deserialization`
    - Json을 객체로 바꾸는 과정
    - `SpringBoot`에서는 `properties` 설정을 통해 객체를 Json으로 바꿀 때에 대한 처리를 할 수 있음
    ```java
    spring.jackson.deserialization.fail-on-unknown-properties=true
    ```
    위 설정을 통해 객체 없는 파라미터가 들어올 경우 `Bad_Request`를 보냄
    